package net.devwool.cyanwool.api.block;

import net.devwool.cyanwool.api.material.BlockMaterial;

public interface BlockState {

    public Block getBlock();

    public BlockMaterial getBlockMaterial();

}