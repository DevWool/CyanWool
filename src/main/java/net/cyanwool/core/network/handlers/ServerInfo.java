package net.cyanwool.core.network.handlers;

import java.awt.image.BufferedImage;

import net.cyanwool.api.Server;

import org.spacehq.mc.auth.GameProfile;
import org.spacehq.mc.protocol.ProtocolConstants;
import org.spacehq.mc.protocol.data.message.Message;
import org.spacehq.mc.protocol.data.message.TextMessage;
import org.spacehq.mc.protocol.data.status.PlayerInfo;
import org.spacehq.mc.protocol.data.status.ServerStatusInfo;
import org.spacehq.mc.protocol.data.status.VersionInfo;
import org.spacehq.mc.protocol.data.status.handler.ServerInfoBuilder;
import org.spacehq.packetlib.Session;

public class ServerInfo implements ServerInfoBuilder {

	private Server server;

	public ServerInfo(Server server) {
		this.server = server;
	}

	@Override
	public ServerStatusInfo buildInfo(Session session) {
		VersionInfo version = new VersionInfo(ProtocolConstants.GAME_VERSION, ProtocolConstants.PROTOCOL_VERSION);
		PlayerInfo player = new PlayerInfo(server.getServerConfiguration().getMaxPlayers(), server.getPlayerManager().getPlayers().size(), new GameProfile[0]);
		Message message = new TextMessage(server.getServerConfiguration().getMotd());

		BufferedImage icon = server.getIcon();
		return new ServerStatusInfo(version, player, message, icon);
	}

}
