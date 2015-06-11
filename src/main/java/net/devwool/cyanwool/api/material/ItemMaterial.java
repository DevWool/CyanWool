package net.devwool.cyanwool.api.material;

import net.devwool.cyanwool.api.block.Block;
import net.devwool.cyanwool.api.entity.EntityLivingBase;
import net.devwool.cyanwool.api.entity.player.Player;
import net.devwool.cyanwool.api.item.ItemStack;
import net.devwool.cyanwool.api.utils.BlockSide;
import net.devwool.cyanwool.api.world.Position;
import net.devwool.cyanwool.api.world.World;

public abstract class ItemMaterial extends Material {

    private int maxStackSize;
    private int maxDurability;
    private int maxAttackDamage;
    private boolean damageable;

    public ItemMaterial(String StringId, int Id) {
        super(StringId, Id);
    }

    public void setMaxStackSize(int max) {
        maxStackSize = max;
    }

    public int getMaxStackSize() {
        return maxStackSize;
    }

    public int getMaxDurability() {
        return maxDurability;
    }

    public void setMaxDurability(int durability) {
        maxDurability = durability;
    }

    public int getMaxAttackDamage() {
        return maxAttackDamage;
    }

    public void setMaxAttackDamage(int max) {
        maxAttackDamage = max;
    }

    public boolean isDamageable() {
        return damageable;
    }

    public void setDamageable(boolean flag) {
        damageable = flag;
    }

    public boolean onItemUse(ItemStack stack, Player player, World world, Position pos, BlockSide side) {
        return true;
    }

    public boolean onItemRightClick(ItemStack itemStackIn, World worldIn, Player playerIn) {
        return true;
    }

    public boolean onItemLeftClick(ItemStack itemStackIn, World worldIn, Player playerIn) {
        return true;
    }

    public boolean onItemUseFinish(ItemStack stack, World worldIn, Player playerIn) {
        return true;
    }

    public boolean onHitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        return true;
    }

    public boolean onBlockDestroyed(ItemStack stack, World worldIn, Block blockIn, Position pos, EntityLivingBase playerIn) {
        return true;
    }

    public void onCreatedItem(ItemStack stack, World worldIn, Player playerIn) {
    }

    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, Player playerIn, int timeLeft) {
    }
}