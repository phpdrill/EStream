package controller;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;

import model.Host;
import model.HostList;
import model.packets.RequestDocumentList;

import org.xml.sax.SAXException;

import ch.judos.generic.data.SerializerException;
import ch.judos.generic.network.udp.Udp4Forwarded;
import util.URLConnector;
import view.SelectionListener;
import view.ViewApi;

public class HostListController implements SelectionListener<Host> {

	private HostList hostList = null;
	private ViewApi api;
	private Udp4Forwarded udp;
	public HostListController(ViewApi api) {
		this.api = api;
	}

	public void show() {

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

		if(udp==null) return;
		
		try {
			udp.sendObjectConfirmTo(new RequestDocumentList(), true, host.getInetSocketAddress());
		} catch (SerializerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// *** PFAD ALESSIO ***//
		/*String path = host.getName().equals("Alessio") ? "D:\\Musik\\25.08.2015" : "D:\\Musik\\gut";

		FileListController fileListC = new FileListController();
		fileListC.show(path, host, api);*/

	}

	public void setUdp(Udp4Forwarded udp) {
		
		this.udp = udp;
		
	}
}
