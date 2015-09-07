package tests;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.bitlet.weupnp.GatewayDevice;
import org.bitlet.weupnp.GatewayDiscover;
import org.bitlet.weupnp.PortMappingEntry;
import org.xml.sax.SAXException;

/**
 * @since 07.09.2015
 * @author Julian Schelker
 */
public class RemovePortMappings {

	public static void main(String[] args) throws SocketException, UnknownHostException,
		IOException, SAXException, ParserConfigurationException {
		GatewayDiscover gatewayDiscover = new GatewayDiscover();
		Map<InetAddress, GatewayDevice> gateways = gatewayDiscover.discover();

		if (gateways.isEmpty()) {
			addLogLine("No gateways found");
			addLogLine("Stopping weupnp");
			return;
		}
		GatewayDevice activeGW = gatewayDiscover.getValidGateway();
		if (activeGW == null) {
			addLogLine("No active gateway device found");
			addLogLine("Stopping weupnp");
			return;
		}

		PortMappingEntry portMapping = new PortMappingEntry();
		int pmCount = 0;
		do {
			if (activeGW.getGenericPortMappingEntry(pmCount, portMapping)) {
				addLogLine("Portmapping #" + pmCount + " successfully retrieved ("
					+ portMapping.getPortMappingDescription() + ":"
					+ portMapping.getExternalPort() + ")");

				if (activeGW.deletePortMapping(portMapping.getExternalPort(), portMapping
					.getProtocol())) {
					addLogLine("Port mapping removed, test SUCCESSFUL");
				}
				else {
					addLogLine("Port mapping removal FAILED");
				}
			}
			else {
				addLogLine("Portmapping #" + pmCount + " retrieval failed");
				break;
			}
			pmCount++;
		} while (portMapping != null);
	}

	private static void addLogLine(String line) {

		String timeStamp = DateFormat.getTimeInstance().format(new Date());
		String logline = timeStamp + ": " + line + "\n";
		System.out.print(logline);
	}

}
