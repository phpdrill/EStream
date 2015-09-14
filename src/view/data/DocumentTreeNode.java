package view.data;

import javax.swing.tree.DefaultMutableTreeNode;

import model.Document;

/**
 * @since 14.09.2015
 * @author Julian Schelker
 */
public class DocumentTreeNode extends DefaultMutableTreeNode {

	private static final long serialVersionUID = 1896838276354149957L;

	private Document document;

	public DocumentTreeNode(Document h) {
		super(h);
		this.document = h;
	}

	public Document getDocument() {
		return this.document;
	}

	@Override
	public String toString() {
		return this.document.toString();
	}
}
