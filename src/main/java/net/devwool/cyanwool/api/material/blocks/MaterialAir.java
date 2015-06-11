package net.devwool.cyanwool.api.material.blocks;

public class MaterialAir extends MinecraftBlockMaterial {

    public static MaterialAir AIR = new MaterialAir();

    MaterialAir() {
        super("air", 0);
        setTransparent(true);
    }

}