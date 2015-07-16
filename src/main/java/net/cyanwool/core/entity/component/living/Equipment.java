package net.cyanwool.api.entity.component.living;

import net.cyanwool.api.entity.component.Component;
import net.cyanwool.api.inventory.item.ItemStack;

public interface Equipment extends Component {

	public ItemStack getHelmet();

	public void setHelmet(ItemStack item);

	public ItemStack getChestplate();

	public void setChestplate(ItemStack item);

	public ItemStack getLeggins();

	public void setLeggins(ItemStack item);

	public ItemStack getBoots();

	public void setBoots(ItemStack item);

	public ItemStack getItemInHand();

	public void setItemInHand(ItemStack item);
}
