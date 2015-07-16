package net.cyanwool.core;

import net.cyanwool.api.CyanWool;
import net.cyanwool.api.material.blocks.BlockMaterial;
import net.cyanwool.api.material.blocks.MaterialDirt;
import net.cyanwool.api.material.blocks.MaterialStone;
import net.cyanwool.api.material.items.ToolItemMaterial;
import net.cyanwool.api.packs.ServerPack;

public class MinecraftServerPack implements ServerPack {

	@Override
	public String getName() {
		return "Vanilla-Standart";
	}

	@Override
	public void registerItems() {
		CyanWool.getRegistry().registerMaterial("minecraft", new ToolItemMaterial("iron_shovel", 256));
	}

	@Override
	public void registerBlocks() {
		CyanWool.getRegistry().registerMaterial("minecraft", new BlockMaterial("air", 0));
		CyanWool.getRegistry().registerMaterial("minecraft", new MaterialStone());
		CyanWool.getRegistry().registerMaterial("minecraft", new MaterialDirt());
	}

	@Override
	public void registerWorlds() {
	}

	@Override
	public void reigsterRecipes() {

	}

	@Override
	public void registerEnchants() {
		// TODO Auto-generated method stub

	}
}
