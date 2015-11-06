package net.hexogendev.hexogen.standalone.lang;

import java.io.File;
import java.io.IOException;

import net.hexogendev.hexogen.api.configuration.file.YamlConfiguration;
import net.hexogendev.hexogen.api.lang.ILanguageFile;

public class HexoLanguageFile implements ILanguageFile {

	private String localeCode;
	private YamlConfiguration config;
	// private HashMap<String, String> values = new HashMap<String, String>();
	private File file;

	public HexoLanguageFile(String localeCode, File file) {
		this.localeCode = localeCode;
		this.file = file;
		this.config = YamlConfiguration.loadConfiguration(file);
		for (String list : config.getKeys(true)) {
			String value = config.getString(list);
			setValue(list, value);
		}
	}

	@Override
	public String getValue(String unlocalizedName) {
		// String text = values.get(unlocalizedName);
		String text = config.getString(unlocalizedName);
		if (text == null) {
			text = "Missing translate";
			setValue(unlocalizedName, text);
		}
		return text;
	}

	@Override
	public void setValue(String unlocalizedName, String value) {
		// values.put(unlocalizedName, value);
		config.set(unlocalizedName, value);
	}

	@Override
	public boolean hasValue(String unlocalizeName) {
		// return values.containsKey(unlocalizeName);
		return config.contains(unlocalizeName);
	}

	@Override
	public String getLocaleCode() {
		return localeCode;
	}

	// @Override
	@Override
	public void save() {
		// CyanWool.getScheduler().runTask(new Runnable() {

		// @Override
		// public void run() {
		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// }
		// }, 1);
	}

}
