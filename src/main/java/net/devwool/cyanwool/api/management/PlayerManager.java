package net.devwool.cyanwool.api.management;

import java.util.List;

import net.devwool.cyanwool.api.Server;
import net.devwool.cyanwool.api.entity.alive.player.Player;
import net.devwool.cyanwool.api.world.World;

import org.spacehq.mc.auth.GameProfile;
import org.spacehq.mc.protocol.data.game.values.PlayerListEntryAction;

public interface PlayerManager {

    public Server getServer();

    public void joinPlayer(GameProfile info);

    public void spawnPlayer(Player player);

    public void refreshPlayer(Player player, PlayerListEntryAction action);

    public void leavePlayer(Player player);

    public List<Player> getPlayers();

    public void sendMessageForAll(String message);

    public void sendMessageForAll(World world, String message);

    public Player getPlayer(String name);
}