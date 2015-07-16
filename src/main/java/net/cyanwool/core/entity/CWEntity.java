package net.cyanwool.core.entity;

import net.cyanwool.api.Server;
import net.cyanwool.api.entity.Entity;
import net.cyanwool.api.entity.component.ComponentManager;
import net.cyanwool.api.entity.types.EntityType;
import net.cyanwool.api.world.Position;
import net.cyanwool.api.world.World;
import net.cyanwool.api.world.chunk.Chunk;
import net.cyanwool.core.entity.component.generic.CWBodyRotation;
import net.cyanwool.core.entity.component.generic.CWFireTicks;
import net.cyanwool.core.entity.component.generic.CWMovement;
import net.cyanwool.core.entity.component.generic.CWTransport;

public abstract class CWEntity implements Entity {

	private Server server;
	private Position pos;
	private int entityId;
	private boolean alive;
	private boolean invisible;
	private boolean onground;
	private EntityType entityType;
	private ComponentManager componentManager;

	public CWEntity(Server server, EntityType type) {
		this.server = server;
		this.entityType = type;
		this.componentManager = new ComponentManager(this);

		// init first components
		getComponentManager().addComponent(new CWBodyRotation(this));
		getComponentManager().addComponent(new CWFireTicks(this));
		getComponentManager().addComponent(new CWMovement(this));
		getComponentManager().addComponent(new CWTransport(this));
		getComponentManager().addComponent(new CWTransport(this));
	}

	@Override
	public Position getPosition() {
		return pos;
	}

	@Override
	public int getEntityID() {
		return entityId;
	}

	@Override
	public void onTick() {
		getComponentManager().onUpdateComponents();
	}

	@Override
	public World getWorld() {
		return getPosition().getWorld();
	}

	@Override
	public boolean isAlive() {
		return alive;
	}

	@Override
	public boolean isInvisible() {
		return invisible;
	}

	@Override
	public boolean onGround() {
		return onground;
	}

	@Override
	public void destroy() {
		getServer().getEntityManager().unregisterEntity(this);
	}

	@Override
	public void setInvisible(boolean flag) {
		this.invisible = flag;
	}

	@Override
	public Chunk getChunk() {
		return getPosition().getChunk();
	}

	@Override
	public Server getServer() {
		return server;
	}

	@Override
	public void setEntityID(int id) {
		this.entityId = id;
	}

	public EntityType getEntityType() {
		return entityType;
	}

	public ComponentManager getComponentManager() {
		return componentManager;
	}
}
