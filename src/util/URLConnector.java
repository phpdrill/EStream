package util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLConnector {
	
	public static URLConnection call(String url){
		
		try {
			
		    URL myURL = new URL(url);
		    URLConnection connection = myURL.openConnection();
		    connection.connect();
		    return connection;

		}catch (MalformedURLException e) {
			e.printStackTrace();
		    // new URL() failed
		    // ...
		} 
		catch (IOException e) { 
			e.printStackTrace();
		    // openConnection() failed
		    // ...
		}
		return null;
		
	}
	public static InputStream getContent(String url) {
		
		try {
			
		    URLConnection connection = call(url);
			return connection.getInputStream();

		} 
		catch (MalformedURLException e) {
			e.printStackTrace();
		    // new URL() failed
		    // ...
		} 
		catch (IOException e) { 
			e.printStackTrace();
		    // openConnection() failed
		    // ...
		}

		
		return null;
	}

}
