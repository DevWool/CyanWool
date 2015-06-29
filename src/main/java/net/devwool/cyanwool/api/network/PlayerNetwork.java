package net.devwool.cyanwool.api.network;

import net.devwool.cyanwool.api.Server;
import net.devwool.cyanwool.api.entity.alive.player.Player;

import org.spacehq.packetlib.event.session.SessionListener;
import org.spacehq.packetlib.packet.Packet;

public interface PlayerNetwork {

    public Player getPlayer();

    public void sendPacket(Packet packet);

    public void addListener(SessionListener listener);

    public void removeListener(SessionListener listener);

    public void disconnect(String reason);

    public String getHost();

    public Server getServer();
}