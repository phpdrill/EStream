package controller;

import model.DocumentList;
import model.Host;
import view.ViewApi;

public class FileListController {

	private ViewApi api;

	public FileListController(ViewApi api) {
		this.api = api;
	}

	public void show(DocumentList list, Host host) {
		
		api.setDocumentListForHost(host, list);
		
	}

	
	
}
