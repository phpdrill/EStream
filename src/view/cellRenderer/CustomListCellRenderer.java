package view.cellRenderer;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import view.design.Constants;

/**
 * @since 07.09.2015
 * @author Julian Schelker
 */
public class CustomListCellRenderer implements ListCellRenderer<Object> {

	@Override
	public Component getListCellRendererComponent(JList<? extends Object> list, Object value,
		int index, boolean isSelected, boolean cellHasFocus) {
		JPanel panel = new JPanel();
		JLabel label = new JLabel(value.toString());
		label.setFont(Constants.cellFont);
		panel.add(label);
		if (cellHasFocus) {
			panel.setBackground(Color.lightGray);
		}
		else {
			panel.setBackground(new Color(0, 0, 0, 0));
		}
		return panel;
	}

}
