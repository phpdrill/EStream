package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DocumentList extends ArrayList<Document>{

	public static DocumentList get(String path) {
		
		DocumentList list = new DocumentList();
		File folder = new File(path);
		
		if(folder == null) return new DocumentList();
		
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
