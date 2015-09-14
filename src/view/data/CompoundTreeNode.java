package view.data;

import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * @since 15.09.2015
 * @author Julian Schelker
 */
public class CompoundTreeNode extends DefaultMutableTreeNode {

	private static final long serialVersionUID = -7912075378641178088L;

	protected JPanel container;

	public CompoundTreeNode(Object userData) {
		super(userData);
		this.container = new JPanel();
	}

	public JPanel getContainer() {
		return this.container;
	}
}
