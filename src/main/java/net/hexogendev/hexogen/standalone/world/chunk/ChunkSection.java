package net.hexogendev.hexogen.standalone.world.chunk;

import org.spacehq.mc.protocol.data.game.NetChunk;
import org.spacehq.mc.protocol.data.game.NibbleArray3d;
import org.spacehq.mc.protocol.data.game.ShortArray3d;

public class ChunkSection extends NetChunk {

    private final int y;
    private ShortArray3d data;

    public ChunkSection(int y, ShortArray3d blocks, NibbleArray3d blocklight, NibbleArray3d skylight) {
        super(blocks, blocklight, skylight);
        this.data = new ShortArray3d(4096);
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public ShortArray3d getNotSupportData() {
        return data;
    }

}