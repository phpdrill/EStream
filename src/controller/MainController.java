package controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import sun.misc.IOUtils;
import util.URLConnector;
import view.ViewApi;

public class MainController {
	
	private ViewApi api;
	
	public MainController(){
		
		api = new ViewApi();
		
		registerHost();
		
		HostListController hostListC = new HostListController(api);
		hostListC.show();
		
		
	}

	private void registerHost() {
		
		String name = System.getProperty("user.name");
		String ip = "blabli";
		String port = "blabla";
		
		InputStream inputStream = 
				URLConnector.getContent("http://www.however.ch/estream/registerHost.php?"
				+ "name=" + name
				+ "&ip=" + ip
				+ "&port=" + port);

		
	}

	
}