package view.listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JList;

import model.Host;
import view.EStreamFrame;
import view.api.SelectionListener;

/**
 * @since 03.09.2015
 * @author Julian Schelker
 */
public class UserListSelectionListener extends MouseAdapter {

	public SelectionListener<Host> selectionListener;
	private EStreamFrame frame;
	private JList<Host> userList;

	public UserListSelectionListener(JList<Host> userList, EStreamFrame eStreamFrame) {
		this.frame = eStreamFrame;
		this.userList = userList;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Host selected = this.userList.getSelectedValue();
		if (e.getClickCount() == 2) {
			this.frame.openOrShowTabForUser(selected);
			if (this.selectionListener != null)
				this.selectionListener.selected(selected);
		}
	}

}
