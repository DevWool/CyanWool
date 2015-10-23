package net.cyanwool.core.entity;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.spacehq.mc.protocol.packet.ingame.server.entity.ServerDestroyEntitiesPacket;

import net.cyanwool.api.entity.Entity;
import net.cyanwool.api.entity.EntityTrackerEntry;
import net.cyanwool.api.entity.alive.player.Player;
import net.cyanwool.api.network.IPacket;
import net.cyanwool.api.world.Position;

public class CyanEntityTrackerEntry implements EntityTrackerEntry {

	private Entity entity;
	private List<Player> viewers;
	private int distance;
	private boolean isDestroy;

	public CyanEntityTrackerEntry(Entity entity, int renderDistance) {
		this.entity = entity;
		this.distance = renderDistance;
		this.viewers = new CopyOnWriteArrayList<Player>();
	}

	public Entity getEntity() {
		return entity;
	}

	public void addViewerPlayer(Player player) {

	}

	public void removeViewerPlayer(Player player) {

	}

	public void destroy() {
		for (Player player : getViewerPlayers()) {
			ServerDestroyEntitiesPacket packet = new ServerDestroyEntitiesPacket(entity.getEntityID());
			player.getPlayerNetwork().sendPacket(packet);
		}
		viewers.clear();
		isDestroy = true;
	}

	public List<Player> getViewerPlayers() {
		return viewers;
	}

	public void update(Collection<Player> players) {
		for (Player player : players) {
			updatePlayerEntity(player);
		}
	}

	public void update() {
		update(entity.getWorld().getPlayers());
	}

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
					ServerDestroyEntitiesPacket packet = new ServerDestroyEntitiesPacket(entity.getEntityID());
					player.getPlayerNetwork().sendPacket(packet);
				}
			}
		}
	}

	public boolean checkDistance(Position pos) {
		if (pos.distanceTo(pos) < distance) {
			return true;
		}
		return false;
	}
}
