package net.devwool.cyanwool.core.network;

import net.devwool.cyanwool.api.Server;
import net.devwool.cyanwool.api.entity.alive.player.Player;
import net.devwool.cyanwool.core.network.handlers.PlaySessionAdapter;

import org.spacehq.mc.auth.GameProfile;
import org.spacehq.mc.protocol.MinecraftProtocol;
import org.spacehq.mc.protocol.ProtocolConstants;
import org.spacehq.mc.protocol.ProtocolMode;
import org.spacehq.packetlib.event.server.ServerAdapter;
import org.spacehq.packetlib.event.server.SessionAddedEvent;
import org.spacehq.packetlib.event.server.SessionRemovedEvent;

public class CWServerListener extends ServerAdapter {

    private Server server;

    public CWServerListener(Server server) {
        this.server = server;
    }

    @Override
    public void sessionAdded(SessionAddedEvent event) {
        event.getSession().addListener(new PlaySessionAdapter(server));
    }

    @Override
    public void sessionRemoved(SessionRemovedEvent event) {
        // FOR TODO: Customize leave message
        if (((MinecraftProtocol) event.getSession().getPacketProtocol()).getMode() == ProtocolMode.GAME) {
            GameProfile profile = event.getSession().getFlag(ProtocolConstants.PROFILE_KEY);
            Player player = server.getPlayerManager().getPlayer(profile.getName());
            server.getPlayerManager().leavePlayer(player);
        }
    }
}