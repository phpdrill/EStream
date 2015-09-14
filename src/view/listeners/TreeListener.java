package view.listeners;

import java.util.function.Consumer;

import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import model.Host;
import view.data.UserTreeNode;

/**
 * @since 14.09.2015
 * @author Julian Schelker
 */
public class TreeListener implements TreeExpansionListener {

	private Consumer<Host> selectionAction;

	public TreeListener(Consumer<Host> hostSelection) {
		this.selectionAction = hostSelection;
	}

	@Override
	public void treeExpanded(TreeExpansionEvent event) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) event.getPath()
			.getLastPathComponent();
		if (node instanceof UserTreeNode) {
			UserTreeNode userNode = (UserTreeNode) node;
			Host h = userNode.getHost();
			this.selectionAction.accept(h);
		}
		else if (!(node instanceof DefaultMutableTreeNode)) {
			System.out.println("Warning: " + node + " selected but no listener action");
		}
	}
	@Override
	public void treeCollapsed(TreeExpansionEvent event) {

	}

}
