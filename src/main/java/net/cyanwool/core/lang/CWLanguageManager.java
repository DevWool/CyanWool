package net.cyanwool.core.lang;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.cyanwool.api.lang.LanguageFile;
import net.cyanwool.api.lang.LanguageManager;

public class CWLanguageManager implements LanguageManager {

	private Map<String, LanguageFile> languages;

	public CWLanguageManager() {
		this.languages = new HashMap<String, LanguageFile>();
	}

	@Override
	public void registerLanguageFile(LanguageFile file) {
		if (!hasAvailableLanguage(file.getLocaleCode())) {
			languages.put(file.getLocaleCode(), file);
		}
	}

	@Override
	public void removeLanguageFile(String langCode) {
		if (hasAvailableLanguage(langCode)) {
			languages.remove(langCode);
		}
	}

	@Override
	public boolean hasAvailableLanguage(String langCode) {
		return languages.containsKey(langCode);
	}

	@Override
	public void loadAllLanguages() {
		// TODO Auto-generated method stub

	}

	@Override
	public void refreshLanguages() {
		languages.clear();
		loadAllLanguages();
	}

	@Override
	public Collection<LanguageFile> getLanguages() {
		return languages.values();
	}

	@Override
	public LanguageFile getLanguageFile(String isoCode) {
		return languages.get(isoCode);
	}

}
