package net.devwool.cyanwool.api.io.requests;

import net.devwool.cyanwool.api.io.IORequest;
import net.devwool.cyanwool.api.world.chunk.Chunk;

public interface ChunkIORequest extends IORequest {

    public void readChunk(int x, int z);

    public void saveChunk(Chunk chunk);

}