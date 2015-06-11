package net.devwool.cyanwool.api.material3.texture.subtypes;

import net.devwool.cyanwool.api.material3.texture.IMaterialTextures;

public enum DirtType implements IMaterialTextures {
    DIRT("dirt", 0), COARSE_DIRT("coarse_dirt", 1), PODZOL("podzol", 2);

    private String sid;
    private int metadata;

    private DirtType(String stringId, int metadata) {
        this.sid = stringId;
        this.metadata = metadata;
    }

    @Override
    public String getStringId() {
        return sid;
    }

    @Override
    public int getIndex() {
        return metadata;
    }

    @Override
    public IMaterialTextures getTextureFromId(int id) {
        for (IMaterialTextures texture : values()) {
            if (texture.getIndex() == id) {
                return texture;
            }
        }
        return DIRT;
    }

    @Override
    public IMaterialTextures getTextureFromId(String id) {
        for (IMaterialTextures texture : values()) {
            if (texture.getStringId().equals(id)) {
                return texture;
            }
        }
        return DIRT;
    }
}