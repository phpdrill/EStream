package view.listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.BiConsumer;

import javax.swing.JTree;
import javax.swing.tree.TreePath;

import model.Document;
import model.Host;
import view.data.DocumentTreeNode;
import view.data.UserTreeNode;

/**
 * @since 15.09.2015
 * @author Julian Schelker
 */
public class TreeMouseListener extends MouseAdapter {

	private BiConsumer<Host, Document> listener;

	public TreeMouseListener(BiConsumer<Host, Document> documentSelectedListener) {
		this.listener = documentSelectedListener;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			JTree tree = (JTree) e.getSource();
			TreePath selected = tree.getSelectionPath();
			Host h = null;
			Document d = null;
			for (Object o : selected.getPath()) {
				if (o instanceof UserTreeNode) {
					h = ((UserTreeNode) o).getHost();
				}
				if (o instanceof DocumentTreeNode) {
					d = ((DocumentTreeNode) o).getDocument();
				}
			}
			if (h != null && d != null)
				this.listener.accept(h, d);
		}
	}

}
