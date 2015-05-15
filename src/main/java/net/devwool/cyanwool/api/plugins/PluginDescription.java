package net.devwool.cyanwool.api.plugins;

import java.util.List;

public interface PluginDescription {

    public String getName();

    public String getVersion();

    public String getDescription();

    public List<String> getAuthors();

    public List<String> getDepends();

}