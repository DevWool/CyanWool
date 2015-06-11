package net.devwool.cyanwool.api.material.blocks;

public class MaterialGravity extends MinecraftBlockMaterial {

    MaterialGravity(String stringId, int id, int metadata, int customMetadata, boolean flowing) {
        super(stringId, id);
        setMinecraftData(metadata);
        setCustomData(customMetadata);
        setGravity(true);
        setSolid(true);
    }

}