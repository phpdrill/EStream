package model;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class DocumentList extends ArrayList<Document> implements Serializable{
 
	private static final long serialVersionUID = -7249527028785845889L;

	public static DocumentList get(String path) {
		
		DocumentList list = new DocumentList();
		File folder = new File(path);
		
		if(folder.listFiles()==null){
			return new DocumentList();
		}
		
		for (final File file : folder.listFiles()) {
	        if (file.isDirectory()) {
	            //get(file); RECURSIVE 
	        } else {
	        	
	            list.add(new Document(file));
	        }
	    }

		
		return list;
	}

}
