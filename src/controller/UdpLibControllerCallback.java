package controller;

import ch.judos.generic.network.udp.Udp4Forwarded;

public interface UdpLibControllerCallback {

	void udpForwardCreated(Udp4Forwarded udp);
}
