import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainController {
	public MainController(){
		
		registerHost();
		
		HostListController hostListC = new HostListController();
		hostListC.show();
		
		
	}

	private void registerHost() {
		
		String name = System.getProperty("user.name");
		
		URLConnector.call("http://www.however.ch/estream/registerHost.php?"
				+ "name=" + name + "");
		
	}

	
}