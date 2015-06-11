package net.devwool.cyanwool.api.material.blocks;

public class MaterialDirt extends MinecraftBlockMaterial {

    // Grass: 2
    public static MaterialDirt GRASS = new MaterialDirt("dirt", 2, 0, 0);

    // Dirt: 3
    public static MaterialDirt DIRT = new MaterialDirt("dirt", 3, 0, 0);
    public static MaterialDirt COARSE_DIRT = new MaterialDirt("dirt", 3, 1, 0);
    public static MaterialDirt PODZOL_DIRT = new MaterialDirt("dirt", 3, 2, 0);

    MaterialDirt(String stringId, int id, int metadata, int customMetadata) {
        super(stringId, id);
        setMinecraftData(metadata);
        setCustomData(customMetadata);
        setSolid(true);
    }

}