package view.design;

import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * @since 07.09.2015
 * @author Julian Schelker
 */
public class StandardGridBagLayoutConstraints extends GridBagConstraints {

	private static final long serialVersionUID = -4711533195892420559L;

	public StandardGridBagLayoutConstraints() {
		super();
		gridx = gridy = 0;
		weightx = weighty = 1;
		fill = BOTH;
		insets = new Insets(3, 5, 3, 5);
	}

}
