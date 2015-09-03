package view;

import java.util.List;

import javax.swing.JList;
import javax.swing.JPanel;

import model.Document;

/**
 * @since 03.09.2015
 * @author Julian Schelker
 */
public class UserPanel extends JPanel {

	private static final long serialVersionUID = -8084185563634502507L;
	private JList<Document> list;

	public UserPanel() {
		this.list = new JList<Document>();
		this.add(this.list);
	}

	public void setFileList(List<Document> files) {
		this.list.setListData(files.toArray(new Document[]{}));
	}

}
