package view.cellRenderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagLayout;

import javax.swing.*;

import view.design.Constants;
import view.design.StandardGridBagLayoutConstraints;

/**
 * @since 07.09.2015
 * @author Julian Schelker
 */
public class CustomListCellRenderer implements ListCellRenderer<Object> {

	@Override
	public Component getListCellRendererComponent(JList<? extends Object> list, Object value,
		int index, boolean isSelected, boolean cellHasFocus) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		StandardGridBagLayoutConstraints c = new StandardGridBagLayoutConstraints();
		JLabel label = new JLabel(value.toString());
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(Constants.cellFont);
		panel.add(label, c);
		if (cellHasFocus) {
			panel.setBackground(Color.lightGray);
		}
		else {
			panel.setBackground(new Color(0, 0, 0, 0));
		}
		return panel;
	}

}
