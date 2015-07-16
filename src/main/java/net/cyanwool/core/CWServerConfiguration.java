package net.cyanwool.core;

import net.cyanwool.api.utils.ServerConfiguration;

public class CWServerConfiguration implements ServerConfiguration {

    private String adress;
    private int port;
    private boolean online;
    private int maxPlayers;
    private String motd;
    private int viewDistance;
    private int maxIO;
    private int threads;

    @Override
    public String getIPAdress() {
        return adress;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public boolean isOnlineMode() {
        return online;
    }

    @Override
    public void setOnlineMode(boolean online_mode) {
        this.online = online_mode;
    }

    @Override
    public int getMaxPlayers() {
        return maxPlayers;
    }

    @Override
    public void setMaxPlayers(int maxplayers) {
        this.maxPlayers = maxplayers;
    }

    @Override
    public String getMotd() {
        return motd;
    }

    @Override
    public void setMotd(String motd) {
        this.motd = motd;
    }

    @Override
    public int getViewDistance() {
        return viewDistance;
    }

    @Override
    public void setViewDistance(int view) {
        this.viewDistance = view;
    }

    @Override
    public int getMaxIterationsIO() {
        return maxIO;
    }

    @Override
    public void setMaxIterationsIO(int iterations) {
        this.maxIO = iterations;
    }

    @Override
    public int getCountThreads() {
        return threads;
    }

    @Override
    public void setCountThreads(int count) {
        threads = count;
    }

}