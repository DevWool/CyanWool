package net.devwool.cyanwool.api.material.blocks;

public class MaterialWood extends MinecraftBlockMaterial {

    // Wood planks: 5
    public static MaterialWood OAK_WOOD_PLANK = new MaterialWood("planks", 5, 0, 0);
    public static MaterialWood SPRUCE_WOOD_PLANK = new MaterialWood("planks", 5, 1, 0);
    public static MaterialWood BIRCH_WOOD_PLANK = new MaterialWood("planks", 5, 2, 0);
    public static MaterialWood JUNGLE_WOOD_PLANK = new MaterialWood("planks", 5, 3, 0);
    public static MaterialWood ACACIA_WOOD_PLANK = new MaterialWood("planks", 5, 4, 0);
    public static MaterialWood DARK_OAK_WOOD_PLANK = new MaterialWood("planks", 5, 5, 0);

    MaterialWood(String stringId, int id, int metadata, int customMetadata) {
        super(stringId, id);
        setMinecraftData(metadata);
        setCustomData(customMetadata);
        setSolid(true);
        setBurnable(true);
    }

}