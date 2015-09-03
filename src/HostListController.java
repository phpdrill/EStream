import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import model.HostList;

public class HostListController {
	
	public void show(){
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
