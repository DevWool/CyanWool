package net.cyanwool.core.entity.metadata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.spacehq.mc.protocol.data.game.EntityMetadata;
import org.spacehq.mc.protocol.data.game.NetItemStack;
import org.spacehq.mc.protocol.data.game.NetPosition;
import org.spacehq.mc.protocol.data.game.NetRotation;
import org.spacehq.mc.protocol.data.game.values.entity.MetadataType;

public class MetadataMap {

	private Map<Integer, EntityMetadata> metadata = new HashMap<Integer, EntityMetadata>();
	private List<EntityMetadata> changes = new ArrayList<EntityMetadata>();

	public Object getMetadata(int id) {
		EntityMetadata metadata = this.metadata.get(id);
		return metadata != null ? metadata.getValue() : null;
	}

	public void setMetadata(int id, EntityMetadata meta) {
		if (meta == null)
			throw new IllegalArgumentException("Cannot set a metadata value to null.");
		EntityMetadata old = this.metadata.get(id);
		this.metadata.put(id, meta);
		if (!meta.equals(old))
			this.changes.add(meta);
	}

	public void setMetadata(int id, Object value) {
		if (value == null)
			throw new IllegalArgumentException("Cannot set a metadata value to null.");

		MetadataType type;
		if (value instanceof Byte)
			type = MetadataType.BYTE;
		else if (value instanceof Short)
			type = MetadataType.SHORT;
		else if (value instanceof Integer)
			type = MetadataType.INT;
		else if (value instanceof Float)
			type = MetadataType.FLOAT;
		else if (value instanceof String)
			type = MetadataType.STRING;
		else if (value instanceof NetItemStack)
			type = MetadataType.ITEM;
		else if (value instanceof NetPosition)
			type = MetadataType.POSITION;
		else if (value instanceof NetRotation)
			type = MetadataType.ROTATION;
		else
			throw new IllegalArgumentException("Metadata value \"" + value + "\" has an unsupported type.");

		Object old = this.getMetadata(id);
		EntityMetadata metadata = new EntityMetadata(id, type, value);
		this.metadata.put(id, metadata);
		if (!value.equals(old))
			this.changes.add(metadata);
	}

	public Number getNumber(int id) {
		Object value = this.getMetadata(id);
		return value == null ? 0 : (Number) value;
	}

	public boolean getBit(int id, int bit) {
		return (this.getNumber(id).byteValue() & bit) != 0;
	}

	public void setBit(int id, int bit, boolean value) {
		this.setMetadata(id, (byte) (value ? (this.getNumber(id).byteValue() | bit) : (this.getNumber(id).byteValue() & ~bit)));
	}

	public void setBitOfInt(int id, int bit, boolean value) {
		this.setMetadata(id, value ? (this.getNumber(id).byteValue() | bit) : (this.getNumber(id).byteValue() & ~bit));
	}

	public EntityMetadata[] getMetaArray() {
		return this.metadata.values().toArray(new EntityMetadata[this.metadata.size()]);
	}

	public EntityMetadata[] getChanges() {
		if (this.changes.isEmpty())
			return null;

		EntityMetadata changes[] = this.changes.toArray(new EntityMetadata[this.changes.size()]);
		this.changes.clear();
		return changes;
	}
}
