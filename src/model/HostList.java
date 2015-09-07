package model;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class HostList extends ArrayList<Host> {

	public static HostList getFromString(InputStream content)
		throws ParserConfigurationException, SAXException, IOException {

		if (content == null)
			return null;

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(content);

		doc.getDocumentElement().normalize();

		NodeList nList = doc.getElementsByTagName("host");
		HostList hostList = new HostList();

		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;

				String name = eElement.getElementsByTagName("name").item(0).getTextContent();
				String ip = eElement.getElementsByTagName("ip").item(0).getTextContent();
				String port = eElement.getElementsByTagName("port").item(0).getTextContent();

				hostList.add(new Host(name, ip, port));

			}
		}

		return hostList;
	}

	private HashMap<InetSocketAddress, Host> hashMap = new HashMap<InetSocketAddress, Host>();

	@Override
	public boolean add(Host e) {
		hashMap.put(e.getInetSocketAddress(), e);
		return super.add(e);
	}

	public Host get(InetSocketAddress address) {
		return hashMap.containsKey(address) ? hashMap.get(address) : null;
	}
}
