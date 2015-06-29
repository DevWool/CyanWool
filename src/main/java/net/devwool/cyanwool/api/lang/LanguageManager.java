package net.devwool.cyanwool.api.lang;

import java.util.Collection;

public interface LanguageManager {

    public void registerLanguageFile(LanguageFile file);

    public void removeLanguageFile(String localeCode);

    public boolean hasAvailableLanguage(String localeCode);

    public void loadAllLanguages();

    public void refreshLanguages();

    public Collection<LanguageFile> getLanguages();

    public LanguageFile getLanguageFile(String localeCode);
}