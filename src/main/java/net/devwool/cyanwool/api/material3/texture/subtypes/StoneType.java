package net.devwool.cyanwool.api.material3.texture.subtypes;

import net.devwool.cyanwool.api.material3.texture.IMaterialTextures;

public enum StoneType implements IMaterialTextures {
    STONE("stone", 0), GRANITE("granite", 1), GRANITE_SMOOTH("granite_smooth", 2), DIORITE("diorite", 3), DIORITE_SMOOTH("diorite_smooth", 4), ANDESITE("andesite", 5), ANDESITE_SMOOTH("andesite_smooth", 6);

    private String sid;
    private int metadata;

    private StoneType(String stringId, int metadata) {
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
        return STONE;
    }

    @Override
    public IMaterialTextures getTextureFromId(String id) {
        for (IMaterialTextures texture : values()) {
            if (texture.getStringId().equals(id)) {
                return texture;
            }
        }
        return STONE;
    }
}