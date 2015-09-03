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

public class MainController {
	public MainController(){
		
		try {
			registerHost();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*HostListController hostListC = new HostListController();
		hostListC.show();*/
		
		
	}

	private void registerHost() throws IOException {
		
		String name = System.getProperty("user.name");
		String ip = null;
		String port = null;
		
		InputStream inputStream = 
				URLConnector.getContent("http://www.however.ch/estream/registerHost.php?"
				+ "name=" + name
				+ "&ip=" + ip
				+ "&port=" + port);
		
		StringBuilder inputStringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        String line = bufferedReader.readLine();
        while(line != null){
            inputStringBuilder.append(line);inputStringBuilder.append('\n');
            line = bufferedReader.readLine();
        }
        System.out.println("Content: "+ inputStringBuilder.toString());

		
	}

	
}