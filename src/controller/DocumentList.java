package controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import model.Document;

public class DocumentList extends ArrayList<Document>{

	public static DocumentList get(String path) {
		
		DocumentList list = new DocumentList();
		File folder = new File(path);
		
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
