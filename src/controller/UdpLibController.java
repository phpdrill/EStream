package controller;

import java.net.SocketException;

import ch.judos.generic.network.udp.Udp4Forwarded;
import ch.judos.generic.network.udp.UdpLib;

public class UdpLibController implements Runnable {

	private Udp4Forwarded udp;
	private UdpLibControllerCallback callback;
	
	public UdpLibController(UdpLibControllerCallback callback){
		this.callback = callback;
	}
	@Override
	public void run() {
		
		try {
			udp = UdpLib.createForwarded();
			callback.udpForwardCreated();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public Udp4Forwarded getUdp() {
		return udp;
	}

}
