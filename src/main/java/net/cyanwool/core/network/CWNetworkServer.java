package net.cyanwool.core.network;

import net.cyanwool.api.entity.types.player.Player;
import net.cyanwool.api.network.NetworkServer;
import net.cyanwool.api.network.Packet;
import net.cyanwool.api.world.Position;
import net.cyanwool.core.network.handlers.ServerInfo;
import net.cyanwool.core.network.handlers.ServerLogin;

import org.spacehq.mc.protocol.MinecraftProtocol;
import org.spacehq.mc.protocol.ProtocolConstants;
import org.spacehq.packetlib.Server;
import org.spacehq.packetlib.tcp.TcpSessionFactory;

public class CWNetworkServer implements NetworkServer {

    private net.cyanwool.api.Server server;
    private Server protocolServer;

    public CWNetworkServer(net.cyanwool.api.Server server) {
        this.server = server;
    }

    public void start() {
        if (protocolServer == null) {
            try {
                this.protocolServer = new Server(server.getServerConfiguration().getIPAdress(), server.getServerConfiguration().getPort(), MinecraftProtocol.class, new TcpSessionFactory());

                protocolServer.setGlobalFlag(ProtocolConstants.VERIFY_USERS_KEY, server.getServerConfiguration().isOnlineMode());
                protocolServer.setGlobalFlag(ProtocolConstants.SERVER_INFO_BUILDER_KEY, new ServerInfo(server));
                protocolServer.setGlobalFlag(ProtocolConstants.SERVER_LOGIN_HANDLER_KEY, new ServerLogin(server));
                protocolServer.addListener(new CWServerListener(server));
                protocolServer.setGlobalFlag(ProtocolConstants.SERVER_COMPRESSION_THRESHOLD, 256);

                protocolServer.bind();
                server.getLogger().info("Protocol server is started!");
            } catch (Exception ex) {
                server.getLogger().info("Server crashed: ");
                ex.printStackTrace();
            }
        }
    }

    @Override
    public net.cyanwool.api.Server getServer() {
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
        return getServer().getServerConfiguration().getIPAdress();
    }

    @Override
    public void sendPacketForAll(Packet packet) {
        for (Player player : getServer().getPlayerManager().getPlayers()) {
            player.getPlayerNetwork().sendPacket(packet);
        }
    }

    @Override
    public void sendPacketDistance(Position pos, Packet packet, int radius) {
        for (Player player : getServer().getPlayerManager().getPlayers()) {
            if (player.getWorld().getName().equals(pos.getWorld().getName())) {
                if (player.getPosition().distanceTo(pos) < radius) {
                    player.getPlayerNetwork().sendPacket(packet);
                }
            }
        }
    }

    @Override
    public void sendPacketForAllExpect(Packet packet, Player expect) {
        for (Player player : server.getPlayerManager().getPlayers()) {
            if (!player.equals(expect)) {
                player.getPlayerNetwork().sendPacket(packet);
            }
        }
    }

    @Override
    public void sendPacketDistanceExpect(Position pos, Packet packet, int radius, Player expect) {
        for (Player player : getServer().getPlayerManager().getPlayers()) {
            if (!player.equals(expect)) {
                if (player.getWorld().getName().equals(pos.getWorld().getName())) {
                    if (player.getPosition().distanceTo(pos) < radius) {
                        player.getPlayerNetwork().sendPacket(packet);
                    }
                }
            }
        }
    }
}