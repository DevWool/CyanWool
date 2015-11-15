package net.hexogendev.hexogen.standalone.world.chunk;

import java.util.List;

import net.hexogendev.hexogen.api.block.Block;
import net.hexogendev.hexogen.api.entity.Entity;
import net.hexogendev.hexogen.api.material.blocks.BlockMaterial;
import net.hexogendev.hexogen.api.world.LightType;
import net.hexogendev.hexogen.api.world.Position;
import net.hexogendev.hexogen.api.world.World;
import net.hexogendev.hexogen.api.world.chunk.Chunk;

public class HexoChunk implements Chunk{

	private World world;
	private int x;
	private int z;
	private int WIDTH = 16;
	private int HEIGHT = 256;

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getZ() {
		return z;
	}

	@Override
	public int getMaxHeight(int x, int z) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxHeight(Position pos) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void generateSkylightMap() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void relightBlock(int x, int y, int z) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void relightBlock(Position pos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Block getBlock(int x, int y, int z) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Block getBlock(Position pos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBlock(int x, int y, int z, BlockMaterial type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBlock(Position pos, BlockMaterial type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getLightLevel(int x, int y, int z) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getLightLevel(Position pos) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLightLevel(LightType type, int x, int y, int z, int level) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLightLevel(LightType type, Position pos, int level) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Entity> getEntities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canSeeSky(Position pos) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onChunkLoad() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onChunkUnload() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNeedUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isNeedUpdate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isLoaded() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public World getWorld() {
		return world;
	}

	@Override
	public int getWidth() {
		return WIDTH;
	}

	@Override
	public int getHeight() {
		return HEIGHT;
	}

	@Override
	public void setKeepInMemory(boolean flag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isKeepInMemory() {
		// TODO Auto-generated method stub
		return false;
	}

}
