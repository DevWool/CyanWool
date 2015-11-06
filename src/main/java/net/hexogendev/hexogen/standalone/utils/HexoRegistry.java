package net.hexogendev.hexogen.standalone.utils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import net.hexogendev.hexogen.api.entity.Entity;
import net.hexogendev.hexogen.api.entity.EntityType;
import net.hexogendev.hexogen.api.material.Material;
import net.hexogendev.hexogen.api.plugins.IPlugin;
import net.hexogendev.hexogen.api.utils.Registry;
import net.hexogendev.hexogen.api.world.Position;

public class HexoRegistry implements Registry {

	private Map<String, List<Material>> materials;
	private Map<String, Class<? extends Entity>> entityString;
	private Map<Integer, Class<? extends Entity>> entityIds;

	public HexoRegistry() {
		this.materials = new ConcurrentHashMap<String, List<Material>>();
		this.entityIds = new ConcurrentHashMap<Integer, Class<? extends Entity>>();
		this.entityString = new ConcurrentHashMap<String, Class<? extends Entity>>();
	}

	@Override
	public boolean registerMaterial(String prefix, Material mat) {
		if (materials.containsKey(prefix)) {
			List<Material> list = materials.get(prefix);
			if (list.contains(mat)) {
				return false;
			}
			list.add(mat);
			return true;
		}

		List<Material> list = new CopyOnWriteArrayList<>();
		list.add(mat);
		materials.put(prefix, list);
		return true;
	}

	@Override
	public boolean registerMaterial(IPlugin plugin, Material mat) {
		return registerMaterial(plugin.getDescription().getName(), mat);
	}

	@Override
	public Material getMaterialFromId(String prefix, int id) {
		return getMaterialFromId(prefix, id, 0);
	}

	@Override
	public Material getMaterialFromId(String prefix, int id, int metadata) {
		return getMaterialFromId(prefix, id, metadata, 0);
	}

	@Override
	public Material getMaterialFromId(String prefix, int id, int metadata, int customData) {
		if (materials.containsKey(prefix)) {
			List<Material> list = materials.get(prefix);
			for (Material mat : list) {
				if (mat.getId() == id && mat.getMinecraftData() == metadata && mat.getCustomData() == customData) {
					return mat;
				}
			}
		}
		return null;
	}

	@Override
	public Material getMaterialFromId(String prefix, String id) {
		return getMaterialFromId(prefix, id, 0);
	}

	@Override
	public Material getMaterialFromId(String prefix, String id, int metadata) {
		return getMaterialFromId(prefix, id, metadata, 0);
	}

	@Override
	public Material getMaterialFromId(String prefix, String id, int metadata, int customData) {
		if (materials.containsKey(prefix)) {
			List<Material> list = materials.get(prefix);
			for (Material mat : list) {
				if (mat.getStringId().equals(id) && mat.getMinecraftData() == metadata && mat.getCustomData() == customData) {
					return mat;
				}
			}
		}
		return null;
	}

	@Override
	public boolean registerEntity(int id, String name, Class<? extends Entity> entity) {
		if (entityIds.containsKey(id) || entityString.containsKey(name) || id == 0 || name == null || entity == null) {
			return false;
		}
		entityIds.put(id, entity);
		entityString.put(name, entity);
		return true;
	}

	@Override
	public boolean registerEntity(EntityType type, Class<? extends Entity> entity) {
		return registerEntity(type.getId(), type.getName(), entity);
	}

	@Override
	public boolean unregisterEntity(int id, String name) {
		if (!entityIds.containsKey(id) || !entityString.containsKey(name) || id == 0 || name == null) {
			return false;
		}
		entityIds.remove(id);
		entityString.remove(name);
		return true;
	}

	@Override
	public boolean unregisterEntity(EntityType type) {
		return unregisterEntity(type.getId(), type.getName());
	}

	@Override
	public Entity createEntity(int id, Position pos) {
		Entity entity = null;

		try {
			Class<? extends Entity> oclass = entityIds.get(id);

			if (oclass != null) {
				entity = oclass.getConstructor(new Class[] { Position.class }).newInstance(new Object[] { pos });
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return entity;
	}

	@Override
	public Entity createEntity(EntityType type, Position pos) {
		return createEntity(type.getId(), pos);
	}

	@Override
	public Entity createEntity(String name, Position pos) {
		Entity entity = null;

		try {
			Class<? extends Entity> oclass = entityString.get(name);

			if (oclass != null) {
				entity = oclass.getConstructor(new Class[] { Position.class }).newInstance(new Object[] { pos });
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return entity;
	}

}
