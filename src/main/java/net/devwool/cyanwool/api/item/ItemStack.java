package net.devwool.cyanwool.api.item;

import org.spacehq.opennbt.tag.builtin.CompoundTag;
import org.spacehq.opennbt.tag.builtin.StringTag;

public class ItemStack {

    private ItemType itemType;
    private int itemDamage;
    private int amount;
    private CompoundTag tag;

    public ItemStack(ItemType type, int amount, int damage) {
        this.itemType = type;
        this.amount = amount;
        this.itemDamage = damage;
    }

    public ItemStack(ItemType type, int amount) {
        this(type, amount, 0);
    }

    public ItemStack(ItemType type) {
        this(type, 1, 0);
    }

    public ItemType getItemType() {
        return itemType;
    }

    public int getData() {
        return getItemType().getData();
    }

    public void setData(int data) {
        getItemType().setData(data);
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
        return getItemType().isDamageable() && getItemDamage() > 0;
    }

    public int getItemDamage() {
        return itemDamage;
    }

    public void setItemDamage(int meta) {
        this.itemDamage = meta;
    }

    public int getMaxItemUseDuration() {
        return getItemType().getMaxItemUseDuration(this);
    }

    public Action getItemUseAction() {
        return getItemType().getItemUseAction(this);
    }

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
}