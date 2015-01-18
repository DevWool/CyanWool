package ykt.BeYkeRYkt.CyanWool.api.theards;

import ykt.BeYkeRYkt.CyanWool.api.world.World;

public class WorldThread extends Thread {

    private World world;
    private boolean running;

    public WorldThread(World world) {
        this.world = world;
        this.running = true;
        setName("CyanWorld-" + world.getName());
    }

    public void shutdown() {
        this.running = false;
    }

    @Override
    public void run() {
        while (running) {
            world.onTick();
        }
    }

}