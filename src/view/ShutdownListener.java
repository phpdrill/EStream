package view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * @since 03.09.2015
 * @author Julian Schelker
 */
public class ShutdownListener extends WindowAdapter {
	private ArrayList<Runnable> shutdownListener;

	public ShutdownListener() {
		this.shutdownListener = new ArrayList<Runnable>();
	}

	public void addListener(Runnable r) {
		this.shutdownListener.add(r);
	}

	@Override
	public void windowClosing(WindowEvent e) {
		for (Runnable listener : this.shutdownListener) {
			listener.run();
		}
	}
}
