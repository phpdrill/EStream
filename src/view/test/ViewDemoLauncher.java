package view.test;

import java.io.File;

import model.Document;
import model.Host;
import view.SelectionListener;
import view.ViewApi;
import ch.judos.generic.data.DynamicList;

/**
 * @since 07.09.2015
 * @author Julian Schelker
 */
public class ViewDemoLauncher implements SelectionListener<Host> {
	private Host demoHost;
	private ViewApi api;

	public static void main(String[] args) {
		new ViewDemoLauncher();
	}

	public ViewDemoLauncher() {
		this.demoHost = new Host("Julian", "127.0.0.1", "1234");
		DynamicList<Host> list = new DynamicList<Host>(demoHost);
		this.api = new ViewApi();
		api.setListOfHosts(list);
		api.setSelectionListener(this);
	}

	@Override
	public void selected(Host object) {
		DynamicList<Document> files = new DynamicList<>();

		for (int i = 0; i < 5; i++) {
			Document d = new Document(new File("demoFile.txt"));
			files.add(d);
		}

		this.api.setDocumentListForHost(this.demoHost, files);
	}
}
