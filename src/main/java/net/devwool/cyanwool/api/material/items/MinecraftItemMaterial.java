package net.devwool.cyanwool.api.material.items;

import net.devwool.cyanwool.api.material.ItemMaterial;

public abstract class MinecraftItemMaterial extends ItemMaterial {

    public MinecraftItemMaterial(String StringId, int Id) {
        super("minecraft:" + StringId, Id);
    }

}