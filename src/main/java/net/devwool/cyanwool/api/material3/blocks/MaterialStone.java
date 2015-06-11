package net.devwool.cyanwool.api.material3.blocks;

import net.devwool.cyanwool.api.material3.Material;

import org.spacehq.mc.protocol.data.game.values.world.GenericSound;

public class MaterialStone extends TexturedMaterial {

    public MaterialStone(String StringId, int Id, int metadata, int customData) {
        super(StringId, Id, metadata, customData);
        setBreakSound(GenericSound.DIG_STONE);
        setPlaceSound(GenericSound.DIG_STONE);
        setStepSound(GenericSound.STONE_STEP);
    }

    public MaterialStone() {
        this("stone", 1, 0, 0);
    }

    public MaterialStone(int metadata) {
        this("stone", 1, metadata, 0);
    }

    public MaterialStone(int metadata, int customData) {
        this("stone", 1, metadata, customData);
    }

    public MaterialStone(Material material) {
        this(material.getStringId(), material.getId(), material.getMinecraftData(), material.getCustomData());
    }
}