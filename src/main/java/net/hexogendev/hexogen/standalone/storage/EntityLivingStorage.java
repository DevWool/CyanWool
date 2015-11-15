package net.hexogendev.hexogen.standalone.storage;

import java.util.List;

public interface EntityLivingStorage extends EntityStorage {

	@Deprecated
	public float getHealF();

	public float getHealth();

	public float getAbsorptionAmount();

	public short getAttackTime();

	public short getHurtTime();

	public int getHurtByTimestamp();

	public short getDeathTime();

	public List getAttributes();

	public List getActiveEffects();

	@Deprecated
	public List getEquipment();

	@Deprecated
	public List getDropChances();

	public List getHandItems();

	public List getArmorItems();

	public List getHandDropChances();

	public List getArmorDropChances();

	public String getDeathLootTable();

	public long getDeathLootTableSeed();

	public boolean canPickUpLoot();

	public boolean isNoAI();

	public boolean isPersistenceRequired();

	public boolean isLeftHanded();

	public String getTeam();

	public boolean isLeashed();

	// etc

	@Deprecated
	public void setHealF(float health);

	public void setHealth(float health);

	public void setAbsorptionAmount(float arg);

	public void setAttackTime(short time);

	public void setHurtTime(short time);

	public void setHurtByTimestamp(short time);

	public void setDeathTime(short time);

	public void setAttributes(List list);

	public void setActiveEffects(List list);

	@Deprecated
	public void setEquipment(List equipment);

	@Deprecated
	public void setDropChances(List chances);

	public void setHandItems(List items);

	public void setArmorItems(List armors);

	public void setHandDropChances(List chances);

	public void setArmorDropChances(List chances);

	public void setDeathLootTable(String name);

	public void setDeathLootTableSeed(long seed);

	public void setCanPickUpLoot(boolean flag);

	public void setNoAI(boolean flag);

	public void setPersistenceRequired(boolean flag);

	public void setLeftHanded(boolean flag);

	public void getTeam(String team);

	public void setLeashed(boolean flag);
}
