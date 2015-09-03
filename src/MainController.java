import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainController {
	public MainController(){
		
		HostListController hostListC = new HostListController();
		hostListC.show();
		
		
	}

	
}