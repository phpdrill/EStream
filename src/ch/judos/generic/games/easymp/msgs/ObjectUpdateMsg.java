package ch.judos.generic.games.easymp.msgs;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.function.Consumer;

import ch.judos.generic.data.MutableBoolean;
import ch.judos.generic.games.easymp.FieldInformation;
import ch.judos.generic.games.easymp.MonitoredObjectStorage;
import ch.judos.generic.games.easymp.ObjectId;
import ch.judos.generic.games.easymp.model.ObjectWithMetaData;
import ch.judos.generic.games.easymp.model.UpdatableI;
import ch.judos.generic.reflection.Classes;

/**
 * @since 22.05.2015
 * @author Julian Schelker
 */
public class ObjectUpdateMsg extends UpdateMsg {

	private static final long serialVersionUID = -170694419417208599L;
	public ObjectWithMetaData data;

	public ObjectUpdateMsg(Object object, MonitoredObjectStorage storage) {
		this.data = ObjectWithMetaData.fromObject(object, storage);
	}

	@Override
	public void install(MonitoredObjectStorage storage) {
		try {
			ObjectId id = this.data.id;
			Object localObject = storage.getObjectById(id);

			ArrayList<UpdatableI> objectsToUpdate = new ArrayList<UpdatableI>();
			updateObjectValues(localObject, this.data, storage, objectsToUpdate);
			for (UpdatableI up : objectsToUpdate) {
				up.wasUpdated();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void updateObjectValues(Object localObject, ObjectWithMetaData remote,
		MonitoredObjectStorage storage, ArrayList<UpdatableI> objectsToUpdate)
		throws Exception {

		ArrayList<Field> localFields = FieldInformation.getRelevantFieldsOf(localObject);
		Object[] remoteValues = remote.fields;
		if (localFields.size() != remoteValues.length)
			throw new RuntimeException("Retrieved fields are not of equal size: "
				+ localFields + "\nAnd:" + remoteValues);
		final MutableBoolean updated = new MutableBoolean(false);
		if (localObject instanceof UpdatableI) {
			objectsToUpdate.add((UpdatableI) localObject);
		}

		for (int i = 0; i < localFields.size(); i++) {
			// distinguish between primitives and objects:
			if (remoteValues[i] instanceof ObjectWithMetaData) {
				Field localField = localFields.get(i);
				Object localValue = localField.get(localObject);
				ObjectWithMetaData remoteValue = (ObjectWithMetaData) remoteValues[i];
				updateObjectIdCheck(localValue, remoteValue, (Object o) -> {
					setFieldValue(localObject, localField, o);
					updated.state = true;
				}, storage, objectsToUpdate);
			}
			else {
				localFields.get(i).set(localObject, remoteValues[i]);
				updated.state = true;
			}
		}

		if (!updated.state && (localObject instanceof UpdatableI)) {
			objectsToUpdate.remove(localObject);
		}
	}

	private static void updateObjectIdCheck(Object localValue, ObjectWithMetaData remoteValue,
		Consumer<Object> onNewObject, MonitoredObjectStorage storage,
		ArrayList<UpdatableI> objectsToUpdate) throws Exception {

		ObjectId localId = storage.getIdOf(localValue);
		ObjectId remoteId = remoteValue.id;
		if (localId != null && localId.equals(remoteId)) {
			// change content of object
			updateObjectValues(localValue, remoteValue, storage, objectsToUpdate);
		}
		else {
			// object reference has changed
			Object newObj = storage.getObjectById(remoteId);
			if (newObj == null) { // object reference is unknown
				Constructor<?> constructor = Classes
					.getSerializationConstructor(remoteValue.clazz);
				newObj = constructor.newInstance();
				storage.addMonitoredObject(newObj, remoteId);
			}
			onNewObject.accept(newObj);
			updateObjectValues(newObj, remoteValue, storage, objectsToUpdate);
		}
	}

	private static void setFieldValue(Object o, Field f, Object newValue) {
		try {
			f.setAccessible(true);
			f.set(o, newValue);
		}
		catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}
