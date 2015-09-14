package view.test;

/**
 * @since 14.09.2015
 * @author Julian Schelker
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.Vector;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

class TreeNodeVector<E> extends Vector<E> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9173596255765043465L;
	String name;

	TreeNodeVector(String name) {
		this.name = name;
	}

	TreeNodeVector(String name, E elements[]) {
		this.name = name;
		for (int i = 0, n = elements.length; i < n; i++) {
			add(elements[i]);
		}
	}

	public String toString() {
		return "[" + name + "]";
	}
}

class Employee {
	public String firstName;

	public String lastName;

	public float salary;

	public Employee(String f, String l, float s) {
		this.firstName = f;
		this.lastName = l;
		this.salary = s;
	}

}

class EmployeeCellRenderer implements TreeCellRenderer {
	JLabel firstNameLabel = new JLabel(" ");

	JLabel lastNameLabel = new JLabel(" ");

	JLabel salaryLabel = new JLabel(" ");

	JPanel renderer = new JPanel();

	DefaultTreeCellRenderer defaultRenderer = new DefaultTreeCellRenderer();

	Color backgroundSelectionColor;

	Color backgroundNonSelectionColor;

	public EmployeeCellRenderer() {
		firstNameLabel.setForeground(Color.BLUE);
		renderer.add(firstNameLabel);

		lastNameLabel.setForeground(Color.BLUE);
		renderer.add(lastNameLabel);

		salaryLabel.setHorizontalAlignment(JLabel.RIGHT);
		salaryLabel.setForeground(Color.RED);
		renderer.add(salaryLabel);
		renderer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		backgroundSelectionColor = defaultRenderer.getBackgroundSelectionColor();
		backgroundNonSelectionColor = defaultRenderer.getBackgroundNonSelectionColor();
	}

	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected,
		boolean expanded, boolean leaf, int row, boolean hasFocus) {
		Component returnValue = null;
		if ((value != null) && (value instanceof DefaultMutableTreeNode)) {
			Object userObject = ((DefaultMutableTreeNode) value).getUserObject();
			if (userObject instanceof Employee) {
				Employee e = (Employee) userObject;
				firstNameLabel.setText(e.firstName);
				lastNameLabel.setText(e.lastName);
				salaryLabel.setText("" + e.salary);
				if (selected) {
					renderer.setBackground(backgroundSelectionColor);
				}
				else {
					renderer.setBackground(backgroundNonSelectionColor);
				}
				renderer.setEnabled(tree.isEnabled());
				returnValue = renderer;
			}
		}
		if (returnValue == null) {
			returnValue = defaultRenderer.getTreeCellRendererComponent(tree, value, selected,
				expanded, leaf, row, hasFocus);
		}
		return returnValue;
	}
}

public class EmployeeTree {
	public static void main(String args[]) {
		JFrame frame = new JFrame("Book Tree");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Employee javaBooks[] = {new Employee("A", "F", 9.99f), new Employee("B", "E", 4.99f),
			new Employee("C", "D", 9.95f)};
		Employee netBooks[] = {new Employee("AA", "CC", 9.99f),
			new Employee("BB", "DD", 9.99f)};
		Vector<Employee> javaVector = new TreeNodeVector<Employee>("A", javaBooks);
		Vector<Employee> netVector = new TreeNodeVector<Employee>("As", netBooks);
		Object rootNodes[] = {javaVector, netVector};
		Vector<Object> rootVector = new TreeNodeVector<Object>("Root", rootNodes);
		JTree tree = new JTree(rootVector);
		TreeCellRenderer renderer = new EmployeeCellRenderer();
		tree.setCellRenderer(renderer);
		JScrollPane scrollPane = new JScrollPane(tree);
		frame.add(scrollPane, BorderLayout.CENTER);
		frame.setSize(300, 300);
		frame.setVisible(true);
	}
}