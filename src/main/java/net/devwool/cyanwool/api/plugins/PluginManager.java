package net.devwool.cyanwool.api.plugins;

import java.io.File;
import java.util.List;

public interface PluginManager {

    public List<Plugin> getPlugins();

    public void loadPlugins();

    public void loadPlugin(PluginDescription desc);

    public void loadPlugin(File file);

    public void unloadPlugins();

    public void unloadPlugin(String name);

    public void enablePlugins();

    public void enablePlugin(String name);

    public void disablePlugins();

    public void disablePlugin(String name);

    public File getPluginsFolder();

    public Plugin getPlugin(String name);

    public boolean isPluginLoaded(String name);

    public boolean isPluginEnabled(String name);
}