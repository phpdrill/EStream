import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;

import ch.judos.generic.data.SerializerException;
import ch.judos.generic.network.udp.ConnectionIssueListener;
import ch.judos.generic.network.udp.UdpLib;
import ch.judos.generic.network.udp.interfaces.Udp4I;
import ch.judos.generic.network.udp.interfaces.UdpFileTransferListener;
import ch.judos.generic.network.udp.interfaces.UdpListener;
import controller.MainController;

public class Launcher implements UdpFileTransferListener, ConnectionIssueListener, UdpListener {
	public static void main(String[] args) throws SerializerException, IOException {

		/**** HARDCODED PROPERTIES ****/
		MainController controller = new MainController();
		
	}

	@Override
	public int getUpdateEveryMS() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void transmissionAcceptedAndStarted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void transmissionCompleted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void transmissionDeniedAndCanceled() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void transmissionProgress(float percentage, float avgSpeed, long transmitted, long total) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void connectionIsBroken(InetSocketAddress destination) {
		// TODO Auto-generated method stub
		System.out.println("connection is broken: " + destination.toString());
	}

	@Override
	public void connectionReconnected(InetSocketAddress from) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receiveMsg(Object source, InetSocketAddress from, Object data) {
		// TODO Auto-generated method stub
		System.out.println("receive msg");
	}
	
	
}
