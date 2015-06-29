package net.devwool.cyanwool.core.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import net.devwool.cyanwool.api.Server;
import net.devwool.cyanwool.api.entity.Entity;
import net.devwool.cyanwool.api.entity.EntityManager;
import net.devwool.cyanwool.api.world.Position;
import net.devwool.cyanwool.api.world.chunk.Chunk;

public class CWEntityManager implements EntityManager {

    private ConcurrentMap<Integer, Entity> entities = new ConcurrentHashMap<Integer, Entity>();
    private Set<Integer> usedIds = new HashSet<Integer>();
    private int last = 0;
    private Server server;

    public CWEntityManager(Server server) {
        this.server = server;
    }

    @Override
    public Collection<Entity> getAll() {
        return entities.values();
    }

    @Override
    public Entity getEntity(int id) {
        return entities.get(id);
    }

    @Override
    public void registerEntity(final Entity entity) {
        if (getEntity(entity.getEntityID()) != null) {
            return; // IMPOSIBBLEEE
        }

        int startedAt = last;
        for (int id = last + 1; id != startedAt; ++id) {
            if (id == -1 || id == 0)
                continue;

            if (usedIds.add(id)) {
                entity.setEntityID(id);
                last = id;
                break;
            }
        }
        entities.put(entity.getEntityID(), entity);
        Position loc = entity.getPosition();
        loc.getChunk().getEntities().add(entity);
        loc.getWorld().getEntities().add(entity);
    }

    @Override
    public void unregisterEntity(Entity entity) {
        entities.remove(entity.getEntityID());
        usedIds.remove(entity.getEntityID());
        for (Entity ent : entity.getChunk().getEntities()) {
            if (ent.getEntityID() == entity.getEntityID()) {
                entity.getChunk().getEntities().remove(ent);
                break;
            }
        }

        for (Entity ent : entity.getWorld().getEntities()) {
            if (ent.getEntityID() == entity.getEntityID()) {
                entity.getWorld().getEntities().remove(ent);
                break;
            }
        }
    }

    @Override
    public void moveToOtherPosition(Entity entity, Position loc) {
        Chunk prev = entity.getChunk();
        Chunk next = loc.getChunk();

        if (!prev.equals(next)) {
            for (Entity ent : prev.getEntities()) {
                if (ent.getEntityID() == entity.getEntityID()) {
                    prev.getEntities().remove(ent);
                    break;
                }
            }

            next.getEntities().add(entity);
        }
    }

    @Override
    public Server getServer() {
        return server;
    }
}