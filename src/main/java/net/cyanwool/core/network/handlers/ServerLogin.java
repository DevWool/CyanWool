package net.cyanwool.core.network.handlers;

import org.spacehq.mc.auth.data.GameProfile;
import org.spacehq.mc.protocol.MinecraftConstants;
import org.spacehq.mc.protocol.ServerLoginHandler;
import org.spacehq.mc.protocol.data.game.values.entity.player.GameMode;
import org.spacehq.mc.protocol.data.game.values.setting.Difficulty;
import org.spacehq.mc.protocol.data.game.values.world.WorldType;
import org.spacehq.mc.protocol.packet.ingame.server.ServerJoinGamePacket;
import org.spacehq.packetlib.Session;

import net.cyanwool.api.Server;

public class ServerLogin implements ServerLoginHandler {

	private Server server;

	public ServerLogin(Server server) {
		this.server = server;
	}

	@Override
	public void loggedIn(Session session) {
		GameProfile profile = session.getFlag(MinecraftConstants.PROFILE_KEY);
		// server.getPlayerManager().joinPlayer(profile);
		session.send(new ServerJoinGamePacket(0, false, GameMode.SURVIVAL, 0, Difficulty.PEACEFUL, 10, WorldType.DEFAULT, false));
	}

}
