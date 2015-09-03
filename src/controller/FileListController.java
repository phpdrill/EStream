package controller;

import java.util.List;

import model.Document;
import model.Host;
import view.ViewApi;

public class FileListController {

	public void show(String path, Host host, ViewApi api) {
		
		List<Document> fileList = DocumentList.get(path);
		
		api.setDocumentListForHost(host, fileList);
		
	}

	
	
}
