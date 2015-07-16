package net.cyanwool.core.entity.component.generic;

import net.cyanwool.api.entity.Entity;
import net.cyanwool.api.entity.component.generic.LivingTicks;
import net.cyanwool.core.entity.component.CWComponent;

public class CWLivingTicks extends CWComponent implements LivingTicks{

	private int ticks;

	public CWLivingTicks(Entity entity) {
		super(entity, "livingTicks");
	}

	@Override
	public void update() {
		if(getEntity().isAlive()){
			ticks++;
		}
	}

	@Override
	public boolean autoUpdate() {
		return true;
	}

	@Override
	public int getLivedTicks() {
		return ticks;
	}

	@Override
	public void setLivedTicks(int ticks) {
		this.ticks = ticks;
	}


}
