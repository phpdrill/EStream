import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import model.HostList;
import view.ViewApi;

public class HostListController {
	
	private HostList hostList = null;
	public void show(){
		
		if(hostList == null) get();
		
		ViewApi api = new ViewApi();
		api.setListOfHosts(hostList);
		
	}
	
	private void get(){
		try{
			InputStream content = URLConnector.getContent("http://www.however.ch/estream/getHostList.php");
			HostList list = HostList.getFromString(content);
		}catch(ParserConfigurationException exception){
			exception.printStackTrace();
		}catch(SAXException exception){
			exception.printStackTrace();
		}catch(IOException exception){
			exception.printStackTrace();
		}
	}
}
