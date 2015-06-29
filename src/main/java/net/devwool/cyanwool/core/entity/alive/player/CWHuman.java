package net.devwool.cyanwool.core.entity.alive.player;

import net.devwool.cyanwool.api.Server;
import net.devwool.cyanwool.api.entity.alive.player.Human;
import net.devwool.cyanwool.api.world.Position;
import net.devwool.cyanwool.core.entity.CWEntityLivingBase;

public abstract class CWHuman extends CWEntityLivingBase implements Human {

    public CWHuman(Server server) {
        super(server);
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isSleeping() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isBlocking() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void sleepInBedAt(int x, int y, int z) {
        // TODO Auto-generated method stub

    }

    @Override
    public void sleepInBedAt(Position pos) {
        // TODO Auto-generated method stub

    }

    @Override
    public void wakeUp() {
        // TODO Auto-generated method stub

    }

    @Override
    public void closeInventory() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isDisableDamage() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setDisableDamage(boolean flag) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isFlying() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setFlying(boolean flag) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isAllowFlying() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setAllowFlying(boolean flag) {
        // TODO Auto-generated method stub

    }

    @Override
    public float getFlySpeed() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setFlySpeed(float speed) {
        // TODO Auto-generated method stub

    }

    @Override
    public float getWalkSpeed() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setWalkSpeed(float speed) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean canBuild() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setBuild(boolean flag) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getSleepingTicks() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setSleepingTicks(int ticks) {
        // TODO Auto-generated method stub

    }

}