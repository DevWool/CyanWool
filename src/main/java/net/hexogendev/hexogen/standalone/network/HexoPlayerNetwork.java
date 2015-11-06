package net.hexogendev.hexogen.standalone.network;

import org.spacehq.packetlib.Session;
import org.spacehq.packetlib.packet.Packet;

import net.hexogendev.hexogen.api.Server;
import net.hexogendev.hexogen.api.entity.alive.player.Player;
import net.hexogendev.hexogen.api.network.IPacket;
import net.hexogendev.hexogen.api.network.PlayerNetwork;

public class HexoPlayerNetwork implements PlayerNetwork {

	private Player player;
	private Session session;

	public HexoPlayerNetwork(Player player, Session session) {
		this.player = player;
		this.session = session;
	}

	@Override
	public Player getPlayer() {
		return player;
	}

	@Override
	public void sendPacket(IPacket packet) {
		if (packet instanceof Packet) {
			Packet p = (Packet) packet;
			session.send(p);
		}
	}

	@Override
	public void disconnect(String reason) {
		session.disconnect(reason);
	}

	@Override
	public String getHost() {
		return session.getHost();
	}

	@Override
	public Server getServer() {
		return player.getServer();
	}
}
