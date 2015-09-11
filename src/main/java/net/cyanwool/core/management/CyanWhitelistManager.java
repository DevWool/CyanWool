package net.cyanwool.core.management;

import net.cyanwool.api.Server;
import net.cyanwool.api.management.WhitelistManager;

public class CyanWhitelistManager extends CyanUserManager implements WhitelistManager {

	public CyanWhitelistManager(Server server) {
		super(server);
	}

}
