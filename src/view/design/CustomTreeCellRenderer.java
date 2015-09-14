package view.design;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

import model.Document;
import model.Host;
import view.data.DocumentTreeNode;
import view.data.UserTreeNode;
import ch.judos.generic.data.format.ByteData;

/**
 * @since 14.09.2015
 * @author Julian Schelker
 */
public class CustomTreeCellRenderer implements TreeCellRenderer {

	private static final Color backgroundSelectionColor = new Color(192, 192, 192);
	private static final Color backgroundNonSelectionColor = new Color(0, 0, 0, 0);

	DefaultTreeCellRenderer defaultRenderer = new DefaultTreeCellRenderer();
	private BufferedImage personIcon;
	private BufferedImage fileIcon;
	private BufferedImage configIcon;
	private BufferedImage configIconPressed;

	public CustomTreeCellRenderer() {
		try {
			this.personIcon = ImageIO.read(new File("data/personIcon.png"));
			this.fileIcon = ImageIO.read(new File("data/fileIcon.png"));
			this.configIcon = ImageIO.read(new File("data/configIcon.png"));
			this.configIconPressed = ImageIO.read(new File("data/configIconPressed.png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected,
		boolean expanded, boolean leaf, int row, boolean hasFocus) {
		Component returnValue = null;
		if ((value != null) && (value instanceof DefaultMutableTreeNode)) {
			JPanel result = new JPanel();
			if (value instanceof UserTreeNode) {
				Host host = ((UserTreeNode) value).getHost();
				renderUserTreeNode(result, host);
			}
			else if (value instanceof DocumentTreeNode) {
				Document d = ((DocumentTreeNode) value).getDocument();
				renderDocumentTreeNode(result, d);
			}
			else {

			}
			if (selected) {
				result.setBackground(backgroundSelectionColor);
			}
			else {
				result.setBackground(backgroundNonSelectionColor);
			}
			result.setEnabled(tree.isEnabled());
			returnValue = result;
		}
		if (returnValue == null) {
			returnValue = defaultRenderer.getTreeCellRendererComponent(tree, value, selected,
				expanded, leaf, row, hasFocus);
		}
		return returnValue;
	}

	private void renderDocumentTreeNode(JPanel result, Document d) {
		ImagePanel pic = new ImagePanel(this.fileIcon);
		pic.setPreferredSize(new Dimension(25, 25));
		result.add(pic);
		JLabel title = new JLabel(d.getName());
		title.setFont(Constants.cellFont);
		result.add(title);
		JLabel size = new JLabel("(" + ByteData.autoFormat(d.getByteSize()) + ")");
		size.setForeground(Color.blue);
		size.setFont(Constants.cellFont);
		result.add(size);
		JButton config = new JButton(new ImageIcon(this.configIcon));
		config.setPressedIcon(new ImageIcon(this.configIconPressed));
		config.setPreferredSize(new Dimension(25, 25));
		config.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(e);
			}
		});
		// config.setOpaque(false);
		// config.setContentAreaFilled(false);
		// config.setBorderPainted(false);
		result.add(config);
	}

	private void renderUserTreeNode(JPanel result, Host host) {
		// JLabel pic = new JLabel(new ImageIcon(this.personIcon));
		// pic.setPreferredSize(new Dimension(30, 30));
		// result.add(pic);
		ImagePanel pic = new ImagePanel(this.personIcon);
		pic.setPreferredSize(new Dimension(25, 25));
		result.add(pic);
		JLabel label = new JLabel(host.toString());
		label.setFont(Constants.cellFont);
		result.add(label);
	}

}
