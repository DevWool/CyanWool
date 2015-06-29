package net.devwool.cyanwool.api.material3.items;

import net.devwool.cyanwool.api.entity.EntityLivingBase;
import net.devwool.cyanwool.api.entity.alive.player.Player;
import net.devwool.cyanwool.api.item.ItemStack;
import net.devwool.cyanwool.api.material3.Material;
import net.devwool.cyanwool.api.utils.BlockSide;
import net.devwool.cyanwool.api.world.Position;
import net.devwool.cyanwool.api.world.World;

public class ToolItemMaterial extends ItemMaterial {

    private boolean damageable;
    private int maxDurability;

    public ToolItemMaterial(String StringId, int Id, int metadata, int customData) {
        super(StringId, Id, metadata, customData);
    }

    public ToolItemMaterial(String stringId, int Id, int metadata) {
        this(stringId, Id, metadata, 0);
    }

    public ToolItemMaterial(String stringId, int Id) {
        this(stringId, Id, 0);
    }

    public ToolItemMaterial(Material material) {
        this(material.getStringId(), material.getId(), material.getMinecraftData(), material.getCustomData());
    }

    public int getMaxDurability() {
        return maxDurability;
    }

    public void setMaxDurability(int durability) {
        maxDurability = durability;
    }

    public boolean isDamageable() {
        return damageable;
    }

    public void setDamageable(boolean flag) {
        damageable = flag;
    }

    @Override
    public boolean onItemUse(ItemStack stack, Player player, World world, Position pos, BlockSide side) {
        stack.setDurability(stack.getDurability() - 1);
        return true;
    }

    @Override
    public boolean onHitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        stack.setDurability(stack.getDurability() - 1);
        return true;
    }

}