package net.devwool.cyanwool.api.material3.texture;

import net.devwool.cyanwool.api.material3.Material;

public interface ITextured {

    public Material setTextureIndex(int index);

    public int getTextureIndex();

    public Material setTexture(IMaterialTextures textures);
}