package net.cyanwool.api.entity.component.living.enderman;

import net.cyanwool.api.entity.component.Component;
import net.cyanwool.api.material.Material;

public interface CarriedBlock extends Component {

	public Material getMaterial();

	public void setMaterial(Material material);

}
