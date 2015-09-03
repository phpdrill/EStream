package model;

public class Host {

	private String name = "";
	private String externalIp = "";
	private String externalPort = "";

	public Host(String name, String externalIp, String externalPort) {

		this.name = name;
		this.externalIp = externalIp;
		this.externalPort = externalPort;

	}

	@Override
	public String toString() {
		return this.name + " (" + this.externalIp + ":" + this.externalPort + ")";
	}

	public String getName() {
		return this.name;
	}
}
