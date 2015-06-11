package net.devwool.cyanwool.core;

import net.devwool.cyanwool.api.CyanWool;
import net.devwool.cyanwool.api.material3.blocks.BlockMaterial;
import net.devwool.cyanwool.api.material3.blocks.MaterialDirt;
import net.devwool.cyanwool.api.material3.blocks.MaterialStone;
import net.devwool.cyanwool.api.material3.items.ToolItemMaterial;
import net.devwool.cyanwool.api.packs.ServerPack;

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