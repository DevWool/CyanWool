package net.hexogendev.hexogen.standalone.entity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.hexogendev.hexogen.api.Server;
import net.hexogendev.hexogen.api.entity.Entity;
import net.hexogendev.hexogen.api.entity.EntityTracker;
import net.hexogendev.hexogen.api.entity.EntityTrackerEntry;

public class HexoEntityTracker implements EntityTracker {

	private Map<Integer, EntityTrackerEntry> trackedEntities;
	private Server server;

	public HexoEntityTracker(Server server) {
		this.server = server;
		this.trackedEntities = new ConcurrentHashMap<Integer, EntityTrackerEntry>();
	}

	@Override
	public void registerEntity(Entity entity) {
		if (trackedEntities.containsKey(entity.getID())) {
			server.getLogger().info("Entity is already tracked!");
			return;
		}

		EntityTrackerEntry entry = entity.getTrackerEntry();
		trackedEntities.put(entity.getID(), entry);
		entry.update();
	}

	@Override
	public void unregisterEntity(Entity entity) {
		if (!trackedEntities.containsKey(entity.getID())) {
			server.getLogger().info("Entity is not tracked!");
			return;
		}
		EntityTrackerEntry entry = entity.getTrackerEntry();
		entry.destroy();
		trackedEntities.remove(entity.getID());
	}

	@Override
	public void onUpdate() {
		for (EntityTrackerEntry entry : getMapTrackedEntities().values()) {
			entry.update();
		}
	}

	@Override
	public Map<Integer, EntityTrackerEntry> getMapTrackedEntities() {
		return trackedEntities;
	}
}
