package net.cyanwool.core.network;

import net.cyanwool.api.Server;
import net.cyanwool.api.entity.types.player.Player;
import net.cyanwool.api.network.Packet;
import net.cyanwool.api.network.PlayerNetwork;


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