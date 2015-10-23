package net.cyanwool.core.entity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.cyanwool.api.Server;
import net.cyanwool.api.entity.Entity;
import net.cyanwool.api.entity.EntityTracker;
import net.cyanwool.api.entity.EntityTrackerEntry;

public class CyanEntityTracker implements EntityTracker {

	private Map<Integer, EntityTrackerEntry> trackedEntities;
	private Server server;

	public CyanEntityTracker(Server server) {
		this.server = server;
		this.trackedEntities = new ConcurrentHashMap<Integer, EntityTrackerEntry>();
	}

	public void registerEntity(Entity entity) {
		if (trackedEntities.containsKey(entity.getEntityID())) {
			server.getLogger().info("Entity is already tracked!");
			return;
		}

		EntityTrackerEntry entry = entity.getTrackerEntry();
		trackedEntities.put(entity.getEntityID(), entry);
		entry.update();
	}

	public void unregisterEntity(Entity entity) {
		if (!trackedEntities.containsKey(entity.getEntityID())) {
			server.getLogger().info("Entity is not tracked!");
			return;
		}
		EntityTrackerEntry entry = entity.getTrackerEntry();
		entry.destroy();
		trackedEntities.remove(entity.getEntityID());
	}

	public void onUpdate() {
		for (EntityTrackerEntry entry : getMapTrackedEntities().values()) {
			entry.update();
		}
	}

	public Map<Integer, EntityTrackerEntry> getMapTrackedEntities() {
		return trackedEntities;
	}
}
