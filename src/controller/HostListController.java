package controller;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import ch.judos.generic.data.SerializerException;
import ch.judos.generic.network.udp.Udp4Forwarded;
import model.Host;
import model.HostList;
import model.packets.RequestDocumentList;
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

	}

	public void setUdp(Udp4Forwarded udp) {
		
		this.udp = udp;
		
	}

	public HostList getHostList() {
		return hostList;
	}
}
