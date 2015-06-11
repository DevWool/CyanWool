package net.devwool.cyanwool.api.material.blocks;

import net.devwool.cyanwool.api.block.Block;
import net.devwool.cyanwool.api.entity.player.Player;
import net.devwool.cyanwool.api.item.ItemStack;

public class MaterialBedrock extends MinecraftBlockMaterial {

    public MaterialBedrock BEDROCK = new MaterialBedrock("bedrock", 7, 0, 0);

    MaterialBedrock(String stringId, int id, int metadata, int customMetadata) {
        super(stringId, id);
        setMinecraftData(metadata);
        setCustomData(customMetadata);
        setSolid(true);
    }

    @Override
    public boolean onBlockDigging(Block block, Player player, ItemStack item) {
        return false;
    }
}