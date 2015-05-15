package net.devwool.cyanwool.api.io;

import net.devwool.cyanwool.api.world.World;

public interface WorldIOService {

    public void readWorld(World world);

    public void saveWorld(World world);

}