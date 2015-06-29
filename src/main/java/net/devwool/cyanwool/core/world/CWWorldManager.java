package net.devwool.cyanwool.core.world;

import java.util.List;

import net.devwool.cyanwool.api.Server;
import net.devwool.cyanwool.api.world.World;
import net.devwool.cyanwool.api.world.WorldManager;

public class CWWorldManager implements WorldManager {

    private Server server;

    public CWWorldManager(Server server) {
        this.server = server;
    }

    @Override
    public Server getServer() {
        return server;
    }

    @Override
    public List<World> getWorlds() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean addWorld(String name) {
        // TODO Auto-generated method stub
        return false;
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