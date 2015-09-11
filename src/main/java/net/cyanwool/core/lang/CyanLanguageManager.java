package net.cyanwool.core.lang;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.cyanwool.api.Server;
import net.cyanwool.api.configuration.file.YamlConfiguration;
import net.cyanwool.api.lang.ILanguageFile;
import net.cyanwool.api.lang.ILanguageManager;

public class CyanLanguageManager implements ILanguageManager {

	private Map<String, ILanguageFile> languages;
	private String folderName = "lang";
	private File langFolder;
	private Server server;

	public CyanLanguageManager(Server server) throws IOException {
		this.server = server;
		this.languages = new HashMap<String, ILanguageFile>();
		this.langFolder = new File(folderName);

		if (!this.langFolder.exists()) {
			if (!this.langFolder.mkdirs()) {
				throw new IOException("Unable to create folder: \"" + this.langFolder.getAbsolutePath() + "\"");
			}
		}

		if (this.langFolder.isFile()) {
			throw new IOException("Path \"" + this.langFolder.getAbsolutePath() + "\" does not be file");
		}
	}

	@Override
	public void registerLanguageFile(ILanguageFile file) {
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
		File[] langList = getListYmls();
		getServer().getLogger().info("Loading " + langList.length + " languages...");
		for (File file : langList) {
			YamlConfiguration lang = YamlConfiguration.loadConfiguration(file);
			String fileName = file.getName().substring(0, file.getName().length() - 4);
			ILanguageFile lf = new CyanLanguageFile(fileName);

			for (String list : lang.getKeys(true)) {
				String value = lang.getString(list);
				lf.setValue(list, value);
			}
			registerLanguageFile(lf);
		}

	}

	private File[] getListYmls() {
		return langFolder.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File file, String s) {
				return s.toLowerCase().endsWith(".yml");
			}
		});
	}

	@Override
	public void refreshLanguages() {
		languages.clear();
		loadAllLanguages();
	}

	@Override
	public Collection<ILanguageFile> getLanguages() {
		return languages.values();
	}

	@Override
	public ILanguageFile getLanguageFile(String isoCode) {
		ILanguageFile file = languages.get(isoCode);
		if (file == null) {
			file = languages.get("en_US");
		}
		return file;
	}

	@Override
	public Server getServer() {
		return server;
	}

}
