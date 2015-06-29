package net.devwool.cyanwool.core.entity;

import net.devwool.cyanwool.api.Server;
import net.devwool.cyanwool.api.entity.Entity;
import net.devwool.cyanwool.api.entity.EntityLivingBase;
import net.devwool.cyanwool.api.world.Position;

import org.spacehq.mc.protocol.data.game.values.world.Sound;

public abstract class CWEntityLivingBase extends CWEntity implements EntityLivingBase {

    public CWEntityLivingBase(Server server) {
        super(server);
    }

    @Override
    public boolean isEntityUndead() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public float getEyeHeight() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Position getEyePosition() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void damage(float amount) {
        // TODO Auto-generated method stub

    }

    @Override
    public void damage(float amount, Entity damager) {
        // TODO Auto-generated method stub

    }

    @Override
    public double getHealth() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setHealth(double health) {
        // TODO Auto-generated method stub

    }

    @Override
    public double getMaxHealth() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void settMaxHealth(double maxHealth) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setTarget(EntityLivingBase entity) {
        // TODO Auto-generated method stub

    }

    @Override
    public EntityLivingBase getTarget() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Sound getDamageSound() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Sound getTalkSound() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Sound getDeathSound() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setDeathSound(Sound sound) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setDamageSound(Sound sound) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setTalkSound(Sound sound) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean playDamageSound() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean playTalkSound() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean playDeathSound() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String getDisplayName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setDisplayName(String name) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean hasDisplayName() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isRenderDisplayName() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setRenderDisplayName(boolean flag) {
        // TODO Auto-generated method stub

    }

    @Override
    public void interact(EntityLivingBase entity) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onDamageEntity(Entity damager, float damage) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onAttackEntity(Entity target, float damage) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onDeathEntity() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onWalkingEntity() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onInteractEntity(EntityLivingBase interacter) {
        // TODO Auto-generated method stub

    }

}