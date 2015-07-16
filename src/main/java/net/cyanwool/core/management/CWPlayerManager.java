package net.cyanwool.core.management;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import net.cyanwool.api.Server;
import net.cyanwool.api.entity.types.player.Player;
import net.cyanwool.api.management.PlayerManager;
import net.cyanwool.api.world.World;

import org.spacehq.mc.auth.GameProfile;
import org.spacehq.mc.protocol.data.game.values.PlayerListEntry;
import org.spacehq.mc.protocol.data.game.values.PlayerListEntryAction;
import org.spacehq.mc.protocol.packet.ingame.server.ServerPlayerListEntryPacket;
import org.spacehq.packetlib.packet.Packet;

public class CWPlayerManager implements PlayerManager {

	private Server server;
	private List<Player> players;

	public CWPlayerManager(Server server) {
		this.server = server;
		this.players = new CopyOnWriteArrayList<Player>();
	}

	@Override
	public Server getServer() {
		return server;
	}

	@Override
	public void joinPlayer(GameProfile info) {
		Player player = null;

		try {
			wait(100); // don't know
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (player.isOnline()) {
			spawnPlayer(player);
			players.add(player);
		}
	}

	@Override
	public void spawnPlayer(Player player) {
		if (player.isOnline()) {
			for (Packet packet : player.getSpawnPackets()) {
				getServer().getNetworkServer().sendPacketDistance(player.getPosition(), packet, getServer().getServerConfiguration().getViewDistance());
			}

			ServerPlayerListEntryPacket packet = new ServerPlayerListEntryPacket(PlayerListEntryAction.ADD_PLAYER, new PlayerListEntry[] { player.getPlayerListEntry() });
			getServer().getNetworkServer().sendPacketForAllExpect(packet, player);

			getServer().getEntityManager().registerEntity(player);
		}
	}

	@Override
	public void leavePlayer(Player player) {
		player.destroy();
		ServerPlayerListEntryPacket packet = new ServerPlayerListEntryPacket(PlayerListEntryAction.REMOVE_PLAYER, new PlayerListEntry[] { player.getPlayerListEntry() });
		getServer().getNetworkServer().sendPacketForAllExpect(packet, player);
	}

	@Override
	public List<Player> getPlayers() {
		return players;
	}

	@Override
	public void sendMessageForAll(String message) {
		for (Player player : getPlayers()) {
			player.sendMessage(message);
		}
	}

	@Override
	public void sendMessageForAll(World world, String message) {
		for (Player player : world.getPlayers()) {
			player.sendMessage(message);
		}
	}

	@Override
	public void refreshPlayer(Player player, PlayerListEntryAction action) {
		if (player.isOnline()) {
			ServerPlayerListEntryPacket packet = new ServerPlayerListEntryPacket(action, new PlayerListEntry[] { player.getPlayerListEntry() });
			getServer().getNetworkServer().sendPacketForAllExpect(packet, player);
		}
	}

	@Override
	public Player getPlayer(String name) {
		for (Player player : getPlayers()) {
			if (player.getName().equals(name)) {
				return player;
			}
		}
		return null;
	}

}
