package net.hexogendev.hexogen.standalone.world;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.spacehq.mc.protocol.data.game.NetPosition;
import org.spacehq.mc.protocol.data.game.values.MagicValues;
import org.spacehq.mc.protocol.data.game.values.world.CustomSound;
import org.spacehq.mc.protocol.data.game.values.world.effect.BreakBlockEffectData;
import org.spacehq.mc.protocol.data.game.values.world.effect.BreakPotionEffectData;
import org.spacehq.mc.protocol.data.game.values.world.effect.HardLandingEffectData;
import org.spacehq.mc.protocol.data.game.values.world.effect.ParticleEffect;
import org.spacehq.mc.protocol.data.game.values.world.effect.RecordEffectData;
import org.spacehq.mc.protocol.data.game.values.world.effect.SmokeEffectData;
import org.spacehq.mc.protocol.data.game.values.world.effect.SoundEffect;
import org.spacehq.mc.protocol.data.game.values.world.effect.WorldEffect;
import org.spacehq.mc.protocol.data.game.values.world.effect.WorldEffectData;
import org.spacehq.mc.protocol.packet.ingame.server.world.ServerPlayEffectPacket;
import org.spacehq.mc.protocol.packet.ingame.server.world.ServerPlaySoundPacket;

import net.hexogendev.hexogen.api.Hexogen;
import net.hexogendev.hexogen.api.Server;
import net.hexogendev.hexogen.api.block.Block;
import net.hexogendev.hexogen.api.entity.Entity;
import net.hexogendev.hexogen.api.entity.EntityLivingBase;
import net.hexogendev.hexogen.api.entity.EntityTracker;
import net.hexogendev.hexogen.api.entity.alive.player.Player;
import net.hexogendev.hexogen.api.material.blocks.BlockMaterial;
import net.hexogendev.hexogen.api.storage.EntityStorage;
import net.hexogendev.hexogen.api.world.Difficulty;
import net.hexogendev.hexogen.api.world.Particle;
import net.hexogendev.hexogen.api.world.Position;
import net.hexogendev.hexogen.api.world.World;
import net.hexogendev.hexogen.api.world.WorldInfo;
import net.hexogendev.hexogen.api.world.chunk.Chunk;
import net.hexogendev.hexogen.api.world.chunk.ChunkManager;
import net.hexogendev.hexogen.api.world.effect.Effect;
import net.hexogendev.hexogen.api.world.sounds.Sound;
import net.hexogendev.hexogen.standalone.entity.HexoEntityTracker;

public class HexoWorld implements World {

	private WorldInfo worldInfo;
	private ChunkManager chunkManager;
	private boolean loadedSpawnChunks;
	private boolean keepSpawnChunks;
	private Position spawnPosition;
	private long worldTime;
	private boolean hardcore;
	private boolean autoSave;
	private Difficulty difficulty;
	private boolean thundering;
	private int thunderingTime;
	private boolean raining;
	private int rainingTime;
	private List<Entity> entities;
	private EntityTracker tracker;

	public HexoWorld(WorldInfo info) {
		this.worldInfo = info;
		this.entities = new CopyOnWriteArrayList<>();
		this.tracker = new HexoEntityTracker(getServer());
	}

	@Override
	public ChunkManager getChunkManager() {
		return chunkManager;
	}

	@Override
	public boolean isLoadedSpawnChunks() {
		return loadedSpawnChunks;
	}

	@Override
	public void loadSpawnChunks() {
		if (!loadedSpawnChunks) {
			int centerX = getSpawnPosition().getBlockX() >> 4;
			int centerZ = getSpawnPosition().getBlockZ() >> 4;
			int radius = 4 * getServer().getServerConfiguration().getViewDistance() / 3;

			long loadTime = System.currentTimeMillis();

			int total = (radius * 2 + 1) * (radius * 2 + 1), current = 0;
			for (int x = centerX - radius; x <= centerX + radius; ++x) {
				for (int z = centerZ - radius; z <= centerZ + radius; ++z) {
					++current;
					Chunk chunk = getChunkManager().loadChunk(x, z);
					chunk.setKeepInMemory(true);

					if (System.currentTimeMillis() >= loadTime + 1000) {
						int progress = 100 * current / total;
						getServer().getLogger().info("Preparing spawn for " + getWorldInfo().getName() + ": " + progress + "%");
						loadTime = System.currentTimeMillis();
					}
				}
			}
			getServer().getLogger().info("Preparing spawn for " + getWorldInfo().getName() + ": done");
			loadedSpawnChunks = true;
		}
	}

	@Override
	public boolean isKeepSpawnChunksInMemory() {
		return keepSpawnChunks;
	}

	@Override
	public void setKeepSpawnChunksInMemory(boolean flag) {
		this.keepSpawnChunks = flag;
	}

	@Override
	public WorldInfo getWorldInfo() {
		return worldInfo;
	}

	@Override
	public void setBlock(Position pos, String prefix, int id, int data) {
		setBlock(pos.getBlockX(), pos.getBlockY(), pos.getBlockZ(), prefix, id, data);
	}

	@Override
	public void setBlock(int x, int y, int z, String prefix, int id, int data) {
		BlockMaterial material = (BlockMaterial) getServer().getRegistry().getMaterialFromId(prefix, id, data);
		setBlock(x, y, z, material);
	}

	@Override
	public void setBlock(Position pos, BlockMaterial type) {
		setBlock(pos.getBlockX(), pos.getBlockY(), pos.getBlockZ(), type);
	}

	@Override
	public void setBlock(int x, int y, int z, BlockMaterial type) {
		getChunkManager().getChunkFromBlockCoords(x, z).setBlock(x, y, z, type);
	}

	@Override
	public Block getBlock(Position position) {
		return getBlock(position.getBlockX(), position.getBlockY(), position.getBlockZ());
	}

	@Override
	public Block getBlock(int x, int y, int z) {
		return getChunkManager().getChunkFromBlockCoords(x, z).getBlock(x, y, z);
	}

	@Override
	public Position getSpawnPosition() {
		return spawnPosition;
	}

	@Override
	public void setSpawnPosition(Position pos) {
		this.spawnPosition = pos;
	}

	@Override
	public void playSound(Position pos, String sound, float volume, float pitch) {
		ServerPlaySoundPacket packet = new ServerPlaySoundPacket(new CustomSound(sound), pos.getX(), pos.getY(), pos.getZ(), volume, pitch);
		getServer().getNetworkServer().sendPacketForAll(this, packet);// ?
	}

	@Override
	public void playSoundExpect(Position pos, String sound, float volume, float pitch, Player player) {
		ServerPlaySoundPacket packet = new ServerPlaySoundPacket(new CustomSound(sound), pos.getX(), pos.getY(), pos.getZ(), volume, pitch);
		getServer().getNetworkServer().sendPacketForAllExpect(this, packet, player);// ?
	}

	@Override
	public void playSound(Position pos, Sound sound, float volume, float pitch) {
		ServerPlaySoundPacket packet = new ServerPlaySoundPacket(new CustomSound(sound.getString()), pos.getX(), pos.getY(), pos.getZ(), volume, pitch);
		getServer().getNetworkServer().sendPacketForAll(this, packet);// ?
	}

	@Override
	public void playEffect(Position pos, Effect effect, int data) {
		WorldEffect worldEffect = null;
		if (effect.getID() >= 2000) {
			MagicValues.key(ParticleEffect.class, effect.getID());
		} else {
			MagicValues.key(SoundEffect.class, effect.getID());
		}
		WorldEffectData worldData = null;
		if (worldEffect == SoundEffect.PLAY_RECORD) {
			worldData = new RecordEffectData(data);
		} else if (worldEffect == ParticleEffect.SMOKE) {
			worldData = MagicValues.key(SmokeEffectData.class, data);
		} else if (worldEffect == ParticleEffect.BREAK_BLOCK) {
			worldData = new BreakBlockEffectData(data);
		} else if (worldEffect == ParticleEffect.BREAK_SPLASH_POTION) {
			worldData = new BreakPotionEffectData(data);
		} else if (worldEffect == ParticleEffect.HARD_LANDING_DUST) {
			worldData = new HardLandingEffectData(data);
		}
		ServerPlayEffectPacket packet = new ServerPlayEffectPacket(worldEffect, new NetPosition(pos.getBlockX(), pos.getBlockY(), pos.getBlockZ()), worldData);
		getServer().getNetworkServer().sendPacketForAll(this, packet);
	}

	@Override
	public void playSoundExpect(Position pos, Sound sound, float volume, float pitch, Player player) {
		ServerPlaySoundPacket packet = new ServerPlaySoundPacket(new CustomSound(sound.getString()), pos.getX(), pos.getY(), pos.getZ(), volume, pitch);
		getServer().getNetworkServer().sendPacketForAllExpect(this, packet, player);// ?
	}

	@Override
	public void playEffectExpect(Position pos, Effect effect, int data, Player player) {
		WorldEffect worldEffect = null;
		if (effect.getID() >= 2000) {
			MagicValues.key(ParticleEffect.class, effect.getID());
		} else {
			MagicValues.key(SoundEffect.class, effect.getID());
		}
		WorldEffectData worldData = null;
		if (worldEffect == SoundEffect.PLAY_RECORD) {
			worldData = new RecordEffectData(data);
		} else if (worldEffect == ParticleEffect.SMOKE) {
			worldData = MagicValues.key(SmokeEffectData.class, data);
		} else if (worldEffect == ParticleEffect.BREAK_BLOCK) {
			worldData = new BreakBlockEffectData(data);
		} else if (worldEffect == ParticleEffect.BREAK_SPLASH_POTION) {
			worldData = new BreakPotionEffectData(data);
		} else if (worldEffect == ParticleEffect.HARD_LANDING_DUST) {
			worldData = new HardLandingEffectData(data);
		}
		ServerPlayEffectPacket packet = new ServerPlayEffectPacket(worldEffect, new NetPosition(pos.getBlockX(), pos.getBlockY(), pos.getBlockZ()), worldData);
		getServer().getNetworkServer().sendPacketForAllExpect(this, packet, player);
	}

	@Override
	public void playParticle(Position pos, Particle particle, int amount, int data) {
		org.spacehq.mc.protocol.data.game.values.world.Particle p = MagicValues.key(org.spacehq.mc.protocol.data.game.values.world.Particle.class, particle.getID());
		// ServerSpawnParticlePacket packet = new ServerSpawnParticlePacket(p, longDistance, x, y, z, offsetX, offsetY, offsetZ, velocityOffset, amount, data)
		// getServer().getNetworkServer().sendPacketForAll(this, packet);
	}

	@Override
	public void playParticleExpect(Position pos, Particle particle, int amount, int data, Player player) {
		// ServerPlayEffectPacket packet = new ServerPlayEffectPacket(worldEffect, new NetPosition(pos.getBlockX(), pos.getBlockY(), pos.getBlockZ()), worldData);
		// getServer().getNetworkServer().sendPacketForAllExpect(this, packet, player);
	}

	@Override
	public Entity spawnEntity(String id, Position pos) {
		Entity entity = getServer().getRegistry().createEntity(id, pos);
		EntityStorage storage = null; // TODO: get Entity storage
		entity.loadStorage(storage);
		getServer().getEntityManager().registerEntity(entity);
		getEntityTracker().registerEntity(entity);
		return entity;
	}

	@Override
	public Entity spawnEntity(int id, Position pos) {
		Entity entity = getServer().getRegistry().createEntity(id, pos);
		EntityStorage storage = null; // TODO: get Entity storage
		entity.loadStorage(storage);
		getServer().getEntityManager().registerEntity(entity);
		getEntityTracker().registerEntity(entity);
		return entity;
	}

	@Override
	public long getWorldTime() {
		return worldTime;
	}

	@Override
	public void setWorldTime(long time) {
		this.worldTime = time;
	}

	@Override
	public long getTotalWorldTime() {
		return 24000; // ?
	}

	@Override
	public boolean isHardcore() {
		return hardcore;
	}

	@Override
	public void setHardcore(boolean flag) {
		this.hardcore = flag;
	}

	@Override
	public boolean isAutoSave() {
		return autoSave;
	}

	@Override
	public void setAutoSave(boolean value) {
		this.autoSave = value;
	}

	@Override
	public Difficulty getDifficulty() {
		return difficulty;
	}

	@Override
	public void setDifficulty(Difficulty diff) {
		this.difficulty = diff;
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
	public int getMaxHeight() {
		return 256;
	}

	@Override
	public List<Entity> getEntities() {
		return entities;
	}

	@Override
	public List<EntityLivingBase> getLivingEntities() {
		return null;
	}

	@Override
	public List<Player> getPlayers() {
		List<Player> list = new CopyOnWriteArrayList<Player>();
		for (Player player : getServer().getPlayerManager().getPlayers()) {
			if (player.getWorld().getWorldInfo().getName().equals(getWorldInfo().getName())) {
				list.add(player);
			}
		}
		return list;
	}

	@Override
	public void onTick() {
		getChunkManager().onTick();
		worldTime++;
		if (worldTime == 24000) {
			worldTime = 0;
		}
		for (Player player : getPlayers()) {
			player.setTime(worldTime);
		}
	}

	@Override
	public Server getServer() {
		return Hexogen.getServer();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((worldInfo == null) ? 0 : worldInfo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HexoWorld other = (HexoWorld) obj;
		if (worldInfo == null) {
			if (other.worldInfo != null)
				return false;
		} else if (!worldInfo.equals(other.worldInfo))
			return false;
		return true;
	}

	@Override
	public EntityTracker getEntityTracker() {
		return tracker;
	}
}
