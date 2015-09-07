package controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Timer;
import java.util.TimerTask;

import ch.judos.generic.network.udp.Udp4Forwarded;
import ch.judos.generic.network.udp.UdpLib;
import ch.judos.generic.network.udp.interfaces.Udp4I;
import sun.misc.IOUtils;
import util.URLConnector;
import view.ViewApi;

public class MainController {
	
	private ViewApi api;
	private Timer timer;
	private Udp4Forwarded udp;
	
	public MainController() throws SocketException{
		
		api = new ViewApi();
		
		
		udp = UdpLib.createForwarded();
		api.addShutdownHook(()->{
			udp.dispose();
			});
		
		createRegisterHostTimer();
		
		api.addShutdownHook(() -> {
			timer.cancel();
		});
		HostListController hostListC = new HostListController(api);
		hostListC.show();
		
		
		
	}

	private void registerHost() {
		
		
		String name = System.getProperty("user.name");
		int port = udp.getExternalPort();
		
		InputStream inputStream = 
				URLConnector.getContent("http://www.however.ch/estream/registerHost.php?"
				+ "name=" + name
				+ "&port=" + port);

		
	}

	private void createRegisterHostTimer() {
		
		timer = new Timer();
		TimerTask task = new TimerTask(){
			@Override
			public void run(){
				registerHost();
			}
		};
		timer.schedule(task, 0l, 1000*10);
		
	}

	
}