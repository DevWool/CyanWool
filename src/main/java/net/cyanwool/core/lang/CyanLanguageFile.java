package net.cyanwool.core.lang;

import java.util.HashMap;

import net.cyanwool.api.lang.ILanguageFile;

public class CyanLanguageFile implements ILanguageFile {

	private String localeCode;
	private HashMap<String, String> values = new HashMap<String, String>();

	public CyanLanguageFile(String localeCode) {
		this.localeCode = localeCode;
	}

	@Override
	public String getValue(String unlocalizedName) {
		String text = values.get(unlocalizedName);
		if (text == null) {
			text = "Missing translate";
		}
		return text;
	}

	@Override
	public void setValue(String unlocalizedName, String value) {
		values.put(unlocalizedName, value);
	}

	@Override
	public boolean hasValue(String unlocalizeName) {
		return values.containsKey(unlocalizeName);
	}

	@Override
	public String getLocaleCode() {
		return localeCode;
	}

}
