package view;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JList;

import model.Host;

/**
 * @since 03.09.2015
 * @author Julian Schelker
 */
public class EStreamFrame extends JFrame {
	private static final long serialVersionUID = 5546962031230294735L;
	public JList<Host> userList;

	public EStreamFrame(WindowListener windowListener) {
		super("EStream");
		setContent();

		addSelfDisposeListener();
		this.addWindowListener(windowListener);

		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void addSelfDisposeListener() {
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				EStreamFrame.this.dispose();
			}
		});
	}

	private void setContent() {
		this.userList = new JList<Host>();
		this.userList.setPreferredSize(new Dimension(250, 500));
		this.add(this.userList);
	}

	public void setSelectionListener(SelectionListener<Host> selectionListener) {
		// for(this.userList.getListSelectionListeners();
	}
}
