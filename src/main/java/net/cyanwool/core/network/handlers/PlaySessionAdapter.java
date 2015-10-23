package net.cyanwool.core.network.handlers;

import org.spacehq.mc.auth.data.GameProfile;
import org.spacehq.mc.protocol.MinecraftConstants;
import org.spacehq.mc.protocol.packet.ingame.client.ClientSettingsPacket;
import org.spacehq.packetlib.event.session.PacketReceivedEvent;
import org.spacehq.packetlib.event.session.SessionAdapter;

import net.cyanwool.api.Server;
import net.cyanwool.api.entity.alive.player.Player;

public class PlaySessionAdapter extends SessionAdapter {

	private Server server;

	public PlaySessionAdapter(Server server) {
		this.server = server;
	}

	@Override
	public void packetReceived(PacketReceivedEvent event) {
		if (ClientSettingsPacket.class.isAssignableFrom(event.getClass())) {
			GameProfile profile = event.getSession().getFlag(MinecraftConstants.PROFILE_KEY);
			Player player = server.getPlayerManager().getPlayer(profile.getName());
			ClientSettingsPacket packet = event.getPacket();
			// ClientSettings settings = new ClientSettings(packet);
			// ((CyanPlayer) player).setSettings(settings);
		}
	}
}
