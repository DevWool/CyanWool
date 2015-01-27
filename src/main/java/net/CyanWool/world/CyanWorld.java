package net.CyanWool.world;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.CyanWool.api.Difficulty;
import net.CyanWool.api.Effect;
import net.CyanWool.api.Gamemode;
import net.CyanWool.api.Sound;
import net.CyanWool.api.block.Block;
import net.CyanWool.api.entity.Entity;
import net.CyanWool.api.entity.EntityLivingBase;
import net.CyanWool.api.entity.player.Player;
import net.CyanWool.api.inventory.ItemStack;
import net.CyanWool.api.io.PlayerIOService;
import net.CyanWool.api.world.Dimension;
import net.CyanWool.api.world.Location;
import net.CyanWool.api.world.World;
import net.CyanWool.api.world.chunks.Chunk;
import net.CyanWool.api.world.chunks.ChunkManager;
import net.CyanWool.entity.CyanEntity;
import net.CyanWool.entity.CyanPlayer;
import net.CyanWool.io.CyanChunkIOService;

import org.spacehq.packetlib.packet.Packet;

public class CyanWorld implements World {

    // private WorldInfo info;
    private ChunkManager chunk;

    private String name;
    private Location spawn;
    private long dayTime;
    private long seed;
    private boolean thundering;
    private int thunderingTime;
    private boolean raining;
    private int rainingTime;
    private PlayerIOService service;
    private String path;
    private List<Entity> entities;

    public CyanWorld(String name, PlayerIOService service) {
        this.name = name;
        this.service = service;
        this.path = "worlds/" + name;
        this.chunk = new ChunkManager(this, new CyanChunkIOService(this), null);
        this.entities = new ArrayList<Entity>();
        // this.chunk.loadChunk(getSpawnLocation().getBlockX() >> 4,
        // getSpawnLocation().getBlockZ() >> 4, false);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Block getBlock(Location location) {
        return location.getChunk().getBlock(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    @Override
    public Chunk getChunkFromChunkCoords(int x, int z) {
        return chunk.getChunk(x, z);
    }

    @Override
    public Chunk getChunkFromBlockCoords(int x, int z) {
        return chunk.getChunk(x >> 4, z >> 4);// ?
    }

    @Override
    public Location getSpawnLocation() {
        return spawn;
    }

    @Override
    public void setSpawnLocation(Location loc) {
        this.spawn = loc;
    }

    @Override
    public boolean isDaytime() {
        return false;
    }

    @Override
    public boolean spawnEntity(Entity entity) {
        for (Packet packet : ((CyanEntity) entity).getSpawnPackets()) {
            for (Player player : getPlayers()) {
                ((CyanPlayer) player).getPlayerNetwork().sendPacket(packet);
                return true;
            }
        }
        return false;
    }

    @Override
    public void createExplosion(Entity entity, Location location, float strength, boolean isFlaming) {
        // TODO Auto-generated method stub

    }

    @Override
    public void createExplosion(Location location, float strength, boolean isFlaming) {
        // TODO Auto-generated method stub

    }

    @Override
    public long getSeed() {
        return seed;
    }

    @Override
    public long getTotalWorldTime() {
        return 24000;
    }

    @Override
    public long getWorldTime() {
        return dayTime;
    }

    @Override
    public void setWorldTime(long time) {
        this.dayTime = time;
    }

    @Override
    public boolean isThundering() {
        return thundering;
    }

    @Override
    public void setThundering(boolean flag) {
        this.thundering = flag;
    }

    @Override
    public int getThunderTime() {
        return thunderingTime;
    }

    @Override
    public void setThunderTime(int time) {
        this.thunderingTime = time;
    }

    @Override
    public boolean isRaining() {
        return raining;
    }

    @Override
    public void setRaining(boolean rain) {
        this.raining = rain;
    }

    @Override
    public int getRainTime() {
        return rainingTime;
    }

    @Override
    public void setRainTime(int time) {
        this.rainingTime = time;
    }

    @Override
    public int getActualHeight() {
        return 256;
    }

    @Override
    public List<Entity> getEntities() {
        return entities;
    }

    @Override
    public List<EntityLivingBase> getLivingEntities() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Player> getPlayers() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onTick() {
        Iterator<Entity> it = entities.iterator();
        while (it.hasNext()) {
            Entity entity = it.next();
            entity.onTick();
        }
    }

    @Override
    public void playSoundAtEntity(Entity entity, String sound, float volume, float pitch) {
        this.playSound(entity.getLocation(), sound, volume, pitch);
    }

    @Override
    public void dropItemStack(ItemStack item) {
        // TODO Auto-generated method stub

    }

    @Override
    public Dimension getDimension() {
        return null;
    }

    @Override
    public Difficulty getDifficulty() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Gamemode getDefaultGamemode() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean getGamerule(String rule) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void saveAll() {
        // TODO Auto-generated method stub

    }

    @Override
    public ChunkManager getChunkManager() {
        return chunk;
    }

    @Override
    public PlayerIOService getPlayerService() {
        return service;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public void playSound(Location location, String sound, float volume, float pitch) {
        for (Player player : getPlayers()) {
            player.playSound(location, sound, volume, pitch);
        }
    }

    @Override
    public void playSoundAtEntity(Entity entity, Sound sound, float volume, float pitch) {
        for (Player player : getPlayers()) {
            player.playSound(entity.getLocation(), sound, volume, pitch);
        }
    }

    @Override
    public void playSound(Location location, Sound sound, float volume, float pitch) {
        for (Player player : getPlayers()) {
            player.playSound(location, sound, volume, pitch);
        }
    }

    @Override
    public void playEffect(Location location, Effect effect, int data) {
        for (Player player : getPlayers()) {
            player.playEffect(location, effect, data);
        }
    }
}