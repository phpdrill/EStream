import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLConnector {
	
	public static InputStream getContent(String url) {
		
		try {
		    URL myURL = new URL(url);
		    URLConnection connection = myURL.openConnection();
		    connection.connect();
		    /*BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
			String inputLine;
			StringBuilder stringBuilder = new StringBuilder();
			
			while ((inputLine = in.readLine()) != null) 
				stringBuilder.append(inputLine);
			
			String hostList = stringBuilder.toString();
			in.close();*/
			
			return connection.getInputStream();

		} 
		catch (MalformedURLException e) { 
		    // new URL() failed
		    // ...
		} 
		catch (IOException e) {   
		    // openConnection() failed
		    // ...
		}

		
		return null;
	}

}
