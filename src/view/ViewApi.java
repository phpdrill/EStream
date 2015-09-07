package view;

import java.util.List;

import model.Document;
import model.Host;

/**
 * @since 03.09.2015
 * @author Julian Schelker
 */
public class ViewApi {
	protected ShutdownListener shutdownListener;
	private EStreamFrame frame;

	public ViewApi() {
		this.shutdownListener = new ShutdownListener();
		this.frame = new EStreamFrame(this.shutdownListener);
	}

	public void setListOfHosts(List<Host> hosts) {
		this.frame.userList.setListData(hosts.toArray(new Host[]{}));
	}

	public void setSelectionListener(SelectionListener<Host> selectionListener) {
		this.frame.setSelectionListener(selectionListener);
	}

	public void setDocumentListForHost(Host host, List<Document> files) {
		this.frame.setDocumentListForHost(host, files);
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
