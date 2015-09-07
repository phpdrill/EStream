package model;

import java.io.File;
import java.io.Serializable;

/**
 * @since 03.09.2015
 * @author Julian Schelker
 */
public class Document implements Serializable {

	private static final long serialVersionUID = -6147394769111959196L;
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
