package model;

import java.io.File;

/**
 * @since 03.09.2015
 * @author Julian Schelker
 */
public class Document {

	String name;
	String description;
	long byteSize;

	public Document(File file) {
		name = file.getName();
		description = "";
		byteSize = file.getUsableSpace();
	}

	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return this.description;
	}

	public long getByteSize() {
		return this.byteSize;
	}

	@Override
	public String toString() {
		return getName();
	}
}
