package net.devwool.cyanwool.api.lang;

import java.util.List;

public interface LanguageManager {

    public void registerLanguageFile(LanguageFile file);

    public void removeLanguageFile(String isoCode);

    public boolean hasAvailableLanguage(String isoCode);

    public String getLocale(String code, String unlocalizeName);

    public void setLocale(String code, String unlocalizeName);

    public boolean hasLocale(String code, String unlocalizeName);

    public void loadAllLanguages();

    public void refreshLanguages();

    public List<LanguageFile> getLanguages();
}