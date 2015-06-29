package net.devwool.cyanwool.core.world;

import java.util.List;

import net.devwool.cyanwool.api.Server;
import net.devwool.cyanwool.api.block.Block;
import net.devwool.cyanwool.api.entity.Entity;
import net.devwool.cyanwool.api.entity.EntityLivingBase;
import net.devwool.cyanwool.api.entity.alive.player.Player;
import net.devwool.cyanwool.api.material3.blocks.BlockMaterial;
import net.devwool.cyanwool.api.world.Position;
import net.devwool.cyanwool.api.world.World;
import net.devwool.cyanwool.api.world.chunk.ChunkManager;

import org.spacehq.mc.protocol.data.game.values.entity.MobType;
import org.spacehq.mc.protocol.data.game.values.entity.ObjectData;
import org.spacehq.mc.protocol.data.game.values.entity.ObjectType;
import org.spacehq.mc.protocol.data.game.values.entity.player.GameMode;
import org.spacehq.mc.protocol.data.game.values.setting.Difficulty;
import org.spacehq.mc.protocol.data.game.values.world.Particle;
import org.spacehq.mc.protocol.data.game.values.world.Sound;
import org.spacehq.mc.protocol.data.game.values.world.effect.WorldEffect;
import org.spacehq.mc.protocol.data.game.values.world.effect.WorldEffectData;

public class CWWorld implements World {

    @Override
    public Block getBlock(Position position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Block getBlock(int x, int y, int z) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ChunkManager getChunkManager() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getPath() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setBlock(Position pos, int id, int data) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setBlock(int x, int y, int z, int id, int data) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setBlock(Position pos, BlockMaterial type) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setBlock(int x, int y, int z, BlockMaterial type) {
        // TODO Auto-generated method stub

    }

    @Override
    public Position getSpawnPosition() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setSpawnPosition(Position pos) {
        // TODO Auto-generated method stub

    }

    @Override
    public void playSoundAtEntity(Entity entity, String sound, float volume, float pitch) {
        // TODO Auto-generated method stub

    }

    @Override
    public void playSound(Position pos, String sound, float volume, float pitch) {
        // TODO Auto-generated method stub

    }

    @Override
    public void playSoundExpect(Position pos, String sound, float volume, float pitch, Player player) {
        // TODO Auto-generated method stub

    }

    @Override
    public void playSoundAtEntity(Entity entity, Sound sound, float volume, float pitch) {
        // TODO Auto-generated method stub

    }

    @Override
    public void playSound(Position pos, Sound sound, float volume, float pitch) {
        // TODO Auto-generated method stub

    }

    @Override
    public void playEffect(Position pos, WorldEffect effect, WorldEffectData data) {
        // TODO Auto-generated method stub

    }

    @Override
    public void playSoundExpect(Position pos, Sound sound, float volume, float pitch, Player player) {
        // TODO Auto-generated method stub

    }

    @Override
    public void playEffectExpect(Position pos, WorldEffect effect, WorldEffectData data, Player player) {
        // TODO Auto-generated method stub

    }

    @Override
    public void playParticle(Position pos, Particle particle, int amount, int data) {
        // TODO Auto-generated method stub

    }

    @Override
    public void playParticleExpect(Position pos, Particle particle, int amount, int data, Player player) {
        // TODO Auto-generated method stub

    }

    @Override
    public Entity spawnEntity(MobType type, Position pos) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Entity spawnObject(ObjectType type, ObjectData data, Position pos) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Entity spawnUnknownEntity(Entity entity, Position pos) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getSeed() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public long getTotalWorldTime() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public long getWorldTime() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setWorldTime(long time) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isThundering() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setThundering(boolean flag) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getThunderTime() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setThunderTime(int time) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isRaining() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setRaining(boolean rain) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getRainTime() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setRainTime(int time) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getActualHeight() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Entity> getEntities() {
        // TODO Auto-generated method stub
        return null;
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
        // TODO Auto-generated method stub

    }

    @Override
    public Difficulty getDifficulty() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setDifficulty(Difficulty diff) {
        // TODO Auto-generated method stub

    }

    @Override
    public GameMode getDefaultGamemode() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setDefaultGamemode(GameMode mode) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isAutoSave() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setAutoSave(boolean value) {
        // TODO Auto-generated method stub

    }

    @Override
    public Server getServer() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isLoadedSpawnChunks() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void loadSpawnChunks() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isHardcore() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setHardcore(boolean flag) {
        // TODO Auto-generated method stub

    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return null;
    }

}