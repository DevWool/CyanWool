package net.devwool.cyanwool.core.network;

import net.devwool.cyanwool.api.Server;
import net.devwool.cyanwool.api.entity.alive.player.Player;
import net.devwool.cyanwool.api.network.PlayerNetwork;

import org.spacehq.packetlib.event.session.SessionListener;
import org.spacehq.packetlib.packet.Packet;

public class CWPlayerNetwork implements PlayerNetwork {

    @Override
    public Player getPlayer() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void sendPacket(Packet packet) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addListener(SessionListener listener) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeListener(SessionListener listener) {
        // TODO Auto-generated method stub

    }

    @Override
    public void disconnect(String reason) {
        // TODO Auto-generated method stub

    }

    @Override
    public String getHost() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Server getServer() {
        // TODO Auto-generated method stub
        return null;
    }
}