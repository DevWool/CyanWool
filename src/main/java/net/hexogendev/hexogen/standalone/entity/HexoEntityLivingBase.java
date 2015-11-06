package net.hexogendev.hexogen.standalone.entity;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.spacehq.mc.protocol.data.game.EntityMetadata;
import org.spacehq.mc.protocol.packet.ingame.server.entity.ServerEntityHeadLookPacket;
import org.spacehq.mc.protocol.packet.ingame.server.entity.ServerEntityMetadataPacket;
import org.spacehq.mc.protocol.packet.ingame.server.entity.ServerEntityMovementPacket;
import org.spacehq.mc.protocol.packet.ingame.server.entity.ServerEntityPositionRotationPacket;
import org.spacehq.mc.protocol.packet.ingame.server.entity.ServerEntityRotationPacket;
import org.spacehq.mc.protocol.packet.ingame.server.entity.ServerEntityTeleportPacket;

import net.hexogendev.hexogen.api.entity.Entity;
import net.hexogendev.hexogen.api.entity.EntityLivingBase;
import net.hexogendev.hexogen.api.entity.controllers.EntityJumpController;
import net.hexogendev.hexogen.api.entity.controllers.EntityLookController;
import net.hexogendev.hexogen.api.network.IPacket;
import net.hexogendev.hexogen.api.utils.DamageSource;
import net.hexogendev.hexogen.api.utils.Rotation;
import net.hexogendev.hexogen.api.world.World;
import net.hexogendev.hexogen.api.world.sounds.Sound;
import net.hexogendev.hexogen.standalone.entity.metadata.MetadataHelper;

public abstract class HexoEntityLivingBase extends HexoEntity implements EntityLivingBase {

	protected Rotation headRotation;
	protected double maxHealth;
	protected EntityLivingBase target;
	protected Sound damageSound;
	protected Sound talkSound;
	protected Sound deathSound;
	protected EntityJumpController jumpController;
	protected EntityLookController lookController;

	public HexoEntityLivingBase(World world) {
		super(world);
	}

	@Override
	public void applyMetadata() {
		super.applyMetadata();
		getMetadataMap().setMetadata(MetadataHelper.NAME_TAG, "");// Display name
		getMetadataMap().setMetadata(MetadataHelper.SHOW_NAME_TAG, (byte) 0);// Render name tag
		getMetadataMap().setMetadata(MetadataHelper.HEALTH, (float) 20);// health
		getMetadataMap().setMetadata(MetadataHelper.POTION_COLOR, 0);// potion color
		getMetadataMap().setMetadata(MetadataHelper.POTION_AMBIENT, (byte) 0);// Is Potion Effect Ambient
		getMetadataMap().setMetadata(MetadataHelper.ARROW_COUNT, 0);// Number of Arrows in Entity
		getMetadataMap().setMetadata(MetadataHelper.NO_AI, (byte) 0);// No ai
	}

	@Override
	public Rotation getHeadRotation() {
		return headRotation;
	}

	@Override
	public void setHeadRotation(Rotation rotation) {
		this.headRotation = rotation;
	}

	@Override
	public void damage(float amount, DamageSource source) {
		// TODO Auto-generated method stub

	}

	@Override
	public void damage(float amount, DamageSource source, Entity damager) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getHealth() {
		return (double) getMetadataMap().getMetadata(MetadataHelper.HEALTH);
	}

	@Override
	public void setHealth(double health) {
		getMetadataMap().setMetadata(MetadataHelper.HEALTH, health);
	}

	@Override
	public double getMaxHealth() {
		return maxHealth;
	}

	@Override
	public void setMaxHealth(double maxHealth) {
		this.maxHealth = maxHealth;
	}

	@Override
	public void setTarget(EntityLivingBase entity) {
		if (entity.equals(this))
			return;
		this.target = entity;
	}

	@Override
	public EntityLivingBase getTarget() {
		return target;
	}

	@Override
	public Sound getDamageSound() {
		return damageSound;
	}

	@Override
	public Sound getTalkSound() {
		return talkSound;
	}

	@Override
	public Sound getDeathSound() {
		return deathSound;
	}

	@Override
	public void setDeathSound(Sound sound) {
		this.deathSound = sound;
	}

	@Override
	public void setDamageSound(Sound sound) {
		this.damageSound = sound;
	}

	@Override
	public void setTalkSound(Sound sound) {
		this.talkSound = sound;
	}

	@Override
	public boolean playDamageSound() {
		getWorld().playSound(getPosition(), getDamageSound(), 1, 1); // ???
		return true;
	}

	@Override
	public boolean playTalkSound() {
		getWorld().playSound(getPosition(), getTalkSound(), 1, 1); // ???
		return true;
	}

	@Override
	public boolean playDeathSound() {
		getWorld().playSound(getPosition(), getDeathSound(), 1, 1); // ???
		return true;
	}

	@Override
	public String getDisplayName() {
		return (String) getMetadataMap().getMetadata(MetadataHelper.NAME_TAG);// TEST
	}

	@Override
	public void setDisplayName(String name) {
		getMetadataMap().setMetadata(MetadataHelper.NAME_TAG, name);
		// TEST
		if (getTrackerEntry() != null) {
			getTrackerEntry().getViewerPlayers().clear();
			getTrackerEntry().update();
		}
	}

	@Override
	public boolean hasDisplayName() {
		return getDisplayName() != null;
	}

	@Override
	public boolean isRenderDisplayName() {
		return (boolean) getMetadataMap().getMetadata(MetadataHelper.SHOW_NAME_TAG);
	}

	@Override
	public void setRenderDisplayName(boolean flag) {
		getMetadataMap().setMetadata(MetadataHelper.SHOW_NAME_TAG, flag);
	}

	@Override
	public void interact(EntityLivingBase entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void kill() {
		// TODO
	}

	@Override
	public void kill(DamageSource source) {
		alive = false;
		setHealth(0);
		teleported = false;
		fireTicks = 0;
		livedTicks = 0;

		if (passenger != null) {
			// passenger.
		}

		getTrackerEntry().destroy();
	}

	@Override
	public void kill(Entity entity) {
		// TODO
	}

	@Override
	public EntityJumpController getJumpController() {
		return jumpController;
	}

	@Override
	public EntityLookController getLookController() {
		return lookController;
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

	@Override
	public List<IPacket> getUpdatePackets() {
		CopyOnWriteArrayList<IPacket> list = new CopyOnWriteArrayList<IPacket>();

		int dx = getPosition().getBlockX() - getPreviousPosition().getBlockX();
		int dy = getPosition().getBlockY() - getPreviousPosition().getBlockY();
		int dz = getPosition().getBlockZ() - getPreviousPosition().getBlockZ();

		if (isTeleported() || isTeleported() && isMoved()) {
			list.add(new ServerEntityTeleportPacket(getEntityID(), getPosition().getX(), getPosition().getY(), getPosition().getZ(), getBodyRotation().getYaw(), getBodyRotation().getPitch(), onGround()));
		} else if (isMoved() && getBodyRotation().isRotated()) {
			list.add(new ServerEntityPositionRotationPacket(getEntityID(), dx, dy, dz, getBodyRotation().getYaw(), getBodyRotation().getPitch(), onGround()));
		} else if (isMoved()) {
			list.add(new ServerEntityMovementPacket(getEntityID(), onGround()));
		} else if (getBodyRotation().isRotated()) {
			list.add(new ServerEntityRotationPacket(getEntityID(), getBodyRotation().getYaw(), getBodyRotation().getPitch(), onGround()));
		}

		if (getHeadRotation().isRotated()) {
			list.add(new ServerEntityHeadLookPacket(getEntityID(), getHeadRotation().getYaw()));
		}

		EntityMetadata[] changes = getMetadataMap().getChanges();
		if (changes.length > 0) {
			list.add(new ServerEntityMetadataPacket(getEntityID(), changes));
		}

		// TODO: Velocity
		return list;
	}
}
