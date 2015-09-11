package net.cyanwool.core.management;

import net.cyanwool.api.Server;
import net.cyanwool.api.management.OperatorsManager;

public class CyanOperatorsManager extends CyanUserManager implements OperatorsManager {

	public CyanOperatorsManager(Server server) {
		super(server);
	}

}
