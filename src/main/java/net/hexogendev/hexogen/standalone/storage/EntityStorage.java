package net.hexogendev.hexogen.api.storage;

import java.util.List;

/**
 * Based: http://minecraft.gamepedia.com/Chunk_format
 */
public interface EntityStorage {

	// Load
	public int getEntityID();

	public double getX();

	public double getY();

	public double getZ();

	public double getMotionX();

	public double getMotionY();

	public double getMotionZ();

	public float getYaw();

	public float getPitch();

	public float getFallDistance();

	public short getFireTicks();

	public short getAirTicks();

	public boolean onGround();

	public int getDimension();

	public boolean isInvulnerable();

	public int getPortalCooldown();

	public long getUUIDMost();

	public long getUUIDLeast();

	public String getUUID();

	public String getCustomName();

	public boolean isCustomNameVisible();

	public boolean isSilent();

	@Deprecated
	public EntityStorage getRiding();

	public List getPassengers(); // ?

	public boolean isGlowing();

	public List getTags(); // ?

	// CommandStats ?

	// Save
	public void setEntityID(int entityId);

	public void setX(double x);

	public void setY(double y);

	public void setZ(double z);

	public void setMotionX(double motionX);

	public void setMotionY(double motionY);

	public void setMotionZ(double motionZ);

	public void setYaw(float yaw);

	public void setPitch(float pitch);

	public void setFallDistance(float fallDistance);

	public void setFireTicks(short ticks);

	public void setAirTicks(short ticks);

	public void setGround(boolean flag);

	public void setDimension(int idDimension);

	public void setInvulnerable(boolean flag);

	public void setPortalCooldown(int cooldown);

	public void setUUIDMost(long uuid);

	public void setUUIDLeast(long uuid);

	public void setUUID(String uuid);

	public void setCustomName(String name);

	public void setCustomNameVisible(boolean flag);

	public void setSilent(boolean flag);

	public void setPassengers(List passengers); // ?

	public void setGlowing(boolean flag);

	public void setTags(List tags); // ?
}
