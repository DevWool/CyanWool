package net.hexogendev.hexogen.standalone;

import net.hexogendev.hexogen.api.Hexogen;

public class Starter {

	public static void main(String[] args) {
		HexoServer server = new HexoServer();
		Hexogen.initServer(server);
	}

}
