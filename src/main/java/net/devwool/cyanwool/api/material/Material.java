package net.devwool.cyanwool.api.material;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Material {

    private String stringId;
    private int intId;
    private int minecraftData;
    private int customData;
    private Map<String, Material> subMaterials;

    public Material(String StringId, int Id, int customData) {
        this.stringId = StringId;
        this.intId = Id;
        this.customData = customData;
        this.subMaterials = new ConcurrentHashMap<String, Material>();
    }

    public Material(String stringId, int Id) {
        this(stringId, Id, 0);
    }

    public String getStringId() {
        return stringId;
    }

    public int getId() {
        return intId;
    }

    public int getMinecraftData() {
        return minecraftData;
    }

    public Material setMinecraftData(int metadata) {
        minecraftData = metadata;
        return this;
    }

    public int getCustomData() {
        return customData;
    }

    public Material setCustomData(int metadata) {
        customData = metadata;
        return this;
    }

    public Material getSubMaterial(String enumName) {
        return subMaterials.get(enumName);
    }

    public void registerSubMaterial(String enumName, Material material) {
        subMaterials.put(enumName, material);
    }

    public void unregisterSubMaterial(String enumName) {
        subMaterials.remove(enumName);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + customData;
        result = prime * result + intId;
        result = prime * result + minecraftData;
        result = prime * result + ((stringId == null) ? 0 : stringId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Material)) {
            return false;
        }
        Material other = (Material) obj;
        if (customData != other.customData) {
            return false;
        }
        if (intId != other.intId) {
            return false;
        }
        if (minecraftData != other.minecraftData) {
            return false;
        }
        if (stringId == null) {
            if (other.stringId != null) {
                return false;
            }
        } else if (!stringId.equals(other.stringId)) {
            return false;
        }
        return true;
    }
}