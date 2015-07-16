package net.cyanwool.api.entity.component.living.zombie;

import net.cyanwool.api.entity.component.Component;

public interface ZombieVillagerHealing extends Component {

	public int getHealTime();

	public void setHealTime(int ticks);

	public void start();

	public void stop();

	public boolean isPaused();

	public void pause();
}
