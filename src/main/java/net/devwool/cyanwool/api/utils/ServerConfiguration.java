package net.devwool.cyanwool.api.utils;

public interface ServerConfiguration {

    public String getIPAdress();

    public int getPort();

    public boolean isOnlineMode();

    public void setOnlineMode(boolean online_mode);

    public int getMaxPlayers();

    public void setMaxPlayers(int maxplayers);

    public String getMotd();

    public void setMotd(String motd);

    public int getViewDistance();

    public void setViewDistance(int view);

    public int getMaxIterationsIO();

    public int setMaxIterationsIO(int iterations);
}