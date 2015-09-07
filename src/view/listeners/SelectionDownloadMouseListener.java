package view.listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JList;

import model.Document;
import model.Host;
import view.api.FileDownloadSelectionListener;

/**
 * @since 07.09.2015
 * @author Julian Schelker
 */
public class SelectionDownloadMouseListener {

	public FileDownloadSelectionListener fileDownloadSelectionListener;

	public MouseListener getMouseListenerForHost(Host host) {
		return new MouseAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JList<Document> list = (JList<Document>) e.getSource();
					Document download = list.getSelectedValue();
					if (fileDownloadSelectionListener != null)
						fileDownloadSelectionListener.fileDownloadInitiated(host, download);
					else {
						System.out.println("trying to download document " + download
							+ " from " + host
							+ ", but no fileDownloadSelectionListener is defined for ViewApi");
					}
				}
			}
		};
	}

}
