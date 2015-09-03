package view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Host;

/**
 * @since 03.09.2015
 * @author Julian Schelker
 */
public class UserListSelectionListener implements MouseListener {

	public SelectionListener<Host> selectionListener;
	private EStreamFrame frame;

	public UserListSelectionListener(EStreamFrame eStreamFrame) {
		this.frame = eStreamFrame;
	}

	public void valueChanged(Host selected) {
		if (selected == null)
			return;
		for (int i = 0; i < this.frame.tabbedPane.getTabCount(); i++) {
			Object o = this.frame.tabs.get(i);
			if ((o instanceof Host) && o == selected) {
				this.frame.tabbedPane.setSelectedIndex(i);
				if (this.selectionListener != null)
					this.selectionListener.selected((Host) o);
				return;
			}
		}

		this.frame.addTabForHost(selected);
		if (this.selectionListener != null)
			this.selectionListener.selected(selected);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Host selected = this.frame.userList.getSelectedValue();
		if (e.getClickCount() == 2) {
			this.valueChanged(selected);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
