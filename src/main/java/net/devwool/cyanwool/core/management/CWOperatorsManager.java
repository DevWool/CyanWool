package net.devwool.cyanwool.core.management;

import net.devwool.cyanwool.api.Server;
import net.devwool.cyanwool.api.management.OperatorsManager;

public class CWOperatorsManager extends CWUserManager implements OperatorsManager {

    public CWOperatorsManager(Server server) {
        super(server);
    }

}