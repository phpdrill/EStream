package view;

import java.awt.Dimension;
import java.awt.event.WindowListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import model.Document;
import model.Host;
import view.api.FileDownloadSelectionListener;
import view.api.SelectionListener;
import view.data.DocumentTreeNode;
import view.data.UserTreeNode;
import view.design.CustomTreeCellRenderer;
import view.listeners.TreeListener;
import view.listeners.TreeMouseListener;
import view.utils.TreeUtils;

/**
 * @since 03.09.2015
 * @author Julian Schelker
 */
public class EStreamFrame extends JFrame {
	private static final long serialVersionUID = 5546962031230294735L;
	JTree documentTree;
	JTabbedPane tabbedPane;
	private DefaultMutableTreeNode documentRootNode;
	private DefaultTreeModel documentTreeModel;
	private HashMap<Host, DefaultMutableTreeNode> treeNodeHashMap;
	private SelectionListener<Host> selectionListener;
	private FileDownloadSelectionListener fileDownloadListener;

	public EStreamFrame(WindowListener windowListener) {
		super("EStream");

		initializeObjects();
		setContent();

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.addWindowListener(windowListener);

		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void initializeObjects() {
		this.treeNodeHashMap = new HashMap<Host, DefaultMutableTreeNode>();
	}

	private void setContent() {
		this.tabbedPane = new JTabbedPane();
		this.add(this.tabbedPane);

		this.documentRootNode = new DefaultMutableTreeNode("Root");
		this.documentTreeModel = new DefaultTreeModel(this.documentRootNode);
		this.documentTree = new JTree(this.documentTreeModel);
		this.documentTree.setRootVisible(false);
		this.documentTree.setShowsRootHandles(true);
		this.documentTree.addTreeExpansionListener(new TreeListener((Host h) -> {
			if (this.selectionListener != null)
				this.selectionListener.selected(h);
			else
				System.err.println("No selectionListener defined for view");
		}));
		this.documentTree.addMouseListener(new TreeMouseListener((Host h, Document d) -> {
			if (this.fileDownloadListener != null)
				this.fileDownloadListener.fileDownloadInitiated(h, d);
			else
				System.err.println("No fileDownloadListener defined for view!");
		}));
		this.documentTree.setCellRenderer(new CustomTreeCellRenderer());
		// this.documentTree.getSelectionPath()
		JScrollPane scroll = new JScrollPane(this.documentTree);
		scroll.setPreferredSize(new Dimension(300, 500));

		this.tabbedPane.addTab("Document list", scroll);
	}

	public void setSelectionListener(SelectionListener<Host> selectionListener) {
		this.selectionListener = selectionListener;
	}
	public void setDocumentListForHost(Host host, List<Document> files) {
		DefaultMutableTreeNode node = this.treeNodeHashMap.get(host);
		if (node == null) {
			System.err.println("Can't open TreeNode for Host " + host
				+ " which doesn't exist in JTree.");
			return;
		}
		this.documentTreeModel.insertNodeInto(new DefaultMutableTreeNode("..."), node, 0);
		TreeUtils.removeAllChildren(this.documentTreeModel, node, Document.class);
		for (Document h : files) {
			DefaultMutableTreeNode hn = new DocumentTreeNode(h);
			this.documentTreeModel.insertNodeInto(hn, node, 0);
		}
		TreeUtils.removeAllChildren(this.documentTreeModel, node, String.class);
	}

	public void setFileDownloadListener(FileDownloadSelectionListener downloadListener) {
		this.fileDownloadListener = downloadListener;
	}

	public void setListData(Host[] array) {
		TreeUtils.removeAllChildren(documentTreeModel, documentRootNode);
		this.treeNodeHashMap.clear();
		for (Host h : array) {
			DefaultMutableTreeNode hn = new UserTreeNode(h);
			this.treeNodeHashMap.put(h, hn);
			hn.add(new DefaultMutableTreeNode("..."));
			this.documentTreeModel.insertNodeInto(hn, this.documentRootNode, 0);
		}
		this.documentTree.expandPath(new TreePath(this.documentRootNode));
	}
}
