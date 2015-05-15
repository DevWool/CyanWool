package net.devwool.cyanwool.api.plugins;

import java.io.File;

import net.devwool.cyanwool.api.Server;

import org.apache.logging.log4j.Logger;

public interface Plugin {

    public String getName();

    public PluginDescription getDescription();

    public void onEnable();

    public void onLoad();

    public void onDisable();

    public boolean isEnabled();

    public void setEnabled(boolean flag);

    public Object getConfig();

    public void saveConfig();

    public void reloadConfig();

    public File getDataFolder();

    public Server getServer();

    public Logger getLogger();
}