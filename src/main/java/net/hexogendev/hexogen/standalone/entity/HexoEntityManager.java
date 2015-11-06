package net.hexogendev.hexogen.standalone.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import net.hexogendev.hexogen.api.Server;
import net.hexogendev.hexogen.api.entity.Entity;
import net.hexogendev.hexogen.api.entity.EntityManager;
import net.hexogendev.hexogen.api.world.Position;
import net.hexogendev.hexogen.api.world.chunk.Chunk;

public class HexoEntityManager implements EntityManager {

	private ConcurrentMap<Integer, Entity> entities = new ConcurrentHashMap<Integer, Entity>();
	private Set<Integer> usedIds = new HashSet<Integer>();
	private int last = 0;
	private Server server;

	public HexoEntityManager(Server server) {
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
		if (getEntity(baseEntity.getID()) != null) {
			return; // IMPOSIBBLEEE
		}

		int startedAt = last;
		for (int id = last + 1; id != startedAt; ++id) {
			if (id == -1 || id == 0)
				continue;

			if (usedIds.add(id)) {
				baseEntity.setID(id);
				last = id;
				break;
			}
		}
		entities.put(baseEntity.getID(), baseEntity);
		Position loc = baseEntity.getPosition();
		loc.getChunk().getEntities().add(baseEntity);
		loc.getWorld().getEntities().add(baseEntity);
	}

	@Override
	public void unregisterEntity(Entity baseEntity) {
		entities.remove(baseEntity.getID());
		usedIds.remove(baseEntity.getID());
		for (Entity ent : baseEntity.getChunk().getEntities()) {
			if (ent.getID() == baseEntity.getID()) {
				baseEntity.getChunk().getEntities().remove(ent);
				break;
			}
		}

		for (Entity ent : baseEntity.getWorld().getEntities()) {
			if (ent.getID() == baseEntity.getID()) {
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
				if (ent.getID() == baseEntity.getID()) {
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
