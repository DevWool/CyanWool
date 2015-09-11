package net.cyanwool.core;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.cyanwool.api.Server;
import net.cyanwool.api.command.ConsoleCommandSender;
import net.cyanwool.api.command.ICommandManager;
import net.cyanwool.api.command.SimpleCommandManager;
import net.cyanwool.api.entity.EntityManager;
import net.cyanwool.api.io.IOManager;
import net.cyanwool.api.lang.ILanguageManager;
import net.cyanwool.api.management.OperatorsManager;
import net.cyanwool.api.management.PlayerManager;
import net.cyanwool.api.management.WhitelistManager;
import net.cyanwool.api.network.NetworkServer;
import net.cyanwool.api.plugins.IPluginManager;
import net.cyanwool.api.plugins.impl.PluginManager;
import net.cyanwool.api.scheduler.Scheduler;
import net.cyanwool.api.scheduler.tasks.ConsoleTask;
import net.cyanwool.api.utils.Registry;
import net.cyanwool.api.utils.ServerConfiguration;
import net.cyanwool.api.utils.SimpleServerConfiguration;
import net.cyanwool.api.utils.chatReplacer.ReplacerManager;
import net.cyanwool.api.utils.chatReplacer.defaultPack.MessageReplacer;
import net.cyanwool.api.utils.chatReplacer.defaultPack.MinecraftVersionReplacer;
import net.cyanwool.api.utils.chatReplacer.defaultPack.ModImplementationReplacer;
import net.cyanwool.api.utils.chatReplacer.defaultPack.ModNameReplacer;
import net.cyanwool.api.utils.chatReplacer.defaultPack.ReasonReplacer;
import net.cyanwool.api.utils.chatReplacer.defaultPack.SenderNameReplacer;
import net.cyanwool.api.utils.chatReplacer.defaultPack.TimeReplacer;
import net.cyanwool.api.world.WorldManager;
import net.cyanwool.core.entity.CyanEntityManager;
import net.cyanwool.core.lang.CyanLanguageManager;
import net.cyanwool.core.management.CyanOperatorsManager;
import net.cyanwool.core.management.CyanPlayerManager;
import net.cyanwool.core.management.CyanWhitelistManager;
import net.cyanwool.core.network.CyanNetworkServer;
import net.cyanwool.core.scheduler.CyanScheduler;
import net.cyanwool.core.utils.CyanRegistry;
import net.cyanwool.core.world.CyanWorldManager;

public class CyanServer implements Server {

	private String MOD_NAME = "CyanWool-Experimental";
	private String MOD_IMPL = "Standalone";
	private String MC_VERSION = "1.8";
	private ServerConfiguration configuration;
	private Scheduler scheduler;
	private Logger logger = LogManager.getLogger(CyanServer.class);
	private WhitelistManager whitelist;
	private OperatorsManager opManager;
	private NetworkServer networkServer;
	private PlayerManager playerManager;
	private Registry registry;
	private IPluginManager pluginManager;
	private ICommandManager cmdManager;
	private IOManager ioManager;
	private WorldManager worldManager;
	private EntityManager entityManager;
	private ILanguageManager langManager;
	private BufferedImage icon;
	private ConsoleCommandSender console;

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

		try {
			icon = ImageIO.read(new File("server-icon.png"));
		} catch (Exception ignored) {
		}

		// init
		ReplacerManager.registerReplacer(new MessageReplacer());
		ReplacerManager.registerReplacer(new MinecraftVersionReplacer());
		ReplacerManager.registerReplacer(new ModImplementationReplacer());
		ReplacerManager.registerReplacer(new ModNameReplacer());
		ReplacerManager.registerReplacer(new ReasonReplacer());
		ReplacerManager.registerReplacer(new SenderNameReplacer());
		ReplacerManager.registerReplacer(new TimeReplacer());

		initManagers();
		if (!getServerConfiguration().isOnlineMode()) {
			getLogger().warn("The server is running in offline mode! Only do this if you know what you're doing.");
		}

		getLanguageManager().loadAllLanguages();
		getPluginManager().loadPlugins();

		getScheduler().runTaskRepeat(new ConsoleTask(this), 0, 1);
		getPluginManager().enablePlugins();

		// init network server
		CyanNetworkServer server = new CyanNetworkServer(this);
		this.networkServer = server;
		server.start();

		long timeEnd = System.currentTimeMillis();
		long time = (timeEnd - timeStart);
		double seconds = ((double) time / 1000);
		getLogger().info("Done! ( " + seconds + " sec)");
	}

	private void initManagers() {
		// init
		try {
			this.console = new ConsoleCommandSender();
			SimpleServerConfiguration config = new SimpleServerConfiguration("server.yml");
			config.createFile();
			this.configuration = config;
			getServerConfiguration().loadFromFile();

			this.whitelist = new CyanWhitelistManager(this);
			this.opManager = new CyanOperatorsManager(this);
			this.playerManager = new CyanPlayerManager(this);
			this.cmdManager = new SimpleCommandManager(this);
			// this.ioManager = new CyanIOManager();
			this.worldManager = new CyanWorldManager(this);
			this.entityManager = new CyanEntityManager(this);
			this.langManager = new CyanLanguageManager(this);
			this.registry = new CyanRegistry();
			this.scheduler = new CyanScheduler(this);
			this.langManager = new CyanLanguageManager(this);
			this.pluginManager = new PluginManager(this, "plugins");
		} catch (IOException e) {
			e.printStackTrace();
		}

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
	public ILanguageManager getLanguageManager() {
		return langManager;
	}

	@Override
	public void shutdown(String message) {
		getLogger().info("Server shutdown: " + message + " !");

		scheduler.shutdown();
		getLogger().info("Scheduler's shutdown!");

		pluginManager.unloadPlugins();
		getLogger().info("Plugins unloaded!");

		networkServer.shutdown();
		getLogger().info("NetworkServer is closed!");

		// worlds.saveAllWorlds();
		System.exit(1);
		// TODO
	}

	@Override
	public Registry getRegistry() {
		return registry;
	}

	@Override
	public IPluginManager getPluginManager() {
		return pluginManager;
	}

	@Override
	public ICommandManager getCommandManager() {
		return cmdManager;
	}

	@Override
	public ConsoleCommandSender getConsoleCommandSender() {
		return console;
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

	@Override
	public String getImplementationType() {
		return MOD_IMPL;
	}

	@Override
	public BufferedImage getIcon() {
		return icon;
	}

}
