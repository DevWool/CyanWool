package net.devwool.cyanwool.core.entity.alive.player;

import java.util.List;

import net.devwool.cyanwool.api.Server;
import net.devwool.cyanwool.api.entity.alive.player.Player;
import net.devwool.cyanwool.api.entity.meta.player.ClientSettings;
import net.devwool.cyanwool.api.network.PlayerNetwork;
import net.devwool.cyanwool.api.world.Position;
import net.devwool.cyanwool.api.world.chunk.Chunk;
import net.devwool.cyanwool.api.world.chunk.ChunkCoords;

import org.spacehq.mc.auth.GameProfile;
import org.spacehq.mc.protocol.data.game.values.PlayerListEntry;
import org.spacehq.mc.protocol.data.game.values.world.Sound;
import org.spacehq.mc.protocol.data.game.values.world.effect.WorldEffect;
import org.spacehq.mc.protocol.data.game.values.world.effect.WorldEffectData;
import org.spacehq.packetlib.packet.Packet;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Particle;

public class CWPlayer extends CWHuman implements Player {

    public CWPlayer(Server server) {
        super(server);
    }

    @Override
    public List<Packet> getUpdatePackets() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Packet> getSpawnPackets() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void sendMessage(String message) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isPlayer() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String getLangCode() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void kickPlayer(String message) {
        // TODO Auto-generated method stub

    }

    @Override
    public GameProfile getGameProfile() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ClientSettings getClientSettings() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isOp() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setOp(boolean flag) {
        // TODO Auto-generated method stub

    }

    @Override
    public void executeCommand(String commandName) {
        // TODO Auto-generated method stub

    }

    @Override
    public void chat(String message) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isBanned() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setBanned(boolean banned) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isWhitelisted() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setWhitelisted(boolean whitelisted) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<ChunkCoords> getChunks() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void playSound(Position pos, String sound, float volume, float pitch) {
        // TODO Auto-generated method stub

    }

    @Override
    public void playSound(Position pos, Sound sound, float volume, float pitch) {
        // TODO Auto-generated method stub

    }

    @Override
    public void playEffect(Position pos, WorldEffect effect, WorldEffectData data) {
        // TODO Auto-generated method stub

    }

    @Override
    public void playParticle(Position pos, Particle particle, int amount, int data) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setTime(long time) {
        // TODO Auto-generated method stub

    }

    @Override
    public void sendChunk(Chunk chunk) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateChunk(Chunk chunk) {
        // TODO Auto-generated method stub

    }

    @Override
    public void respawn() {
        // TODO Auto-generated method stub

    }

    @Override
    public PlayerNetwork getPlayerNetwork() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PlayerListEntry getPlayerListEntry() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isOnline() {
        // TODO Auto-generated method stub
        return false;
    }

}