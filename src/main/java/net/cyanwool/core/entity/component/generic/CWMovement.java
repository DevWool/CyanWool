package net.cyanwool.core.entity.component.generic;

import net.cyanwool.api.entity.Entity;
import net.cyanwool.api.entity.component.generic.BodyRotation;
import net.cyanwool.api.entity.component.generic.Movement;
import net.cyanwool.api.world.Position;
import net.cyanwool.core.entity.component.CWComponent;
import net.cyanwool.core.network.MCProtocolPacket;

import org.spacehq.mc.protocol.packet.ingame.server.entity.ServerEntityTeleportPacket;

public class CWMovement extends CWComponent implements Movement{

	private float speed;
	
	public CWMovement(Entity entity) {
		super(entity, "movement");
	}

	@Override
	public void update() {
	}

	@Override
	public boolean autoUpdate() {
		return false;
	}

	@Override
	public float getMovementSpeed() {
		return speed;
	}

	@Override
	public void setMovementSpeed(float speed) {
		this.speed = speed;
	}

	@Override
	public void moveEntity(double x, double y, double z) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveEntity(Position pos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveEntity(Entity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void teleport(Position pos) {
		getEntity().getPosition().setX(pos.getX());
		getEntity().getPosition().setY(pos.getY());
		getEntity().getPosition().setZ(pos.getZ());
		
		BodyRotation rotation = (BodyRotation) getEntity().getComponentManager().getComponent(BodyRotation.class);
		ServerEntityTeleportPacket packet = new ServerEntityTeleportPacket(getEntity().getEntityID(), getEntity().getPosition().getX(), getEntity().getPosition().getY(), getEntity().getPosition().getZ(), rotation.getBodyRotation().getYaw(), rotation.getBodyRotation().getPitch(), getEntity().onGround());
		getEntity().getServer().getNetworkServer().sendPacketDistance(pos, new MCProtocolPacket(packet), getEntity().getServer().getServerConfiguration().getRadiusViewDistance());
	}

}
