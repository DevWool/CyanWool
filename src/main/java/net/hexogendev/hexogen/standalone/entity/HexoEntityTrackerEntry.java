package net.hexogendev.hexogen.standalone.entity;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.spacehq.mc.protocol.packet.ingame.server.entity.ServerDestroyEntitiesPacket;

import net.hexogendev.hexogen.api.entity.Entity;
import net.hexogendev.hexogen.api.entity.EntityTrackerEntry;
import net.hexogendev.hexogen.api.entity.alive.player.Player;
import net.hexogendev.hexogen.api.network.IPacket;
import net.hexogendev.hexogen.api.world.Position;

public class HexoEntityTrackerEntry implements EntityTrackerEntry {

	private Entity entity;
	private List<Player> viewers;
	private int distance;
	private boolean isDestroy;

	public HexoEntityTrackerEntry(Entity entity, int renderDistance) {
		this.entity = entity;
		this.distance = renderDistance;
		this.viewers = new CopyOnWriteArrayList<Player>();
	}

	@Override
	public Entity getEntity() {
		return entity;
	}

	@Override
	public void addViewerPlayer(Player player) {

	}

	@Override
	public void removeViewerPlayer(Player player) {

	}

	@Override
	public void destroy() {
		for (Player player : getViewerPlayers()) {
			ServerDestroyEntitiesPacket packet = new ServerDestroyEntitiesPacket(entity.getID());
			player.getPlayerNetwork().sendPacket(packet);
		}
		viewers.clear();
		isDestroy = true;
	}

	@Override
	public List<Player> getViewerPlayers() {
		return viewers;
	}

	@Override
	public void update(Collection<Player> players) {
		for (Player player : players) {
			updatePlayerEntity(player);
		}
	}

	@Override
	public void update() {
		update(entity.getWorld().getPlayers());
	}

	@Override
	public void updatePlayerEntity(Player player) {
		if (isDestroy)
			return;
		if (!player.equals(this.entity)) {
			if (checkDistance(player.getPosition())) {
				if (!viewers.contains(player)) {
					for (IPacket packet : entity.getSpawnPackets()) {
						player.getPlayerNetwork().sendPacket(packet);
					}

					for (IPacket packet : entity.getUpdatePackets()) {
						player.getPlayerNetwork().sendPacket(packet);
					}
				}
			} else {
				if (viewers.contains(player)) {
					viewers.remove(player);
					ServerDestroyEntitiesPacket packet = new ServerDestroyEntitiesPacket(entity.getID());
					player.getPlayerNetwork().sendPacket(packet);
				}
			}
		}
	}

	@Override
	public boolean checkDistance(Position pos) {
		if (pos.distanceTo(pos) < distance) {
			return true;
		}
		return false;
	}
}
