package net.hexogendev.hexogen.standalone.world;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import net.hexogendev.hexogen.api.Server;
import net.hexogendev.hexogen.api.world.World;
import net.hexogendev.hexogen.api.world.WorldManager;

public class HexoWorldManager implements WorldManager {

	private Server server;
	private final List<World> worlds = new CopyOnWriteArrayList<World>();

	public HexoWorldManager(Server server) {
		this.server = server;
	}

	@Override
	public Server getServer() {
		return server;
	}

	@Override
	public List<World> getWorlds() {
		return worlds;
	}

	@Override
	public boolean addWorld(String folderName, String name) {
		if (getWorld(name) != null) {
			return false;
		}

		World world = getServer().getStorageManager().readWorld(folderName, name);
		worlds.add(world);
		world.loadSpawnChunks();
		return true;
	}

	@Override
	public World getWorld(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeWorld(World world) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveWorld(World world) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveAllWorlds() {
		// TODO Auto-generated method stub

	}

}
