package net.devwool.cyanwool.api.lang;

public interface LanguageFile {

    public String getValue(String unlocalizedName);

    public void setValue(String unlocalizedName, String value);

    public boolean hasValue(String unlocalizeName);

    public String getLocaleCode();

}