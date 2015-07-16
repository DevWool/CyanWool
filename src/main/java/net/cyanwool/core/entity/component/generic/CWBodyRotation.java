package net.cyanwool.core.entity.component.generic;

import org.spacehq.mc.protocol.packet.ingame.server.entity.ServerEntityRotationPacket;

import net.cyanwool.api.entity.Entity;
import net.cyanwool.api.entity.component.generic.BodyRotation;
import net.cyanwool.api.utils.Rotation;
import net.cyanwool.core.entity.component.CWComponent;
import net.cyanwool.core.network.MCProtocolPacket;

public class CWBodyRotation extends CWComponent implements BodyRotation {

	private Rotation rotation;

	public CWBodyRotation(Entity entity) {
		super(entity, "bodyRotation");
		setBodyRotation(new Rotation(0, 0));
	}

	@Override
	public boolean autoUpdate() {
		return false;
	}

	@Override
	public Rotation getBodyRotation() {
		return rotation;
	}

	@Override
	public void setBodyRotation(Rotation rotation) {
		this.rotation = rotation;
		ServerEntityRotationPacket packet = new ServerEntityRotationPacket(getEntity().getEntityID(), getBodyRotation().getYaw(), getBodyRotation().getPitch(), getEntity().onGround());
		getEntity().getServer().getNetworkServer().sendPacketDistance(getEntity().getPosition(), new MCProtocolPacket(packet), getEntity().getServer().getServerConfiguration().getRadiusViewDistance());
	}

	@Override
	public void update() {
	}
}
