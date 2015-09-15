package view.data;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.JLabel;

import model.Host;
import view.design.CompoundTreeNode;
import view.design.Constants;
import view.design.ImagePanel;

/**
 * @since 14.09.2015
 * @author Julian Schelker
 */
public class UserTreeNode extends CompoundTreeNode {

	private static final long serialVersionUID = -3613479970446521200L;
	protected Host host;

	public UserTreeNode(Host h) {
		super(h);
		this.host = h;
		setupContent();
	}

	private void setupContent() {
		Image icon = Constants.personIcon;
		ImagePanel pic = new ImagePanel(icon);
		pic.setPreferredSize(new Dimension(25, 25));
		this.container.add(pic);
		JLabel label = new JLabel(host.toString());
		label.setFont(Constants.cellFont);
		this.container.add(label);
	}

	public Host getHost() {
		return this.host;
	}

	@Override
	public String toString() {
		return this.host.toString();
	}
}
