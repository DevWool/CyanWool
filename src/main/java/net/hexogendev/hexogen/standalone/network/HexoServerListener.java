package net.hexogendev.hexogen.standalone.network;

import org.spacehq.mc.auth.data.GameProfile;
import org.spacehq.mc.protocol.MinecraftConstants;
import org.spacehq.mc.protocol.MinecraftProtocol;
import org.spacehq.mc.protocol.data.SubProtocol;
import org.spacehq.packetlib.event.server.ServerAdapter;
import org.spacehq.packetlib.event.server.SessionAddedEvent;
import org.spacehq.packetlib.event.server.SessionRemovedEvent;

import net.hexogendev.hexogen.api.Server;
import net.hexogendev.hexogen.api.entity.alive.player.Player;
import net.hexogendev.hexogen.standalone.network.handlers.PlaySessionAdapter;

public class HexoServerListener extends ServerAdapter {

	private Server server;

	public HexoServerListener(Server server) {
		this.server = server;
	}

	@Override
	public void sessionAdded(SessionAddedEvent event) {
		event.getSession().addListener(new PlaySessionAdapter(server));
	}

	@Override
	public void sessionRemoved(SessionRemovedEvent event) {
		// FOR TODO: Customize leave message
		if (((MinecraftProtocol) event.getSession().getPacketProtocol()).getSubProtocol() == SubProtocol.GAME) {
			GameProfile profile = event.getSession().getFlag(MinecraftConstants.PROFILE_KEY);
			Player player = server.getPlayerManager().getPlayer(profile.getName());
			server.getPlayerManager().onLeavePlayer(player);
		}
	}
}
