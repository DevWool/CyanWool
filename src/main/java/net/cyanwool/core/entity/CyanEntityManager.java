package net.cyanwool.core.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import net.cyanwool.api.Server;
import net.cyanwool.api.entity.Entity;
import net.cyanwool.api.entity.EntityManager;
import net.cyanwool.api.world.Position;
import net.cyanwool.api.world.chunk.Chunk;

public class CyanEntityManager implements EntityManager {

	private ConcurrentMap<Integer, Entity> entities = new ConcurrentHashMap<Integer, Entity>();
	private Set<Integer> usedIds = new HashSet<Integer>();
	private int last = 0;
	private Server server;

	public CyanEntityManager(Server server) {
		this.server = server;
	}

	@Override
	public Collection<Entity> getAllEntities() {
		return entities.values();
	}

	@Override
	public Entity getEntity(int id) {
		return entities.get(id);
	}

	@Override
	public void registerEntity(final Entity baseEntity) {
		if (getEntity(baseEntity.getEntityID()) != null) {
			return; // IMPOSIBBLEEE
		}

		int startedAt = last;
		for (int id = last + 1; id != startedAt; ++id) {
			if (id == -1 || id == 0)
				continue;

			if (usedIds.add(id)) {
				baseEntity.setEntityID(id);
				last = id;
				break;
			}
		}
		entities.put(baseEntity.getEntityID(), baseEntity);
		Position loc = baseEntity.getPosition();
		loc.getChunk().getEntities().add(baseEntity);
		loc.getWorld().getEntities().add(baseEntity);
	}

	@Override
	public void unregisterEntity(Entity baseEntity) {
		entities.remove(baseEntity.getEntityID());
		usedIds.remove(baseEntity.getEntityID());
		for (Entity ent : baseEntity.getChunk().getEntities()) {
			if (ent.getEntityID() == baseEntity.getEntityID()) {
				baseEntity.getChunk().getEntities().remove(ent);
				break;
			}
		}

		for (Entity ent : baseEntity.getWorld().getEntities()) {
			if (ent.getEntityID() == baseEntity.getEntityID()) {
				baseEntity.getWorld().getEntities().remove(ent);
				break;
			}
		}
	}

	@Override
	public void moveToOtherPosition(Entity baseEntity, Position loc) {
		Chunk prev = baseEntity.getChunk();
		Chunk next = loc.getChunk();

		if (!prev.equals(next)) {
			for (Entity ent : prev.getEntities()) {
				if (ent.getEntityID() == baseEntity.getEntityID()) {
					prev.getEntities().remove(ent);
					break;
				}
			}

			next.getEntities().add(baseEntity);
		}
	}

	@Override
	public Server getServer() {
		return server;
	}
}
