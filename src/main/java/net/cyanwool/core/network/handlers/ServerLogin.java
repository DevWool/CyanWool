package net.cyanwool.core.network.handlers;

import net.cyanwool.api.Server;

import org.spacehq.mc.auth.GameProfile;
import org.spacehq.mc.protocol.ProtocolConstants;
import org.spacehq.mc.protocol.ServerLoginHandler;
import org.spacehq.packetlib.Session;

public class ServerLogin implements ServerLoginHandler {

	private Server server;

	public ServerLogin(Server server) {
		this.server = server;
	}

	@Override
	public void loggedIn(Session session) {
		GameProfile profile = session.getFlag(ProtocolConstants.PROFILE_KEY);
		server.getPlayerManager().joinPlayer(profile);
	}

}
