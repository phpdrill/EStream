package view.data;

import javax.swing.tree.DefaultMutableTreeNode;

import model.Host;

/**
 * @since 14.09.2015
 * @author Julian Schelker
 */
public class UserTreeNode extends DefaultMutableTreeNode {

	private static final long serialVersionUID = -3613479970446521200L;
	protected Host host;

	public UserTreeNode(Host h) {
		super(h);
		this.host = h;
	}

	public Host getHost() {
		return this.host;
	}

	@Override
	public String toString() {
		return this.host.toString();
	}
}
