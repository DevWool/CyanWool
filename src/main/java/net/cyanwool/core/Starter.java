package net.cyanwool.core;

import net.cyanwool.api.CyanWool;

public class Starter {

	public static void main(String[] args) {
		CyanServer server = new CyanServer();
		CyanWool.initServer(server);
	}

}
