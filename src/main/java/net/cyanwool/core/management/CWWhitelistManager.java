package net.cyanwool.core.management;

import net.cyanwool.api.Server;
import net.cyanwool.api.management.WhitelistManager;

public class CWWhitelistManager extends CWUserManager implements WhitelistManager {

	public CWWhitelistManager(Server server) {
		super(server);
	}

}
