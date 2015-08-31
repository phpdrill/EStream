package tests;

import ch.judos.generic.network.udp.Udp4Forwarded;
import ch.judos.generic.network.udp.UdpLib;

/**
 * @since 31.08.2015
 * @author Julian Schelker
 */
public class TestGenericUpnpForwarding {
	public static void main(String[] args) throws Exception {
		new TestGenericUpnpForwarding();
	}

	public TestGenericUpnpForwarding() throws Exception {
		Udp4Forwarded lib = UdpLib.createForwarded();

		synchronized (this) {
			this.wait(1000);
		}
		lib.dispose();
	}
}
