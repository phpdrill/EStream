package view.data;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import model.Document;
import view.design.CompoundTreeNode;
import view.design.Constants;
import view.design.ImagePanel;
import ch.judos.generic.data.format.ByteData;

/**
 * @since 14.09.2015
 * @author Julian Schelker
 */
public class DocumentTreeNode extends CompoundTreeNode {

	private static final long serialVersionUID = 1896838276354149957L;

	private Document document;

	public DocumentTreeNode(Document h) {
		super(h);
		this.document = h;
		setupContent();
	}

	private void setupContent() {
		ImagePanel pic = new ImagePanel(Constants.fileIcon);
		pic.setPreferredSize(new Dimension(25, 25));
		this.container.add(pic);
		JLabel title = new JLabel(this.document.getName());
		title.setFont(Constants.cellFont);
		this.container.add(title);
		JLabel size = new JLabel("(" + ByteData.autoFormat(this.document.getByteSize()) + ")");
		size.setForeground(Color.blue);
		size.setFont(Constants.cellFont);
		this.container.add(size);
		JButton config = new JButton(new ImageIcon(Constants.configIcon));
		config.setPressedIcon(new ImageIcon(Constants.configIconPressed));
		config.setPreferredSize(new Dimension(25, 25));
		config.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(e);
			}
		});
		// config.setOpaque(false);
		// config.setContentAreaFilled(false);
		// config.setBorderPainted(false);
		this.container.add(config);
	}

	public Document getDocument() {
		return this.document;
	}

	@Override
	public String toString() {
		return this.document.toString();
	}
}
