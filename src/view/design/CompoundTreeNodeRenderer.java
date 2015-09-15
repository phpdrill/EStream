package view.design;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

/**
 * @since 15.09.2015
 * @author Julian Schelker
 */
public class CompoundTreeNodeRenderer implements TreeCellRenderer {

	private static final Color backgroundSelectionColor = new Color(192, 192, 192);
	private static final Color backgroundNonSelectionColor = new Color(0, 0, 0, 0);

	DefaultTreeCellRenderer defaultRenderer = new DefaultTreeCellRenderer();

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected,
		boolean expanded, boolean leaf, int row, boolean hasFocus) {
		Component returnValue = null;
		if ((value != null) && (value instanceof CompoundTreeNode)) {
			JPanel result = ((CompoundTreeNode) value).getContainer();
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

}
