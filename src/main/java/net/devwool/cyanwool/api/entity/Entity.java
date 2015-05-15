package net.devwool.cyanwool.api.entity;

import java.util.List;

import net.devwool.cyanwool.api.Server;
import net.devwool.cyanwool.api.world.Position;
import net.devwool.cyanwool.api.world.World;
import net.devwool.cyanwool.api.world.chunk.Chunk;

import org.spacehq.opennbt.tag.builtin.CompoundTag;
import org.spacehq.packetlib.packet.Packet;

public interface Entity {

    public Position getPosition();

    public int getEntityID();

    public World getWorld();

    public void teleport(Position pos);

    public boolean isAlive();

    public boolean isInvisible();

    public boolean onGround();

    public void destroy();

    public void onTick();

    public void setInvisible(boolean flag);

    public int getFireTicks();

    public void setFireTicks(int ticks);

    // maybe add entity type ?

    public int getLivedTicks();

    public Chunk getChunk();

    public void setPassenger(Entity entity);

    public Entity getPassenger();

    public Entity getVehicle();

    public void setVehicle(Entity entity);

    public boolean canSeeEntity(Entity entity);

    public boolean canSeeLocation(Position pos);

    public Server getServer();

    // Body
    public float getRotationYaw();

    public float getRotationPitch();

    public void setRotationYaw(float yaw);

    public void setRotationPitch(float pitch);

    public void moveEntity(double x, double y, double z);

    // NBT
    public void loadCompoundTag(CompoundTag tag);

    public void saveCompoundTag(CompoundTag tag);

    public CompoundTag getCompoundTag();

    public List<Packet> getUpdatePackets();

    public abstract List<Packet> getSpawnPackets();
}