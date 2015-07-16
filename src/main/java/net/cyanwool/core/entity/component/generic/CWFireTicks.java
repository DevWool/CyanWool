package net.cyanwool.core.entity.component.generic;

import net.cyanwool.api.entity.Entity;
import net.cyanwool.api.entity.component.generic.FireTicks;
import net.cyanwool.core.entity.component.CWComponent;

public class CWFireTicks extends CWComponent implements FireTicks {

	private int ticks;

	public CWFireTicks(Entity entity) {
		super(entity, "fireTicks");
	}

	@Override
	public void update() {
		if (getFireTicks() > 0) {
			ticks--;
		} else if (getFireTicks() < 0) {
			ticks = 0;
		}
	}

	@Override
	public boolean autoUpdate() {
		return true;
	}

	@Override
	public boolean isBurned() {
		return getFireTicks() > 0;
	}

	@Override
	public int getFireTicks() {
		return ticks;
	}

	@Override
	public void setFireTicks(int ticks) {
		this.ticks = ticks;
	}
}
