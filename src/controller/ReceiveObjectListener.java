package controller;

import java.io.IOException;
import java.net.InetSocketAddress;

import ch.judos.generic.data.SerializerException;
import ch.judos.generic.network.udp.Udp4Forwarded;
import ch.judos.generic.network.udp.interfaces.UdpListener;
import model.DocumentList;
import model.HostList;
import model.packets.RequestDocumentList;



public class ReceiveObjectListener implements UdpListener {

	private Udp4Forwarded udp;
	private HostListController hostListC;
	private FileListController fileListC;

	public ReceiveObjectListener(Udp4Forwarded udp, HostListController hostListC, FileListController fileListC) {
		this.udp = udp;
		this.hostListC = hostListC;
		this.fileListC = fileListC;
	}

	@Override
	public void receiveMsg(Object source, InetSocketAddress from, Object data) {
		System.out.println("Received request ");
		
		if(data instanceof RequestDocumentList){
			
			sendFileList(from);
			
		}else if(data instanceof DocumentList){
			
			showFileList((DocumentList)data, from);
			
		}
	
		
	}

	private void showFileList(DocumentList list, InetSocketAddress from) {
		
		HostList hostList = hostListC.getHostList();
		
		
		fileListC.show(list, hostList.get(from));
		
	}

	private void sendFileList(InetSocketAddress from) {

		// *** PFAD ALESSIO ***//
		String path = from.getAddress().toString() == "85.2.139.222" ? 
				"D:\\Musik\\25.08.2015" : "D:\\Musik\\gut";
		
		DocumentList list = DocumentList.get(path);
		
		try {
			udp.sendObjectConfirmTo(list, true, from);
		} catch (SerializerException | IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
