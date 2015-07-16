package net.cyanwool.core.entity;

import net.cyanwool.api.Server;
import net.cyanwool.api.entity.Entity;
import net.cyanwool.api.entity.EntityLivingBase;
import net.cyanwool.api.entity.types.EntityType;
import net.cyanwool.api.world.Position;
import net.cyanwool.api.world.sounds.Sound;

public abstract class CWEntityLivingBase extends CWEntity implements EntityLivingBase {

	public CWEntityLivingBase(Server server, EntityType type) {
		super(server, type);
	}

	@Override
	public void onTick() {
	}

	@Override
	public boolean canSeeEntity(Entity entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canSeePosition(Position pos) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void sendMessage(String message) {
	}

	@Override
	public String getName() {
		return getEntityType().getName();
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

	@Override
	public boolean isEntityUndead() {
		return false;
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
