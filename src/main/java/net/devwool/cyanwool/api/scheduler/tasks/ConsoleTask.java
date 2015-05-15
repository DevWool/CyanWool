package net.devwool.cyanwool.api.scheduler.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import net.devwool.cyanwool.api.Server;

public class ConsoleTask implements Runnable {

    private Server server;
    private boolean running;

    public ConsoleTask(Server server) {
        this.server = server;
        this.running = true;
    }

    public void shutdown() {
        this.running = false;
    }

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));// Console...
        while (running) {
            // Maybe...
            try {
                String line = reader.readLine();
                server.getCommandManager().dispatchCommand(server.getConsoleCommandSender(), line);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}