package net.devwool.cyanwool.core;

import java.util.ArrayList;
import java.util.List;

import net.devwool.cyanwool.api.Server;
import net.devwool.cyanwool.api.command.CommandManager;
import net.devwool.cyanwool.api.command.ConsoleCommandSender;
import net.devwool.cyanwool.api.entity.EntityManager;
import net.devwool.cyanwool.api.io.IOManager;
import net.devwool.cyanwool.api.lang.LanguageManager;
import net.devwool.cyanwool.api.management.OperatorsManager;
import net.devwool.cyanwool.api.management.PlayerManager;
import net.devwool.cyanwool.api.management.WhitelistManager;
import net.devwool.cyanwool.api.material3.Registry;
import net.devwool.cyanwool.api.network.NetworkServer;
import net.devwool.cyanwool.api.packs.ServerPack;
import net.devwool.cyanwool.api.plugins.PluginManager;
import net.devwool.cyanwool.api.scheduler.Scheduler;
import net.devwool.cyanwool.api.utils.ServerConfiguration;
import net.devwool.cyanwool.api.world.WorldManager;
import net.devwool.cyanwool.core.entity.CWEntityManager;
import net.devwool.cyanwool.core.lang.CWLanguageManager;
import net.devwool.cyanwool.core.management.CWOperatorsManager;
import net.devwool.cyanwool.core.management.CWPlayerManager;
import net.devwool.cyanwool.core.management.CWWhitelistManager;
import net.devwool.cyanwool.core.network.CWNetworkServer;
import net.devwool.cyanwool.core.scheduler.CWScheduler;
import net.devwool.cyanwool.core.world.CWWorldManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CWServer implements Server {

    private String MOD_NAME = "CyanWool-Experimental";
    private String MC_VERSION = "1.8";
    private List<String> developers = new ArrayList<String>();
    private ServerPack pack;
    private ServerConfiguration configuration;
    private Scheduler scheduler;
    private Logger logger = LogManager.getLogger(CWServer.class);
    private WhitelistManager whitelist;
    private OperatorsManager opManager;
    private NetworkServer networkServer;
    private PlayerManager playerManager;
    private Registry registry;
    private PluginManager pluginManager;
    private CommandManager cmdManager;
    private IOManager ioManager;
    private WorldManager worldManager;
    private EntityManager entityManager;
    private LanguageManager langManager;

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

        getDevelopers().add("BeYkeRYkt");

        // init
        this.configuration = new CWServerConfiguration();
        this.whitelist = new CWWhitelistManager(this);
        this.opManager = new CWOperatorsManager(this);
        this.playerManager = new CWPlayerManager(this);
        this.cmdManager = new CommandManager(this);
        this.ioManager = new CWIOManager();
        this.worldManager = new CWWorldManager(this);
        this.entityManager = new CWEntityManager(this);
        this.langManager = new CWLanguageManager();
        this.registry = new CWRegistry();
        this.scheduler = new CWScheduler(this);

        // init server pack...
        this.pack = new MinecraftServerPack();

        // Loading assets
        getServerPack().registerItems();
        getServerPack().registerBlocks();

        // init network server
        CWNetworkServer server = new CWNetworkServer(this);
        this.networkServer = server;
        server.start();
    }

    @Override
    public void broadcastMessage(String string) {
        getPlayerManager().sendMessageForAll(string);
    }

    @Override
    public Logger getLogger() {
        return logger;
    }

    @Override
    public WhitelistManager getWhitelistManager() {
        return whitelist;
    }

    @Override
    public OperatorsManager getOperatorsManager() {
        return opManager;
    }

    @Override
    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    @Override
    public NetworkServer getNetworkServer() {
        return networkServer;
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public LanguageManager getLanguageManager() {
        return langManager;
    }

    @Override
    public void shutdown(String message) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<String> getDevelopers() {
        return developers;
    }

    @Override
    public Registry getRegistry() {
        return registry;
    }

    @Override
    public PluginManager getPluginManager() {
        return pluginManager;
    }

    @Override
    public CommandManager getCommandManager() {
        return cmdManager;
    }

    @Override
    public ConsoleCommandSender getConsoleCommandSender() {
        return null;
    }

    @Override
    public ServerPack getServerPack() {
        return pack;
    }

    @Override
    public ServerConfiguration getServerConfiguration() {
        return configuration;
    }

    @Override
    public IOManager getIOManager() {
        return ioManager;
    }

    @Override
    public WorldManager getWorldManager() {
        return worldManager;
    }

    @Override
    public Scheduler getScheduler() {
        return scheduler;
    }

}