package net.devwool.cyanwool.api.material;

public abstract class MinecraftMaterial extends Material {

    public MinecraftMaterial(String StringId, int Id) {
        super("minecraft:" + StringId, Id);
    }
}