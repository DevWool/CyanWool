package net.devwool.cyanwool.api.material.blocks;

public class MaterialSapling extends MinecraftBlockMaterial {

    // Sapling: 6
    public static MaterialSapling OAK_SAPLING = new MaterialSapling("sapling", 6, 0, 0);
    public static MaterialSapling SPRUCE_SAPLING = new MaterialSapling("sapling", 6, 1, 0);
    public static MaterialSapling BIRCH_SAPLING = new MaterialSapling("sapling", 6, 2, 0);
    public static MaterialSapling JUNGLE_SAPLING = new MaterialSapling("sapling", 6, 3, 0);
    public static MaterialSapling ACACIA_SAPLING = new MaterialSapling("sapling", 6, 4, 0);
    public static MaterialSapling DARK_SAPLING = new MaterialSapling("sapling", 6, 5, 0);

    MaterialSapling(String stringId, int id, int metadata, int customMetadata) {
        super(stringId, id);
        setMinecraftData(metadata);
        setCustomData(customMetadata);
        setSolid(true);
        setBurnable(true);
    }

}