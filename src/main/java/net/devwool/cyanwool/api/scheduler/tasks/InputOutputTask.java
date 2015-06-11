package net.devwool.cyanwool.api.scheduler.tasks;

import java.util.concurrent.CopyOnWriteArrayList;

import net.devwool.cyanwool.api.Server;
import net.devwool.cyanwool.api.io.IORequest;

public class InputOutputTask implements Runnable {

    private CopyOnWriteArrayList<IORequest> requests;
    private Server server;
    private int i;
    private boolean running;

    public InputOutputTask(Server server) {
        this.server = server;
        this.requests = new CopyOnWriteArrayList<IORequest>();
        this.i = 0;
        this.running = true;
    }

    public void shutdown() {
        this.running = false;
    }

    public void addRequest(IORequest request) {
        if (running) {
            this.requests.add(request);
        }
    }

    @Override
    public void run() {
        if (isRunning()) {
            this.i = 0;
            while (!this.requests.isEmpty() && i <= server.getServerConfiguration().getMaxIterationsIO()) {
                i++;
                IORequest request = requests.get(0);
                request.update();
                requests.remove(0);
            }
        }
    }

    public boolean isRunning() {
        return running;
    }

}