package view.design;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.JPanel;

/**
 * @since 14.09.2015
 * @author Julian Schelker
 */
public class ImagePanel extends JPanel {

	private Image image;
	private static final long serialVersionUID = -6708764175600586422L;

	public ImagePanel(Image srcImage) {
		this.image = srcImage;
	}

	@Override
	protected void paintComponent(Graphics g) {
		Rectangle bounds = g.getClipBounds();
		g.drawImage(this.image, bounds.x, bounds.y, bounds.width, bounds.height, null);
	}
}
