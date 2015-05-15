package net.devwool.cyanwool.api.io;

import net.devwool.cyanwool.api.world.chunk.Chunk;

public interface ChunkIOService {

    public Chunk readChunk(int x, int z);

    public void saveChunk(Chunk chunk);

}