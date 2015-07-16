package net.cyanwool.core.entity.component;

import net.cyanwool.api.entity.Entity;
import net.cyanwool.api.entity.component.Component;

public abstract class CWComponent implements Component {

	private Entity entity;
	private String id;

	public CWComponent(Entity entity, String id) {
		this.entity = entity;
		this.id = id;
	}

	@Override
	public void initialization() {
	}

	@Override
	public Entity getEntity() {
		return entity;
	}

	@Override
	public String getID() {
		return id;
	}

}
