package net.cyanwool.core.entity.component.generic;

import net.cyanwool.api.entity.Entity;
import net.cyanwool.api.entity.component.generic.Transport;
import net.cyanwool.core.entity.component.CWComponent;

public class CWTransport extends CWComponent implements Transport {

	public CWTransport(Entity entity) {
		super(entity, "transport");
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean autoUpdate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setPassenger(Entity entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public Entity getPassenger() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entity getVehicle() {
		// TODO Auto-generated method stub
		return null;
	}

}
