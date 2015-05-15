package net.devwool.cyanwool.core;

import java.util.ArrayList;
import java.util.List;

import net.devwool.cyanwool.api.Server;
import net.devwool.cyanwool.api.command.CommandManager;
import net.devwool.cyanwool.api.command.ConsoleCommandSender;
import net.devwool.cyanwool.api.entity.EntityManager;
import net.devwool.cyanwool.api.lang.LanguageManager;
import net.devwool.cyanwool.api.management.OperatorsManager;
import net.devwool.cyanwool.api.management.PlayerManager;
import net.devwool.cyanwool.api.management.WhitelistManager;
import net.devwool.cyanwool.api.network.NetworkServer;
import net.devwool.cyanwool.api.packs.ServerPack;
import net.devwool.cyanwool.api.plugins.PluginManager;
import net.devwool.cyanwool.api.utils.Registry;

import org.apache.logging.log4j.Logger;

public class CWServer implements Server {

    private String MOD_NAME = "CyanWool-Experimental";
    private String MC_VERSION = "1.8";
    private List<String> developers = new ArrayList<String>();
    private ServerPack pack;

    @Override
    public String getModName() {
        return MOD_NAME;
    }

    @Override
    public String getMCVersion() {
        return MC_VERSION;
    }

    @Override
    public void start() {
        long timeStart = System.currentTimeMillis();
        if (Runtime.getRuntime().maxMemory() / 1024L / 1024L < 512L) {
            getLogger().warn("To start the server with more ram, launch it as \"java -Xmx1024M -Xms1024M -jar <server_name>.jar\"");
            return;
        }

        // init server pack...

        // Loading assets
        getServerPack().registerItems();
        getServerPack().registerBlocks();

    }

    @Override
    public void broadcastMessage(String string) {
        // TODO Auto-generated method stub

    }

    @Override
    public Logger getLogger() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WhitelistManager getWhitelistManager() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public OperatorsManager getOperatorsManager() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PlayerManager getPlayerManager() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public NetworkServer getNetworkServer() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EntityManager getEntityManager() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LanguageManager getLanguageManager() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void shutdown(String message) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<String> getDevelopers() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Registry getRegistry() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PluginManager getPluginManager() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CommandManager getCommandManager() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ConsoleCommandSender getConsoleCommandSender() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServerPack getServerPack() {
        // TODO Auto-generated method stub
        return null;
    }

}