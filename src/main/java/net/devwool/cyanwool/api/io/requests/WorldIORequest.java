package net.devwool.cyanwool.api.io.requests;

import net.devwool.cyanwool.api.io.IORequest;
import net.devwool.cyanwool.api.world.World;

public interface WorldIORequest extends IORequest {

    public void readWorld(String name);

    public void saveWorld(World world);

}