package net.hexogendev.hexogen.standalone.management;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import org.spacehq.mc.protocol.data.game.values.PlayerListEntryAction;

import net.hexogendev.hexogen.api.Server;
import net.hexogendev.hexogen.api.entity.alive.player.Player;
import net.hexogendev.hexogen.api.entity.alive.player.PlayerProfile;
import net.hexogendev.hexogen.api.management.PlayerManager;
import net.hexogendev.hexogen.api.world.World;

public class HexoPlayerManager implements PlayerManager {

	private Server server;
	private List<Player> players;

	public HexoPlayerManager(Server server) {
		this.server = server;
		this.players = new CopyOnWriteArrayList<Player>();
	}

	@Override
	public Server getServer() {
		return server;
	}

	@Override
	public void joinPlayer(PlayerProfile info) {
		Player player = null;

		try {
			wait(100); // don't know
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (player.isOnline()) {
			getServer().getEntityManager().registerEntity(player);
			players.add(player);
		}
	}

	@Override
	public void onLeavePlayer(Player player) {
		// player.destroy();
		// ServerPlayerListEntryPacket packet = new ServerPlayerListEntryPacket(PlayerListEntryAction.REMOVE_PLAYER, new PlayerListEntry[] { player.getPlayerListEntry() });
		// getServer().getNetworkServer().sendPacketForAllExpect(packet, player);
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

	// @Override
	public void refreshPlayer(Player player, PlayerListEntryAction action) {
		if (player.isOnline()) {
			// ServerPlayerListEntryPacket packet = new ServerPlayerListEntryPacket(action, new PlayerListEntry[] { player.getPlayerListEntry() });
			// getServer().getNetworkServer().sendPacketForAllExpect(packet, player);
			// player.getTrackerEntry().refresh();
			// player.getTrackerEntry().
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

	@Override
	public Player getPlayer(UUID uuid) {
		for (Player player : getPlayers()) {
			if (player.getUUID().equals(uuid)) {
				return player;
			}
		}
		return null;
	}

}
