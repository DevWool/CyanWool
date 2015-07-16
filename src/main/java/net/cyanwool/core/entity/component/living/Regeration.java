package net.cyanwool.api.entity.component.living;

import net.cyanwool.api.entity.component.Component;

public interface Regeration extends Component {

	public int getRegenerationSpeedTicks();

	public void setRegenerationSpeedTicks(int regenSpeed);

	public double getRegenerationPoint();

	public void setRegenerationPoint(double regenPoint);
}
