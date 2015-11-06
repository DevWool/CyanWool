package net.hexogendev.hexogen.standalone.network.handlers;

import java.awt.image.BufferedImage;

import org.spacehq.mc.auth.data.GameProfile;
import org.spacehq.mc.protocol.MinecraftConstants;
import org.spacehq.mc.protocol.data.message.Message;
import org.spacehq.mc.protocol.data.message.TextMessage;
import org.spacehq.mc.protocol.data.status.PlayerInfo;
import org.spacehq.mc.protocol.data.status.ServerStatusInfo;
import org.spacehq.mc.protocol.data.status.VersionInfo;
import org.spacehq.mc.protocol.data.status.handler.ServerInfoBuilder;
import org.spacehq.packetlib.Session;

import net.hexogendev.hexogen.api.Server;
import net.hexogendev.hexogen.api.utils.ChatColor;

public class ServerInfo implements ServerInfoBuilder {

	private Server server;

	public ServerInfo(Server server) {
		this.server = server;
	}

	@Override
	public ServerStatusInfo buildInfo(Session session) {
		VersionInfo version = new VersionInfo(MinecraftConstants.GAME_VERSION, MinecraftConstants.PROTOCOL_VERSION);
		PlayerInfo player = new PlayerInfo(server.getServerConfiguration().getMaxPlayers(), server.getPlayerManager().getPlayers().size(), new GameProfile[0]);

		String text = ChatColor.translateAlternateColorCodes('&', server.getServerConfiguration().getMotd());
		Message message = new TextMessage(text);

		BufferedImage icon = server.getServerConfiguration().getIcon();
		return new ServerStatusInfo(version, player, message, icon);
	}

}
