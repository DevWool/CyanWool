package net.devwool.cyanwool.api.plugins.java;

import java.io.File;
import java.io.IOException;

import net.devwool.cyanwool.api.CyanWool;
import net.devwool.cyanwool.api.Server;
import net.devwool.cyanwool.api.configuration.file.FileConfiguration;
import net.devwool.cyanwool.api.configuration.file.YamlConfiguration;
import net.devwool.cyanwool.api.plugins.Plugin;
import net.devwool.cyanwool.api.plugins.PluginDescription;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JavaPlugin implements Plugin {

    private FileConfiguration config;
    private PluginDescription description;
    private boolean enable;
    private File folder;
    private Logger logger;
    private File configFile;

    public void init(PluginDescription file) {
        this.description = file;
        this.folder = new File(CyanWool.getPluginManager().getPluginsFolder(), getDescription().getName());
        this.logger = LogManager.getLogger(getDescription().getName());
        this.configFile = new File(this.getDataFolder(), "config.yml");
    }

    @Override
    public String getName() {
        return getDescription().getName();
    }

    @Override
    public PluginDescription getDescription() {
        return description;
    }

    @Override
    public void onEnable() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onLoad() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onDisable() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isEnabled() {
        return enable;
    }

    @Override
    public FileConfiguration getConfig() {
        if (config == null) {
            reloadConfig();
        }
        return config;
    }

    @Override
    public void saveConfig() {
        try {
            getConfig().save(configFile);
        } catch (IOException ex) {
            getServer().getLogger().error("Could not save config to " + configFile, ex);
        }
    }

    @Override
    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(configFile);

        // InputStream defConfigStream = getResource("config.yml");
        // if (defConfigStream != null) {
        // YamlConfiguration defConfig =
        // YamlConfiguration.loadConfiguration(defConfigStream);

        // config.setDefaults(defConfig);
        // }
    }

    @Override
    public File getDataFolder() {
        return folder;
    }

    @Override
    public Server getServer() {
        return CyanWool.getServer();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        JavaPlugin other = (JavaPlugin) obj;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        return true;
    }

    @Override
    public void setEnabled(boolean flag) {
        this.enable = flag;
    }

    @Override
    public Logger getLogger() {
        return logger;
    }

}