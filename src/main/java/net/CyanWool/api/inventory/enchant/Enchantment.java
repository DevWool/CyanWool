package net.CyanWool.api.inventory.enchant;

import net.CyanWool.api.inventory.ItemStack;

public class Enchantment {

    private int weight;
    private int minLvl;
    private int maxLvl;
    private String name;
    private String StringId;
    private int id;

    public Enchantment(int id, String stringId, String name, int weight, int minLvl, int maxLvl) {
        this.id = id;
        this.StringId = stringId;
        this.name = name;
        this.weight = weight;
        this.minLvl = minLvl;
        this.maxLvl = maxLvl;
    }

    public int getWeight() {
        return weight;
    }

    public int getMinLvl() {
        return minLvl;
    }

    public int getMaxLvl() {
        return maxLvl;
    }

    public String getName() {
        return name;
    }

    public void setString(String name) {
        this.name = name;
    }

    /**
     * Example: "protection" , "projectile_protection"
     * TODO: Maybe other ?
     * 
     * @return id
     */
    public String getStringID() {
        return StringId;
    }

    public int getID() {
        return id;
    }

    public int getMinEnchantability(int i) {
        return 1 + i * 10;
    }

    public int getMaxEnchantability(int i) {
        return this.getMinEnchantability(i) + 5;
    }

    public boolean canApply(ItemStack itemStack) {
        return false; // TODO: Add enchantTypes...
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (!(other instanceof Enchantment)) {
            return false;
        } else {
            Enchantment enchant = (Enchantment) other;
            return this.getID() == enchant.getID() && this.getStringID().equals(enchant.getStringID());
        }
    }

}