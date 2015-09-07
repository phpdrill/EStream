package view.api;

import model.Document;
import model.Host;

/**
 * @since 07.09.2015
 * @author Julian Schelker
 */
public interface FileDownloadSelectionListener {

	public void fileDownloadInitiated(Host fromHost, Document downloadDocument);

}
