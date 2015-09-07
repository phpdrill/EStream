package controller;

import java.net.InetSocketAddress;

import ch.judos.generic.network.udp.interfaces.UdpListener;

public class ReceiveObjectListener implements UdpListener {

	@Override
	public void receiveMsg(Object source, InetSocketAddress from, Object data) {
		System.out.println("Received request ");
		
	}

}
