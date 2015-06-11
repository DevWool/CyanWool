package net.devwool.cyanwool.api.material3.blocks;

import net.devwool.cyanwool.api.material3.Material;
import net.devwool.cyanwool.api.material3.texture.IMaterialTextures;
import net.devwool.cyanwool.api.material3.texture.ITextured;

public class TexturedMaterial extends BlockMaterial implements ITextured {

    public TexturedMaterial(String string, int id, int metadata, int customData) {
        super(string, id, metadata, customData);
    }

    public TexturedMaterial(String string, int id, int metadata) {
        this(string, id, 0, 0);
    }

    public TexturedMaterial(String string, int id) {
        this(string, id, 0);
    }

    public TexturedMaterial(Material material) {
        this(material.getStringId(), material.getId(), material.getMinecraftData(), material.getCustomData());
    }

    @Override
    public Material setTextureIndex(int index) {
        return setMinecraftData(index);
    }

    @Override
    public int getTextureIndex() {
        return getMinecraftData();
    }

    @Override
    public Material setTexture(IMaterialTextures textures) {
        return setTextureIndex(textures.getIndex());
    }

}