package net.cyanwool.core.management;

import net.cyanwool.api.Server;
import net.cyanwool.api.management.OperatorsManager;

public class CWOperatorsManager extends CWUserManager implements OperatorsManager {

    public CWOperatorsManager(Server server) {
        super(server);
    }

}