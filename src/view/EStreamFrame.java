package view;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import model.Document;
import model.Host;

/**
 * @since 03.09.2015
 * @author Julian Schelker
 */
public class EStreamFrame extends JFrame {
	private static final long serialVersionUID = 5546962031230294735L;
	public JList<Host> userList;
	JTabbedPane tabbedPane;
	private UserListSelectionListener userListSelectionListener;
	ArrayList<Object> tabs;

	public EStreamFrame(WindowListener windowListener) {
		super("EStream");

		initializeObjects();
		setContent();

		addSelfDisposeListener();
		this.addWindowListener(windowListener);

		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void initializeObjects() {
		this.tabs = new ArrayList<Object>();
		this.tabs.add("UserList");
		this.userListSelectionListener = new UserListSelectionListener(this);
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
		this.tabbedPane = new JTabbedPane();
		this.add(this.tabbedPane);

		this.userList = new JList<Host>();
		this.userList.setPreferredSize(new Dimension(250, 500));
		this.userList.addMouseListener(this.userListSelectionListener);
		this.tabbedPane.addTab("UserList", this.userList);
	}

	public void setSelectionListener(SelectionListener<Host> selectionListener) {
		this.userListSelectionListener.selectionListener = selectionListener;
	}

	public void addTabForHost(Host host) {
		JPanel panel = new UserPanel();
		this.tabbedPane.addTab(host.getName(), panel);
		this.tabs.add(host);
		this.tabbedPane.setSelectedIndex(this.tabbedPane.getTabCount() - 1);
	}

	public void setDocumentListForHost(Host host, List<Document> files) {
		for (int i = 0; i < this.tabbedPane.getTabCount(); i++) {
			Object o = this.tabs.get(i);
			if ((o instanceof Host) && o == host) {
				Object tab = this.tabbedPane.getComponentAt(i);
				if (tab instanceof UserPanel) {
					((UserPanel) tab).setFileList(files);
				}
				else {
					System.out.println("Warning no UseerPanel found for index: " + i);
				}

				return;
			}
		}
	}
}
