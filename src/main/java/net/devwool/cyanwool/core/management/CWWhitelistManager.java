package net.devwool.cyanwool.core.management;

import net.devwool.cyanwool.api.Server;
import net.devwool.cyanwool.api.management.WhitelistManager;

public class CWWhitelistManager extends CWUserManager implements WhitelistManager {

    public CWWhitelistManager(Server server) {
        super(server);
    }

}