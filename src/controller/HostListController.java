package controller;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;

import model.Host;
import model.HostList;

import org.xml.sax.SAXException;

import util.URLConnector;
import view.SelectionListener;
import view.ViewApi;

public class HostListController implements SelectionListener<Host> {

	private HostList hostList = null;
	private ViewApi api;
	public HostListController(ViewApi api) {
		this.api = api;
	}

	public void show() {

		if (hostList == null)
			get();

		api.setSelectionListener(this);
		api.setListOfHosts(hostList);

	}

	private void get() {

		try {

			InputStream content = URLConnector
				.getContent("http://www.however.ch/estream/getHostList.php");
			hostList = HostList.getFromString(content);

		}
		catch (ParserConfigurationException exception) {
			exception.printStackTrace();
		}
		catch (SAXException exception) {
			exception.printStackTrace();
		}
		catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public void selected(Host host) {

		// *** PFAD ALESSIO ***//
		String path = host.getName().equals("Alessio") ? "D:\\Musik\\25.08.2015" : "";
		// *** PFAD JULIAN ***//

		FileListController fileListC = new FileListController();
		fileListC.show(path, host, api);

	}
}
