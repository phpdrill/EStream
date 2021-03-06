package view.api;

import java.util.List;

import model.Document;
import model.Host;
import view.EStreamFrame;
import view.ShutdownListener;
import view.design.Constants;

/**
 * @since 03.09.2015
 * @author Julian Schelker
 */
public class ViewApi {
	protected ShutdownListener shutdownListener;
	private EStreamFrame frame;

	public ViewApi() {
		Constants.load();
		this.shutdownListener = new ShutdownListener();
		this.frame = new EStreamFrame(this.shutdownListener);
	}

	public void setListOfHosts(List<Host> hosts) {
		this.frame.setListData(hosts.toArray(new Host[]{}));
	}

	public void setSelectionListener(SelectionListener<Host> selectionListener) {
		this.frame.setSelectionListener(selectionListener);
	}

	public void
		setFileDownloadSelectionListener(FileDownloadSelectionListener downloadListener) {
		this.frame.setFileDownloadListener(downloadListener);
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
