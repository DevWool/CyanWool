package net.devwool.cyanwool.api.item;

import net.devwool.cyanwool.api.material.ItemMaterial;

import org.spacehq.opennbt.tag.builtin.CompoundTag;
import org.spacehq.opennbt.tag.builtin.StringTag;

public class ItemStack {

    private ItemMaterial itemType;
    private int durability;
    private int amount;
    private CompoundTag tag;

    public ItemStack(ItemMaterial type, int amount, int durability) {
        this.itemType = type;
        this.amount = amount;
        this.durability = durability;
    }

    public ItemStack(ItemMaterial type, int amount) {
        this(type, amount, 0);
    }

    public ItemStack(ItemMaterial type) {
        this(type, 1, 0);
    }

    public ItemMaterial getItemType() {
        return itemType;
    }

    public int getMinecraftData() {
        return getItemType().getMinecraftData();
    }

    public void setMinecraftData(int data) {
        getItemType().setMinecraftData(data);
    }

    public int getCustomData() {
        return getItemType().getCustomData();
    }

    public void setCustomData(int data) {
        getItemType().setCustomData(data);
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        if (amount < 0) {
            amount = 0;
        }
        this.amount = amount;
    }

    public boolean isItemDamaged() {
        return getItemType().isDamageable() && getDurability() > 0;
    }

    // public int getMaxItemUseDuration() {
    // return getItemType().getMaxItemUseDuration(this);
    // }

    // public Action getItemUseAction() {
    // return getItemType().getItemUseAction(this);
    // }

    public String getDisplayName() {
        String name = getItemType().getStringId();
        if (getCompoundTag() != null && getCompoundTag().contains("display")) {
            CompoundTag tag = getCompoundTag().get("display");
            if (tag.contains("Name")) {
                name = ((StringTag) tag.get("Name")).getValue();
            }
        }
        return name;
    }

    public void setDisplayName(String name) {
        if (this.tag == null) {
            this.tag = new CompoundTag("");
        }

        if (!getCompoundTag().contains("display")) {
            getCompoundTag().put(new CompoundTag("Name"));
        }

        ((CompoundTag) getCompoundTag().get("display")).put(new StringTag("Name", name));
    }

    public void clearCustomName() {
        // TODO
    }

    public boolean hasDisplayName() {
        return getDisplayName() != null;
    }

    // NBT
    public void loadCompoundTag(CompoundTag tag) {
    }

    public void saveCompoundTag(CompoundTag tag) {
    }

    public CompoundTag getCompoundTag() {
        return tag;
    }

    public void setCompountTag(CompoundTag tag) {
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }
}