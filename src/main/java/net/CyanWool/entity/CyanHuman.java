package net.CyanWool.entity;

import net.CyanWool.api.Gamemode;
import net.CyanWool.api.entity.EntityType;
import net.CyanWool.api.entity.player.Human;
import net.CyanWool.api.inventory.Inventory;
import net.CyanWool.api.inventory.ItemStack;
import net.CyanWool.api.world.Location;

import org.spacehq.mc.auth.GameProfile;

public class CyanHuman extends CyanEntityLivingBase implements Human {

    private GameProfile profile;
    private String displayName;
    private boolean sleeping;
    private boolean blocking;

    private int foodLevel;
    private int xpLevel;
    private int xpTotal;
    private float xpInBar;
    private boolean disableDamage;
    private boolean isFlying;
    private boolean isAllowFlying;
    private boolean isCreative;
    private float flySpeed;
    private float walkSpeed;
    private boolean canBuild;
    private Inventory viewInventory;
    private Gamemode gameMode;

    public CyanHuman(GameProfile profile, Location location) {
        super(location);// TODO
        this.profile = profile;
        this.displayName = profile.getName();
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public void setDisplayName(String name) {
        this.displayName = name;
        // update...
    }

    @Override
    public String getName() {
        return profile.getName();
    }

    @Override
    public boolean hasItemInHand() {
        return getItemInHand() != null;
    }

    @Override
    public void setItemInHand(ItemStack item) {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean isSleeping() {
        return sleeping;
    }

    @Override
    public boolean isBlocking() {
        return blocking;
    }

    @Override
    public void sleepInBedAt(int x, int y, int z) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isNeedFood() {
        return getFoodLevel() < 20;
    }

    @Override
    public int getFoodLevel() {
        return foodLevel;
    }

    @Override
    public void setFoodLevel(int level) {
        this.foodLevel = level;
    }

    @Override
    public void closeInventory() {
        if (viewInventory != null) {
            viewInventory.closeInventory(this);
        }
    }

    @Override
    public int getXPLevel() {
        return xpLevel;
    }

    @Override
    public int getXPTotal() {
        return xpTotal;
    }

    @Override
    public float getXPInBar() {
        return xpInBar;
    }

    @Override
    public void setXPLevel(int level) {
        this.xpLevel = level;
    }

    @Override
    public void setXPTotal(int xp) {
        this.xpTotal = xp;
    }

    @Override
    public void setXPInBar(float xp) {
        this.xpInBar = xp;
    }

    @Override
    public boolean isDisableDamage() {
        return disableDamage;
    }

    @Override
    public void setDisableDamage(boolean flag) {
        this.disableDamage = flag;
    }

    @Override
    public boolean isFlying() {
        return isFlying;
    }

    @Override
    public void setFlying(boolean flag) {
        this.isFlying = flag;
    }

    @Override
    public boolean isAllowFlying() {
        return isAllowFlying;
    }

    @Override
    public void setAllowFlying(boolean flag) {
        this.isAllowFlying = flag;
    }

    @Override
    public boolean isCreativeMode() {
        return isCreative;
    }

    @Override
    public float getFlySpeed() {
        return flySpeed;
    }

    @Override
    public void setFlySpeed(float speed) {
        this.flySpeed = speed;
    }

    @Override
    public float getWalkSpeed() {
        return walkSpeed;
    }

    @Override
    public void setWalkSpeed(float speed) {
        this.walkSpeed = speed;
    }

    @Override
    public boolean canBuild() {
        return canBuild;
    }

    @Override
    public void setBuild(boolean flag) {
        this.canBuild = flag;
    }

    @Override
    public void wakeUp() {
        this.sleeping = false;
        // todo...
    }

    @Override
    public Inventory getViewInventory() {
        return viewInventory;
    }

    @Override
    public Inventory getEnderChest() {
        return null;// TODO ?
    }

    @Override
    public void openInventory(Inventory inventory) {
        closeInventory();
        inventory.openInventory(this);
        viewInventory = inventory;
    }

    @Override
    public Gamemode getGameMode() {
        return gameMode;
    }

    @Override
    public void setGamemode(Gamemode mode) {
        this.gameMode = mode;
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.PLAYER;
    }

    // Not from API

    public GameProfile getGameProfile() {
        return profile;
    }
}