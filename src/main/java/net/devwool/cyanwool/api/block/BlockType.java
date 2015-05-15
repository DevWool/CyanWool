package net.devwool.cyanwool.api.block;

import net.devwool.cyanwool.api.entity.Entity;
import net.devwool.cyanwool.api.entity.EntityLivingBase;
import net.devwool.cyanwool.api.entity.player.Player;
import net.devwool.cyanwool.api.item.ItemStack;
import net.devwool.cyanwool.api.item.ItemType;
import net.devwool.cyanwool.api.utils.BlockSide;
import net.devwool.cyanwool.api.world.Position;
import net.devwool.cyanwool.api.world.World;

import org.spacehq.mc.protocol.data.game.values.world.Sound;

public interface BlockType extends ItemType {

    public Sound getStepSound();

    public int getLightLevel();

    public void setLightLevel(int level);

    public float getResistance();

    public void setResistance(float resistance);

    public void setHardness(float hardness);

    public float getHardness();

    public float setUnbreakable(boolean flag);

    public float isUnbreakable();

    public void updateTick();

    public void dropBlock();

    public void spawnDrop(World world, Position pos, ItemStack stack);

    public boolean canPlaceBlockOnSide(World world, Position pos, BlockSide side);

    public boolean canPlaceBlockAt(World world, Position pos);

    // Events

    public void onPlayerBreakBlock(World world, Position pos, Player player);

    public void onPlayerPlaceBlock(World world, Position pos, Player player);

    // public void onBlockDestroyedByExplosion(World world, Position pos,
    // Explosion explosionIn) {} - TODO

    // public boolean onBlockActivated(); // - TODO: Redstone update

    public void onEntityCollidedWithBlock(World world, Position pos, Entity entity);

    public void onBlockClicked(World worldIn, Position pos, Player player);

    public void onBlockPlacedBy(World worldIn, Position pos, Block block, EntityLivingBase placer, ItemStack stack);
}