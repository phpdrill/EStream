package model;

import java.io.File;

/**
 * @since 03.09.2015
 * @author Julian Schelker
 */
public class Document {
	public Document(File file) {
		name = file.getName();
		description = "";
		byteSize = file.getUsableSpace();
	}
	String name;
	String description;
	long byteSize;
}
