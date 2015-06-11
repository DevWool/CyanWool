package net.devwool.cyanwool.api.material3.blocks;

import net.devwool.cyanwool.api.material3.Material;

import org.spacehq.mc.protocol.data.game.values.world.GenericSound;

public class MaterialDirt extends TexturedMaterial {

    public MaterialDirt(String StringId, int Id, int metadata, int customData) {
        super(StringId, Id, metadata, customData);
        setBreakSound(GenericSound.DIG_GRASS);
        setPlaceSound(GenericSound.DIG_GRASS);
        setStepSound(GenericSound.GRASS_STEP);
    }

    public MaterialDirt() {
        this("dirt", 2, 0, 0);
    }

    public MaterialDirt(int metadata) {
        this("dirt", 2, metadata, 0);
    }

    public MaterialDirt(int metadata, int customData) {
        this("dirt", 2, metadata, customData);
    }

    public MaterialDirt(Material material) {
        this(material.getStringId(), material.getId(), material.getMinecraftData(), material.getCustomData());
    }
}