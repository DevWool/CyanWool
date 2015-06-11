package net.devwool.cyanwool.api.material.blocks;

import net.devwool.cyanwool.api.material.BlockMaterial;

public abstract class MinecraftBlockMaterial extends BlockMaterial {

    public MinecraftBlockMaterial(String StringId, int Id) {
        super("minecraft:" + StringId, Id);
    }
}