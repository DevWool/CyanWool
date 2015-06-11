package net.devwool.cyanwool.api.material;

import net.devwool.cyanwool.api.block.Block;
import net.devwool.cyanwool.api.entity.EntityLivingBase;
import net.devwool.cyanwool.api.entity.player.Player;
import net.devwool.cyanwool.api.item.ItemStack;
import net.devwool.cyanwool.api.world.Position;
import net.devwool.cyanwool.api.world.World;

public abstract class BlockMaterial extends Material {

    private boolean transparent;
    private boolean flammable;
    private boolean burnable;
    private boolean gravity;
    private boolean edible;
    private boolean replaceable;
    private boolean glowing;
    private boolean solid;

    public BlockMaterial(String StringId, int Id) {
        super(StringId, Id);
    }

    public boolean isTransparent() {
        return transparent;
    }

    public boolean isFlammable() {
        return flammable;
    }

    public boolean isBurnable() {
        return burnable;
    }

    public boolean hasGravity() {
        return gravity;
    }

    public boolean isEdible() {
        return edible;
    }

    public boolean isReplaceable() {
        return replaceable;
    }

    public boolean isGlowing() {
        return glowing;
    }

    public boolean isSolid() {
        return solid;
    }

    public BlockMaterial setTransparent(boolean flag) {
        transparent = flag;
        return this;
    }

    public BlockMaterial setFlammable(boolean flag) {
        flammable = flag;
        return this;
    }

    public BlockMaterial setBurnable(boolean flag) {
        burnable = flag;
        return this;
    }

    public BlockMaterial setGravity(boolean flag) {
        gravity = flag;
        return this;
    }

    public BlockMaterial setEdible(boolean flag) {
        edible = flag;
        return this;
    }

    public BlockMaterial setGlowing(boolean flag) {
        glowing = flag;
        return this;
    }

    public BlockMaterial setSolid(boolean flag) {
        solid = flag;
        return this;
    }

    public boolean onBlockInteract(Block block, Player player, ItemStack item) {
        return true;
    }

    public boolean onPlayerBreakBlock(World world, Block block, Player player) {
        return true;
    }

    public boolean onPlayerPlaceBlock(World world, Position pos, Player player) {
        return true;
    }

    public boolean onBlockPlacedBy(World worldIn, Position pos, Block block, EntityLivingBase placer, ItemStack stack) {
        return true;
    }

    public boolean onBlockDigging(Block block, Player player, ItemStack item) {
        return true;
    }
}