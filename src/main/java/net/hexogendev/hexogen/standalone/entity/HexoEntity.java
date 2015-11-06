package net.hexogendev.hexogen.standalone.entity;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.spacehq.opennbt.tag.builtin.CompoundTag;

import net.hexogendev.hexogen.api.Server;
import net.hexogendev.hexogen.api.command.ICommandSender;
import net.hexogendev.hexogen.api.entity.Entity;
import net.hexogendev.hexogen.api.entity.EntityTrackerEntry;
import net.hexogendev.hexogen.api.entity.controllers.EntityMoveController;
import net.hexogendev.hexogen.api.network.IPacket;
import net.hexogendev.hexogen.api.utils.Rotation;
import net.hexogendev.hexogen.api.world.Position;
import net.hexogendev.hexogen.api.world.World;
import net.hexogendev.hexogen.api.world.chunk.Chunk;
import net.hexogendev.hexogen.standalone.entity.metadata.MetadataHelper;
import net.hexogendev.hexogen.standalone.entity.metadata.MetadataMap;

public abstract class HexoEntity implements Entity, ICommandSender {

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

	public HexoEntity(World world) {
		this.world = world;
		this.pos = new Position(world, 0, 0, 0);
		this.random = new Random();
		this.alive = true;
		this.bodyRotation = new Rotation(0, 0);
		this.metadata = new MetadataMap();
		applyMetadata();
	}

	@Override
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

	@Override
	public abstract EntityTrackerEntry getTrackerEntry();

	@Override
	public Position getPreviousPosition() {
		return prevPos;
	}

	@Override
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

	@Override
	public UUID getUUID() {
		if (uuid == null) {
			uuid = UUID.randomUUID();
		}
		return uuid;
	}

	public Random getRandom() {
		return random;
	}

	@Override
	public World getWorld() {
		return world;
	}

	@Override
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

	@Override
	public boolean isAlive() {
		return alive;
	}

	@Override
	public boolean isInvisible() {
		Object object = getMetadataMap().getBit(MetadataHelper.STATUS, MetadataHelper.StatusFlags.INVISIBLE);
		return (boolean) object;
	}

	@Override
	public boolean isSneaking() {
		Object object = getMetadataMap().getBit(MetadataHelper.STATUS, MetadataHelper.StatusFlags.SNEAKING);
		return (boolean) object;
	}

	@Override
	public void setSneaking(boolean flag) {
		getMetadataMap().setBit(MetadataHelper.STATUS, MetadataHelper.StatusFlags.SNEAKING, flag);
	}

	@Override
	public boolean isSprinting() {
		Object object = getMetadataMap().getBit(MetadataHelper.STATUS, MetadataHelper.StatusFlags.SPRINTING);
		return (boolean) object;
	}

	@Override
	public void setSprinting(boolean flag) {
		getMetadataMap().setBit(MetadataHelper.STATUS, MetadataHelper.StatusFlags.SPRINTING, flag);
	}

	@Override
	public boolean onGround() {
		return onGround;
	}

	@Override
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

	@Override
	public void setInvisible(boolean flag) {
		getMetadataMap().setBit(MetadataHelper.STATUS, MetadataHelper.StatusFlags.INVISIBLE, flag);
	}

	@Override
	public int getFireTicks() {
		return fireTicks;
	}

	@Override
	public void setFireTicks(int ticks) {
		this.fireTicks = ticks;
	}

	@Override
	public int getLivedTicks() {
		return livedTicks;
	}

	@Override
	public Chunk getChunk() {
		return getPosition().getChunk();
	}

	@Override
	public void setPassenger(Entity entity) {
		this.passenger = entity;
	}

	@Override
	public Entity getPassenger() {
		return passenger;
	}

	@Override
	public Entity getVehicle() {
		return this.vehicle;
	}

	@Override
	public boolean canSeeEntity(Entity entity) {
		return canSeePosition(entity.getPosition());
	}

	@Override
	public boolean canSeePosition(Position pos) {
		double dx = Math.abs(getPosition().getX() - pos.getX());
		double dz = Math.abs(getPosition().getZ() - pos.getZ());
		return pos.getWorld().equals(getWorld()) && dx <= (getServer().getServerConfiguration().getViewDistance() * getChunk().getWidth()) && dz <= (getServer().getServerConfiguration().getViewDistance() * getChunk().getHeight());
	}

	@Override
	public Server getServer() {
		return world.getServer();
	}

	@Override
	public Rotation getBodyRotation() {
		return bodyRotation;
	}

	@Override
	public void setBodyRotation(Rotation rotation) {
		this.bodyRotation = rotation;
	}

	@Override
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

	@Override
	public abstract List<IPacket> getUpdatePackets();

	@Override
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
		if (id != other.getID())
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

	@Override
	public void sendMessage(String message) {

	}

	@Override
	public String getName() {
		return toString();
	}

	@Override
	public boolean isPlayer() {
		return false;
	}

	@Override
	public String getLangCode() {
		return "en_US";
	}

	@Override
	public void executeCommand(String commandName) {
		getServer().getCommandManager().dispatchCommand(this, commandName);
	}
}
