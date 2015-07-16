package net.cyanwool.api.entity.component.living;

import net.cyanwool.api.entity.Entity;
import net.cyanwool.api.entity.component.Component;
import net.cyanwool.api.utils.DamageSource;

public interface Health extends Component {

	public double getHealth();

	public void setHealth(double health);

	public double getMaxHealth();

	public void setMaxHealth(double maxHealth);

	public void damage(float amount, DamageSource source);

	public void damage(float amount, DamageSource source, Entity damager);

	public void kill();

	public void kill(DamageSource source);

	public void kill(Entity entity);
}
