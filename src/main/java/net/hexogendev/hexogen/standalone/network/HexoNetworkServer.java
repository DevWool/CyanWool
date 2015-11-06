package net.hexogendev.hexogen.standalone.network;

import org.spacehq.mc.protocol.MinecraftConstants;
import org.spacehq.mc.protocol.MinecraftProtocol;
import org.spacehq.packetlib.Server;
import org.spacehq.packetlib.tcp.TcpSessionFactory;

import net.hexogendev.hexogen.api.entity.alive.player.Player;
import net.hexogendev.hexogen.api.network.IPacket;
import net.hexogendev.hexogen.api.network.NetworkServer;
import net.hexogendev.hexogen.api.world.Position;
import net.hexogendev.hexogen.api.world.World;
import net.hexogendev.hexogen.standalone.network.handlers.ServerInfo;
import net.hexogendev.hexogen.standalone.network.handlers.ServerLogin;

public class HexoNetworkServer implements NetworkServer {

	private net.hexogendev.hexogen.api.Server server;
	private Server protocolServer;

	public HexoNetworkServer(net.hexogendev.hexogen.api.Server server) {
		this.server = server;
	}

	public void start() {
		if (protocolServer == null) {
			try {
				this.protocolServer = new Server(server.getServerConfiguration().getIPAddress(), server.getServerConfiguration().getPort(), MinecraftProtocol.class, new TcpSessionFactory());

				protocolServer.setGlobalFlag(MinecraftConstants.VERIFY_USERS_KEY, server.getServerConfiguration().isOnlineMode());
				protocolServer.setGlobalFlag(MinecraftConstants.SERVER_INFO_BUILDER_KEY, new ServerInfo(server));
				protocolServer.setGlobalFlag(MinecraftConstants.SERVER_LOGIN_HANDLER_KEY, new ServerLogin(server));
				protocolServer.addListener(new HexoServerListener(server));
				protocolServer.setGlobalFlag(MinecraftConstants.SERVER_COMPRESSION_THRESHOLD, server.getServerConfiguration().getServerCompressionThreshold());

				protocolServer.bind();
				server.getLogger().info("Protocol server is started!");
			} catch (Exception ex) {
				server.getLogger().info("Server crashed: ");
				ex.printStackTrace();
			}
		}
	}

	@Override
	public net.hexogendev.hexogen.api.Server getServer() {
		return server;
	}

	@Override
	public Server getProtocolServer() {
		return protocolServer;
	}

	@Override
	public int getPort() {
		return getServer().getServerConfiguration().getPort();
	}

	@Override
	public String getHostAddress() {
		return getServer().getServerConfiguration().getIPAddress();
	}

	@Override
	public void sendPacketForAll(IPacket packet) {
		for (Player player : getServer().getPlayerManager().getPlayers()) {
			player.getPlayerNetwork().sendPacket(packet);
		}
	}

	@Override
	public void sendPacketForAll(World world, IPacket packet) {
		for (Player player : world.getPlayers()) {
			player.getPlayerNetwork().sendPacket(packet);
		}
	}

	@Override
	public void sendPacketForAllExpect(World world, IPacket packet, Player expect) {
		for (Player player : world.getPlayers()) {
			if (!player.equals(expect)) {
				player.getPlayerNetwork().sendPacket(packet);
			}
		}
	}

	@Override
	public void sendPacketForAllExpect(IPacket packet, Player expect) {
		for (Player player : server.getPlayerManager().getPlayers()) {
			if (!player.equals(expect)) {
				player.getPlayerNetwork().sendPacket(packet);
			}
		}
	}

	@Override
	public void sendPacketDistance(Position pos, IPacket packet, int radius) {
		for (Player player : pos.getWorld().getPlayers()) {
			if (player.getPosition().distanceTo(pos) < radius) {
				player.getPlayerNetwork().sendPacket(packet);
			}
		}
	}

	@Override
	public void sendPacketDistanceExpect(Position pos, IPacket packet, int radius, Player expect) {
		for (Player player : pos.getWorld().getPlayers()) {
			if (!player.equals(expect)) {
				if (player.getPosition().distanceTo(pos) < radius) {
					player.getPlayerNetwork().sendPacket(packet);
				}
			}
		}
	}

	@Override
	public void shutdown() {
		getProtocolServer().close();
	}
}
