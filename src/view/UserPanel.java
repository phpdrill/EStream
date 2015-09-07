package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import model.Document;
import view.cellRenderer.CustomListCellRenderer;
import view.design.StandardGridBagLayoutConstraints;

/**
 * @since 03.09.2015
 * @author Julian Schelker
 */
public class UserPanel extends JPanel {

	private static final long serialVersionUID = -8084185563634502507L;
	private JList<Document> list;

	public UserPanel() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new StandardGridBagLayoutConstraints();

		this.list = new JList<Document>();
		this.list.setBorder(new LineBorder(Color.gray));
		this.list.setCellRenderer(new CustomListCellRenderer());
		this.add(this.list, c);
	}

	public void setFileList(List<Document> files) {
		this.list.setListData(files.toArray(new Document[]{}));
	}

}
