package net.hexogendev.hexogen.standalone.management;

import net.hexogendev.hexogen.api.Server;
import net.hexogendev.hexogen.api.management.WhitelistManager;

public class HexoWhitelistManager extends HexoUserManager implements WhitelistManager {

	public HexoWhitelistManager(Server server) {
		super(server);
	}

}
