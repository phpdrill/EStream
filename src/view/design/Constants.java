package view.design;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @since 07.09.2015
 * @author Julian Schelker
 */
public class Constants {

	public static BufferedImage fileIcon;
	public static BufferedImage configIcon;
	public static BufferedImage configIconPressed;
	public static BufferedImage personIcon;
	public static Font cellFont = new Font("Arial", 0, 18);

	public static void load() {
		try {
			personIcon = ImageIO.read(new File("data/personIcon.png"));
			fileIcon = ImageIO.read(new File("data/fileIcon.png"));
			configIcon = ImageIO.read(new File("data/configIcon.png"));
			configIconPressed = ImageIO.read(new File("data/configIconPressed.png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
