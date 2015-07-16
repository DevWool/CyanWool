package net.cyanwool.api.entity.component.living.enderman;

import net.cyanwool.api.entity.component.Component;

public interface Teleport extends Component {

	public double getTeleportRadius();

	public void setTeleportRadius(double radius);

	public void teleportRandomly();
}
