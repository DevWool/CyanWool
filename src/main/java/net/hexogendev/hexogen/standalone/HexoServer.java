package net.hexogendev.hexogen.standalone;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.hexogendev.hexogen.api.ImplementationType;
import net.hexogendev.hexogen.api.Server;
import net.hexogendev.hexogen.api.Side;
import net.hexogendev.hexogen.api.command.ConsoleCommandSender;
import net.hexogendev.hexogen.api.command.ICommandManager;
import net.hexogendev.hexogen.api.command.SimpleCommandManager;
import net.hexogendev.hexogen.api.entity.EntityManager;
import net.hexogendev.hexogen.api.lang.ILanguageFile;
import net.hexogendev.hexogen.api.lang.ILanguageManager;
import net.hexogendev.hexogen.api.management.OperatorsManager;
import net.hexogendev.hexogen.api.management.PlayerManager;
import net.hexogendev.hexogen.api.management.WhitelistManager;
import net.hexogendev.hexogen.api.network.NetworkServer;
import net.hexogendev.hexogen.api.plugins.IPluginManager;
import net.hexogendev.hexogen.api.plugins.impl.PluginManager;
import net.hexogendev.hexogen.api.scheduler.Scheduler;
import net.hexogendev.hexogen.api.scheduler.tasks.ConsoleTask;
import net.hexogendev.hexogen.api.storage.StorageManager;
import net.hexogendev.hexogen.api.utils.Registry;
import net.hexogendev.hexogen.api.utils.ServerConfiguration;
import net.hexogendev.hexogen.api.utils.SimpleServerConfiguration;
import net.hexogendev.hexogen.api.utils.chatReplacer.ReplacerManager;
import net.hexogendev.hexogen.api.utils.chatReplacer.defaultPack.MessageReplacer;
import net.hexogendev.hexogen.api.utils.chatReplacer.defaultPack.MinecraftVersionReplacer;
import net.hexogendev.hexogen.api.utils.chatReplacer.defaultPack.ModImplementationReplacer;
import net.hexogendev.hexogen.api.utils.chatReplacer.defaultPack.ModNameReplacer;
import net.hexogendev.hexogen.api.utils.chatReplacer.defaultPack.ReasonReplacer;
import net.hexogendev.hexogen.api.utils.chatReplacer.defaultPack.SenderNameReplacer;
import net.hexogendev.hexogen.api.utils.chatReplacer.defaultPack.TimeReplacer;
import net.hexogendev.hexogen.api.world.WorldManager;
import net.hexogendev.hexogen.standalone.entity.HexoEntityManager;
import net.hexogendev.hexogen.standalone.lang.HexoLanguageManager;
import net.hexogendev.hexogen.standalone.management.HexoOperatorsManager;
import net.hexogendev.hexogen.standalone.management.HexoPlayerManager;
import net.hexogendev.hexogen.standalone.management.HexoWhitelistManager;
import net.hexogendev.hexogen.standalone.network.HexoNetworkServer;
import net.hexogendev.hexogen.standalone.scheduler.HexoScheduler;
import net.hexogendev.hexogen.standalone.utils.HexoRegistry;
import net.hexogendev.hexogen.standalone.world.HexoWorldManager;

public class HexoServer implements Server {

	private String MOD_NAME = "CyanWool-Experimental";
	private ImplementationType MOD_IMPL = ImplementationType.STANDALONE;
	private String MC_VERSION = "1.8";
	private Side SIDE = Side.SERVER;

	private ServerConfiguration configuration;
	private Scheduler scheduler;
	private Logger logger = LogManager.getLogger(HexoServer.class);
	private WhitelistManager whitelist;
	private OperatorsManager opManager;
	private NetworkServer networkServer;
	private PlayerManager playerManager;
	private Registry registry;
	private IPluginManager pluginManager;
	private ICommandManager cmdManager;
	private WorldManager worldManager;
	private EntityManager entityManager;
	private ILanguageManager langManager;
	private ConsoleCommandSender console;
	private StorageManager storageManager;

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
		HexoNetworkServer server = new HexoNetworkServer(this);
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

			this.scheduler = new HexoScheduler(this);
			this.whitelist = new HexoWhitelistManager(this);
			this.opManager = new HexoOperatorsManager(this);
			this.playerManager = new HexoPlayerManager(this);
			this.cmdManager = new SimpleCommandManager(this);
			// this.ioManager = new CyanIOManager();
			this.worldManager = new HexoWorldManager(this);
			this.entityManager = new HexoEntityManager(this);
			this.langManager = new HexoLanguageManager(this);
			this.registry = new HexoRegistry();
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

		networkServer.shutdown();
		getLogger().info("NetworkServer is closed!");

		pluginManager.unloadPlugins();
		getLogger().info("Plugins unloaded!");

		worldManager.saveAllWorlds();
		getLogger().info("World's saved!");

		for (ILanguageFile file : getLanguageManager().getLanguages()) {
			file.save();
		}

		scheduler.shutdown();
		getLogger().info("Scheduler's shutdown!");
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
	public WorldManager getWorldManager() {
		return worldManager;
	}

	@Override
	public Scheduler getScheduler() {
		return scheduler;
	}

	@Override
	public ImplementationType getImplementationType() {
		return MOD_IMPL;
	}

	@Override
	public Side getSide() {
		return SIDE;
	}

	@Override
	public StorageManager getStorageManager() {
		return storageManager;
	}

}
