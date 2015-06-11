package net.devwool.cyanwool.api.world;

import java.util.List;

import net.devwool.cyanwool.api.Server;
import net.devwool.cyanwool.api.block.Block;
import net.devwool.cyanwool.api.entity.Entity;
import net.devwool.cyanwool.api.entity.EntityLivingBase;
import net.devwool.cyanwool.api.entity.player.Player;
import net.devwool.cyanwool.api.material3.blocks.BlockMaterial;
import net.devwool.cyanwool.api.world.chunk.ChunkManager;

import org.spacehq.mc.protocol.data.game.values.entity.player.GameMode;
import org.spacehq.mc.protocol.data.game.values.setting.Difficulty;
import org.spacehq.mc.protocol.data.game.values.world.Particle;
import org.spacehq.mc.protocol.data.game.values.world.Sound;
import org.spacehq.mc.protocol.data.game.values.world.effect.WorldEffect;
import org.spacehq.mc.protocol.data.game.values.world.effect.WorldEffectData;

public interface World {

    public Block getBlock(Position position);

    public Block getBlock(int x, int y, int z);

    public ChunkManager getChunkManager();

    public String getPath();

    public void setBlock(Position pos, int id, int data);

    public void setBlock(int x, int y, int z, int id, int data);

    public void setBlock(Position pos, BlockMaterial type);

    public void setBlock(int x, int y, int z, BlockMaterial type);

    public Position getSpawnPosition();

    public void setSpawnPosition(Position pos);

    public void playSoundAtEntity(Entity entity, String sound, float volume, float pitch);

    public void playSound(Position pos, String sound, float volume, float pitch);

    public void playSoundExpect(Position pos, String sound, float volume, float pitch, Player player);

    public void playSoundAtEntity(Entity entity, Sound sound, float volume, float pitch);

    public void playSound(Position pos, Sound sound, float volume, float pitch);

    public void playEffect(Position pos, WorldEffect effect, WorldEffectData data);

    public void playSoundExpect(Position pos, Sound sound, float volume, float pitch, Player player);

    public void playEffectExpect(Position pos, WorldEffect effect, WorldEffectData data, Player player);

    public void playParticle(Position pos, Particle particle, int amount, int data);

    public void playParticleExpect(Position pos, Particle particle, int amount, int data, Player player);

    public Entity spawnEntity(Entity entity, Position pos);

    public long getSeed();

    public long getTotalWorldTime();

    public long getWorldTime();

    public void setWorldTime(long time);

    public boolean isThundering();

    public void setThundering(boolean flag);

    public int getThunderTime();

    public void setThunderTime(int time);

    public boolean isRaining();

    public void setRaining(boolean rain);

    public int getRainTime();

    public void setRainTime(int time);

    public int getActualHeight();

    public List<Entity> getEntities();

    public List<EntityLivingBase> getLivingEntities();

    public List<Player> getPlayers();

    public void onTick();

    public Difficulty getDifficulty();

    public void setDifficulty(Difficulty diff);

    public GameMode getDefaultGamemode();

    public void setDefaultGamemode(GameMode mode);

    public boolean isAutoSave();

    public void setAutoSave(boolean value);

    public Server getServer();

    public boolean isLoadedSpawnChunks();

    public void loadSpawnChunks();

    public boolean isHardcore();

    public void setHardcore(boolean flag);
}