package net.hexogendev.hexogen.standalone.management;

import net.hexogendev.hexogen.api.Server;
import net.hexogendev.hexogen.api.management.OperatorsManager;

public class HexoOperatorsManager extends HexoUserManager implements OperatorsManager {

	public HexoOperatorsManager(Server server) {
		super(server);
	}

}
