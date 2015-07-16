package net.cyanwool.core.entity.component.generic;

import net.cyanwool.api.entity.Entity;
import net.cyanwool.api.entity.component.generic.Combust;
import net.cyanwool.api.entity.component.generic.FireTicks;
import net.cyanwool.core.entity.component.CWComponent;

public class CWCombust extends CWComponent implements Combust {

	private boolean combust;

	public CWCombust(Entity entity) {
		super(entity, "combust");
	}

	public boolean canCombust() {
		return combust;
	}

	public void setCombust(boolean flag) {
		this.combust = flag;
	}

	@Override
	public void update() {
		if (getEntity().getWorld().getWorldTime() > 0 && getEntity().getWorld().getWorldTime() < 12000) {
			FireTicks fire = (FireTicks) getEntity().getComponentManager().getComponent(FireTicks.class);
			// TODO: Check up block
			if (!fire.isBurned()) {
				fire.setFireTicks(1000);
			}
		}
	}

	@Override
	public boolean autoUpdate() {
		return true;
	}

}
