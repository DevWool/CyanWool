package net.devwool.cyanwool.api.material3;

import net.devwool.cyanwool.api.plugins.Plugin;

public interface Registry {

    public boolean registerMaterial(String prefix, Material mat);

    public boolean registerMaterial(Plugin plugin, Material mat);

    public Material getMaterialFromId(String prefix, int id);

    public Material getMaterialFromId(String prefix, String id);

    public Material getMaterialFromId(String prefix, int id, int metadata);

    public Material getMaterialFromId(String prefix, String id, int metadata);

    public Material getMaterialFromId(String prefix, int id, int metadata, int customData);

    public Material getMaterialFromId(String prefix, String id, int metadata, int customData);

    // public boolean unregisterMaterial(String prefix, String id);

    // public boolean unregisterMaterial(String prefix, int id);
}