package net.cyanwool.core.entity.component.generic;

import net.cyanwool.api.entity.Entity;
import net.cyanwool.api.entity.component.generic.DisplayName;
import net.cyanwool.core.entity.component.CWComponent;

public class CWDisplayName extends CWComponent implements DisplayName {

	private String displayName;
	private boolean render;

	public CWDisplayName(Entity entity) {
		super(entity, "displayName");
	}

	@Override
	public void update() {
	}

	@Override
	public boolean autoUpdate() {
		return false;
	}

	@Override
	public String getDisplayName() {
		return displayName;
	}

	@Override
	public void setDisplayName(String name) {
		this.displayName = name;
		// TODO: send metadata
	}

	@Override
	public boolean hasDisplayName() {
		return getDisplayName() != null;
	}

	@Override
	public boolean isRenderDisplayName() {
		return render;
	}

	@Override
	public void setRenderDisplayName(boolean flag) {
		this.render = flag;
		// TODO: send metadata
	}
}
