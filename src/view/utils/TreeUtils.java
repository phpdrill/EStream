package view.utils;

import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

/**
 * @since 14.09.2015
 * @author Julian Schelker
 */
public class TreeUtils {
	public static void removeAllChildren(DefaultTreeModel model, TreeNode node) {
		Enumeration<?> e = node.children();
		while (e.hasMoreElements()) {
			DefaultMutableTreeNode n = (DefaultMutableTreeNode) e.nextElement();
			model.removeNodeFromParent(n);
		}
	}

	public static void removeAllChildren(DefaultTreeModel model, DefaultMutableTreeNode node,
		Class<?> clazz) {
		int amount = model.getChildCount(node);
		for (int i = amount - 1; i >= 0; i--) {
			DefaultMutableTreeNode n = (DefaultMutableTreeNode) model.getChild(node, i);
			Object userObject = n.getUserObject();
			if (userObject != null && userObject.getClass() == clazz)
				model.removeNodeFromParent(n);
		}
	}
}
