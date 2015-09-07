package controller;

import java.net.SocketException;
import java.util.Timer;
import java.util.TimerTask;

import ch.judos.generic.network.udp.Udp4Forwarded;
import util.URLConnector;
import view.api.ViewApi;

public class MainController implements UdpLibControllerCallback {

	private ViewApi api;
	private Timer timer;
	private Udp4Forwarded udp;
	private UdpLibController udpLibController;
	private HostListController hostListC;

	public MainController() throws SocketException {

		api = new ViewApi();

		udpLibController = new UdpLibController(this);
		Thread thread = new Thread(udpLibController);
		thread.start();

		api.addShutdownHook(() -> {
			Udp4Forwarded udp = getUdp();
			if (udp == null)
				return;
			try {
				udp.dispose();
			}
			catch (Exception e) {
				e.printStackTrace();
			}

		});

		hostListC = new HostListController(api);
		api.addShutdownHook(() ->

		{
			timer.cancel();
		});

		hostListC = new HostListController(api);

	}

	private Udp4Forwarded getUdp() {
		return udpLibController.getUdp();
	}

	private void registerHost() {

		String name = System.getProperty("user.name");
		int port = udp.getExternalPort();

		URLConnector.getContent("http://www.however.ch/estream/registerHost.php?" + "name="
			+ name + "&port=" + port);

	}

	private void createRegisterHostTimer() {

		timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				registerHost();
				hostListC.show();
			}
		};
		timer.schedule(task, 0l, 1000 * 10);

	}

	@Override
	public void udpForwardCreated(Udp4Forwarded udp) {

		this.udp = udp;
		FileListController fileListC = new FileListController(api);
		this.udp.addObjectListener(new ReceiveObjectListener(udp, hostListC, fileListC));
		hostListC.setUdp(udp);
		hostListC.show();
		createRegisterHostTimer();

	}

}