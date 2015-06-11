package net.devwool.cyanwool.api.material.blocks;

public class MaterialStone extends MinecraftBlockMaterial {

    // Stone: 1
    public static MaterialStone STONE = new MaterialStone("stone", 1, 0, 0);
    public static MaterialStone GRANITE = new MaterialStone("stone", 1, 1, 0);
    public static MaterialStone POLISHED_GRANITE = new MaterialStone("stone", 1, 2, 0);
    public static MaterialStone DIORITE = new MaterialStone("stone", 1, 3, 0);
    public static MaterialStone POLISHED_DIORITE = new MaterialStone("stone", 1, 4, 0);
    public static MaterialStone ANDESITE = new MaterialStone("stone", 1, 5, 0);

    // Cobblestone: 4
    public static MaterialStone COBBLESTONE = new MaterialStone("cobblestone", 4, 0, 0);

    MaterialStone(String stringId, int id, int metadata, int customMetadata) {
        super(stringId, id);
        setMinecraftData(metadata);
        setCustomData(customMetadata);
        setSolid(true);
    }
}