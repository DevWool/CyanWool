package net.cyanwool.core.entity;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.spacehq.opennbt.tag.builtin.CompoundTag;

import net.cyanwool.api.Server;
import net.cyanwool.api.command.ICommandSender;
import net.cyanwool.api.entity.Entity;
import net.cyanwool.api.entity.EntityTrackerEntry;
import net.cyanwool.api.entity.controllers.EntityMoveController;
import net.cyanwool.api.network.IPacket;
import net.cyanwool.api.utils.Rotation;
import net.cyanwool.api.world.Position;
import net.cyanwool.api.world.World;
import net.cyanwool.api.world.chunk.Chunk;
import net.cyanwool.core.entity.metadata.MetadataHelper;
import net.cyanwool.core.entity.metadata.MetadataMap;

public abstract class CyanEntity implements Entity, ICommandSender {

	protected World world;
	protected Position pos;
	protected Position prevPos;
	protected int id;
	protected UUID uuid;
	protected Random random;
	protected boolean alive;
	protected boolean onGround;
	protected int fireTicks;
	protected int livedTicks;
	protected Entity passenger;
	protected Entity vehicle;
	protected Rotation bodyRotation;
	protected CompoundTag compoundTag;
	protected boolean teleported;
	protected EntityMoveController moveController;
	protected MetadataMap metadata;

	public CyanEntity(World world) {
		this.world = world;
		this.pos = new Position(world, 0, 0, 0);
		this.random = new Random();
		this.alive = true;
		this.bodyRotation = new Rotation(0, 0);
		this.metadata = new MetadataMap();
		applyMetadata();
	}

	public void applyMetadata() {
		// TODO: Metadata
		// Information about metadata:
		// http://wiki.vg/Entities#Entity_Metadata_Format
		getMetadataMap().setMetadata(MetadataHelper.STATUS, (byte) 0);
		getMetadataMap().setMetadata(MetadataHelper.AIR_TIME, (short) 0);
		getMetadataMap().setBit(MetadataHelper.STATUS, MetadataHelper.StatusFlags.ON_FIRE, this.fireTicks > 0);
		getMetadataMap().setBit(MetadataHelper.STATUS, MetadataHelper.StatusFlags.SNEAKING, isSneaking());
		getMetadataMap().setBit(MetadataHelper.STATUS, MetadataHelper.StatusFlags.SPRINTING, isSprinting());
		getMetadataMap().setBit(MetadataHelper.STATUS, MetadataHelper.StatusFlags.ARM_UP, false);// Eating,
		// drinking,
		// blocking
		getMetadataMap().setBit(MetadataHelper.STATUS, MetadataHelper.StatusFlags.INVISIBLE, isInvisible());// TEST
	}

	public abstract EntityTrackerEntry getTrackerEntry();

	@Override
	public Position getPreviousPosition() {
		return prevPos;
	}

	public Position getPosition() {
		return pos;
	}

	public int getEntityID() {
		return id;
	}

	public void setEntityID(int id) {
		if (this.id != 0) {
			getServer().getLogger().info("Change entity id is impossible.");
			return;
		}
		this.id = id;
	}

	public UUID getUUID() {
		if (uuid == null) {
			uuid = UUID.randomUUID();
		}
		return uuid;
	}

	public Random getRandom() {
		return random;
	}

	public World getWorld() {
		return world;
	}

	public void teleport(Position pos) {
		this.pos = pos;
		if (!getWorld().equals(pos.getWorld())) {
			world = pos.getWorld();
		}

		this.teleported = true;
		// TEST
	}

	@Override
	public boolean isTeleported() {
		return teleported;
	}

	@Override
	public boolean isMoved() {
		return prevPos.equals(pos); // TEST
	}

	@Override
	public boolean isRotated() {
		return getBodyRotation().isRotated(); // TEST
	}

	public boolean isAlive() {
		return alive;
	}

	public boolean isInvisible() {
		Object object = getMetadataMap().getBit(MetadataHelper.STATUS, MetadataHelper.StatusFlags.INVISIBLE);
		return (boolean) object;
	}

	public boolean isSneaking() {
		Object object = getMetadataMap().getBit(MetadataHelper.STATUS, MetadataHelper.StatusFlags.SNEAKING);
		return (boolean) object;
	}

	public void setSneaking(boolean flag) {
		getMetadataMap().setBit(MetadataHelper.STATUS, MetadataHelper.StatusFlags.SNEAKING, flag);
	}

	public boolean isSprinting() {
		Object object = getMetadataMap().getBit(MetadataHelper.STATUS, MetadataHelper.StatusFlags.SPRINTING);
		return (boolean) object;
	}

	public void setSprinting(boolean flag) {
		getMetadataMap().setBit(MetadataHelper.STATUS, MetadataHelper.StatusFlags.SPRINTING, flag);
	}

	public boolean onGround() {
		return onGround;
	}

	public void onTick() {
		if (isAlive()) {
			livedTicks++;
		}

		if (fireTicks > 0) {
			fireTicks--;
		}

		getMetadataMap().setBit(MetadataHelper.STATUS, MetadataHelper.StatusFlags.ON_FIRE, fireTicks > 0);

		// resend position if it's been a while
		if (livedTicks % (30 * 20) == 0) {
			teleported = true;
		}
	}

	public void extinguish() {
		// TODO Auto-generated method stub

	}

	public void setInvisible(boolean flag) {
		getMetadataMap().setBit(MetadataHelper.STATUS, MetadataHelper.StatusFlags.INVISIBLE, flag);
	}

	public int getFireTicks() {
		return fireTicks;
	}

	public void setFireTicks(int ticks) {
		this.fireTicks = ticks;
	}

	public int getLivedTicks() {
		return livedTicks;
	}

	public Chunk getChunk() {
		return getPosition().getChunk();
	}

	public void setPassenger(Entity entity) {
		this.passenger = entity;
	}

	public Entity getPassenger() {
		return passenger;
	}

	public Entity getVehicle() {
		return this.vehicle;
	}

	public boolean canSeeEntity(Entity entity) {
		return canSeePosition(entity.getPosition());
	}

	public boolean canSeePosition(Position pos) {
		double dx = Math.abs(getPosition().getX() - pos.getX());
		double dz = Math.abs(getPosition().getZ() - pos.getZ());
		return pos.getWorld().equals(getWorld()) && dx <= (getServer().getServerConfiguration().getViewDistance() * getChunk().getWidth()) && dz <= (getServer().getServerConfiguration().getViewDistance() * getChunk().getHeight());
	}

	public Server getServer() {
		return world.getServer();
	}

	public Rotation getBodyRotation() {
		return bodyRotation;
	}

	public void setBodyRotation(Rotation rotation) {
		this.bodyRotation = rotation;
	}

	public EntityMoveController getMoveController() {
		return moveController;
	}

	public void loadCompoundTag(CompoundTag tag) {

	}

	public void saveCompoundTag(CompoundTag tag) {

	}

	public CompoundTag getCompoundTag() {
		return compoundTag;
	}

	public abstract List<IPacket> getUpdatePackets();

	public abstract List<IPacket> getSpawnPackets();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entity other = (Entity) obj;
		if (id != other.getEntityID())
			return false;
		if (uuid == null) {
			if (other.getUUID() != null)
				return false;
		} else if (!uuid.equals(other.getUUID()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}

	@Override
	public boolean isValid() {
		return getServer().getEntityManager().getEntity(id) != null;
	}

	public MetadataMap getMetadataMap() {
		return metadata;
	}

	public void sendMessage(String message) {

	}

	public String getName() {
		return toString();
	}

	public boolean isPlayer() {
		return false;
	}

	public String getLangCode() {
		return "en_US";
	}

	public void executeCommand(String commandName) {
		getServer().getCommandManager().dispatchCommand(this, commandName);
	}
}
