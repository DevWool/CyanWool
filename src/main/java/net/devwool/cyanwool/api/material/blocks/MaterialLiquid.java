package net.devwool.cyanwool.api.material.blocks;

import net.devwool.cyanwool.api.block.Block;
import net.devwool.cyanwool.api.entity.player.Player;
import net.devwool.cyanwool.api.item.ItemStack;

public class MaterialLiquid extends MinecraftBlockMaterial {

    public static MaterialLiquid FLOWING_WATER = new MaterialLiquid("flowing_water", 8, 0, 0, true);
    public static MaterialLiquid WATER = new MaterialLiquid("water", 9, 0, 0, false);
    public static MaterialLiquid FLOWING_LAVA = new MaterialLiquid("flowing_lava", 10, 0, 0, true);
    public static MaterialLiquid LAVA = new MaterialLiquid("lava", 11, 0, 0, false);

    private boolean flowing;

    MaterialLiquid(String stringId, int id, int metadata, int customMetadata, boolean flowing) {
        super(stringId, id);
        setMinecraftData(metadata);
        setCustomData(customMetadata);
        setFlowing(flowing);
    }

    @Override
    public boolean onBlockDigging(Block block, Player player, ItemStack item) {
        return false;
    }

    public boolean isFlowing() {
        return flowing;
    }

    public void setFlowing(boolean flag) {
        this.flowing = flag;
    }
}