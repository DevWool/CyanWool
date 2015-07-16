package net.cyanwool.api.entity.component.living.player;

public interface PlayerCapabilities {

	public boolean isDisableDamage();

	public void setDisableDamage(boolean flag);

	public boolean isFlying();

	public void setFlying(boolean flag);

	public boolean isAllowFlying();

	public void setAllowFlying(boolean flag);

	public float getFlySpeed();

	public void setFlySpeed(float speed);

	public float getWalkSpeed();

	public void setWalkSpeed(float speed);

	public boolean canBuild();

	public void setBuild(boolean flag);

}
