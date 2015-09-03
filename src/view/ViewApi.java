package view;

import java.util.List;

import model.Host;
import ch.judos.generic.data.DynamicList;

/**
 * @since 03.09.2015
 * @author Julian Schelker
 */
public class ViewApi {
	protected ShutdownListener shutdownListener;
	private EStreamFrame frame;

	public static void main(String[] args) {
		DynamicList<Host> list = new DynamicList<Host>(new Host("Julian", "127.0.0.1", "1234"));
		ViewApi api = new ViewApi();
		api.setListOfHosts(list);
	}

	public ViewApi() {
		this.shutdownListener = new ShutdownListener();
		this.frame = new EStreamFrame(this.shutdownListener);
	}

	public void setListOfHosts(List<Host> hosts) {
		this.frame.userList.setListData(hosts.toArray(new Host[]{}));
	}
	/**
	 * the passed Runnable object gets executed when the window is closed
	 * 
	 * @param r
	 */
	public void addShutdownHook(Runnable r) {
		this.shutdownListener.addListener(r);
	}
}
