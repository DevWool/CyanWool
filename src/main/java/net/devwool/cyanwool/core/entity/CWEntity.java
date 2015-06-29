package net.devwool.cyanwool.core.entity;

import net.devwool.cyanwool.api.Server;
import net.devwool.cyanwool.api.entity.Entity;
import net.devwool.cyanwool.api.world.Position;
import net.devwool.cyanwool.api.world.World;
import net.devwool.cyanwool.api.world.chunk.Chunk;

import org.spacehq.opennbt.tag.builtin.CompoundTag;

public abstract class CWEntity implements Entity {

    private Server server;
    private Position pos;
    private int entityId;
    private boolean alive;
    private boolean invisible;
    private boolean onground;
    private int fireTicks;
    private int livedTicks;
    private Entity passenger;
    private Entity vehicle;

    public CWEntity(Server server) {
        this.server = server;
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
    public World getWorld() {
        return getPosition().getWorld();
    }

    @Override
    public void teleport(Position pos) {
        // TODO Auto-generated method stub

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
        // TODO Auto-generated method stub

    }

    @Override
    public void onTick() {
        // TODO Auto-generated method stub

    }

    @Override
    public void setInvisible(boolean flag) {
        invisible = flag;
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
        return vehicle;
    }

    @Override
    public boolean canSeeEntity(Entity entity) {
        return false;
    }

    @Override
    public boolean canSeePosition(Position pos) {
        return false;
    }

    @Override
    public Server getServer() {
        return server;
    }

    @Override
    public float getRotationYaw() {
        return 0;
    }

    @Override
    public float getRotationPitch() {
        return 0;
    }

    @Override
    public void setRotationYaw(float yaw) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setRotationPitch(float pitch) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveEntity(double x, double y, double z) {
        // TODO Auto-generated method stub

    }

    @Override
    public void loadCompoundTag(CompoundTag tag) {
        // TODO Auto-generated method stub

    }

    @Override
    public void saveCompoundTag(CompoundTag tag) {
        // TODO Auto-generated method stub

    }

    @Override
    public CompoundTag getCompoundTag() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setEntityID(int id) {
        // TODO Auto-generated method stub

    }

}