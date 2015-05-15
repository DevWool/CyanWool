package net.devwool.cyanwool.api.utils;

import java.util.List;

import net.devwool.cyanwool.api.block.BlockType;
import net.devwool.cyanwool.api.item.ItemType;

public interface Registry {

    public boolean registerItem(ItemType item);

    public boolean registerBlock(BlockType block);

    public boolean hasItem(int id, int data);

    public boolean hasBlock(int id, int data);

    public boolean removeItem(ItemType item);

    public boolean removeBlock(BlockType type);

    public ItemType getItemType(int id);

    public ItemType getItemType(int id, int data);

    public BlockType getBlock(int id);

    public BlockType getBlock(int id, int data);

    public List<ItemType> getItems();

    public List<BlockType> getBlocks();

}