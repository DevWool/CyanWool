package net.hexogendev.hexogen.standalone.storage;

import java.io.File;
import java.io.IOException;

import org.spacehq.opennbt.NBTIO;
import org.spacehq.opennbt.tag.builtin.ByteTag;
import org.spacehq.opennbt.tag.builtin.CompoundTag;
import org.spacehq.opennbt.tag.builtin.IntTag;
import org.spacehq.opennbt.tag.builtin.LongTag;
import org.spacehq.opennbt.tag.builtin.StringTag;

import net.hexogendev.hexogen.api.Server;
import net.hexogendev.hexogen.api.storage.StorageManager;
import net.hexogendev.hexogen.api.world.Difficulty;
import net.hexogendev.hexogen.api.world.Environment;
import net.hexogendev.hexogen.api.world.GameMode;
import net.hexogendev.hexogen.api.world.Position;
import net.hexogendev.hexogen.api.world.World;
import net.hexogendev.hexogen.api.world.WorldInfo;
import net.hexogendev.hexogen.api.world.WorldType;
import net.hexogendev.hexogen.api.world.chunk.Chunk;
import net.hexogendev.hexogen.standalone.world.HexoWorld;

public class HexoStorageManager implements StorageManager {

	private Server server;
	
	public HexoStorageManager(Server server) {
		this.server = server;
	}
	
	@Override
	public World readWorld(String folderName, String worldName) {
		if (folderName == null || worldName == null) {
			return null;
		}

		if (!folderName.endsWith("/")) {
			folderName = folderName + "/";
		}

		try {
			CompoundTag tag = NBTIO.readFile(new File(folderName + worldName + "/level.dat"));
			CompoundTag data = tag.get("Data");

			StringTag name = data.get("LevelName");
			LongTag seed = data.get("RandomSeed");
			IntTag gamemode = data.get("GameType");
			StringTag type = data.get("generatorName");
			IntTag generateStructures = data.get("MapFeatures");

			WorldInfo worldInfo = new WorldInfo(name.getValue(), folderName + worldName, Environment.NORMAL, seed.getValue(), GameMode.getGameMode(gamemode.getValue()), WorldType.getByName(type.getValue()), generateStructures.getValue() == 1);
			World world = new HexoWorld(worldInfo);

			IntTag xTag = data.get("SpawnX");
			IntTag yTag = data.get("SpawnY");
			IntTag zTag = data.get("SpawnZ");

			ByteTag difficulty = data.get("Difficulty");
			LongTag daytime = data.get("DayTime");

			Position newSpawn = new Position(world, xTag.getValue(), yTag.getValue(), zTag.getValue());
			world.setSpawnPosition(newSpawn);
			world.setDifficulty(Difficulty.getDifficulty(difficulty.getValue()));
			world.setWorldTime(daytime.getValue());
			return world;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void saveWorld(World world) {
		server.getLogger().info("Can't save world");
	}

	@Override
	public Chunk readChunk(WorldInfo info, int x, int z) {
		// TODO Auto-generated method stub
		return null;
	}
}
