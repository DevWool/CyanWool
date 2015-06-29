package net.devwool.cyanwool.core.scheduler;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import net.devwool.cyanwool.api.Server;
import net.devwool.cyanwool.api.scheduler.Scheduler;
import net.devwool.cyanwool.api.scheduler.Task;

public class CWScheduler implements Scheduler {

    private final HashMap<Integer, Task> tasks;
    private ScheduledExecutorService es;
    private int id;
    private Server server;

    public CWScheduler(Server server) {
        this.server = server;
        this.tasks = new HashMap<Integer, Task>();
        start();
    }

    private void start() {
        runTaskRepeat(new Runnable() {

            @Override
            public void run() {
                for (Task task : tasks.values()) {
                    if (task.isCancelled() || task.isDone()) {
                        tasks.remove(task.getID());
                    }
                }
            }
        }, 1, 1);
    }

    @Override
    public void shutdown() {
        cancelAllTasks();
        getService().shutdown();
    }

    @Override
    public Task runTask(Runnable r, long delay) {
        long ticks = delay * 50;
        ScheduledFuture<?> sch = getService().schedule(r, ticks, TimeUnit.MILLISECONDS);
        Task task = new CWTask(id, sch);
        tasks.put(id, task);
        id++;
        return task;
    }

    @Override
    public Task runTaskRepeat(Runnable r, long startAfter, long delay) {
        long afterTicks = startAfter * 50;
        long ticks = delay * 50;
        ScheduledFuture<?> sch = getService().scheduleWithFixedDelay(r, afterTicks, ticks, TimeUnit.MILLISECONDS);
        Task task = new CWTask(id, sch);
        tasks.put(id, task);
        id++;
        return task;
    }

    @Override
    public Task getTask(int id) {
        return tasks.get(id);
    }

    @Override
    public boolean cancelTask(int id) {
        if (getTask(id) != null) {
            return getTask(id).cancel();
        }
        return false;
    }

    @Override
    public void cancelAllTasks() {
        for (Task task : tasks.values()) {
            task.getScheduledFuture().cancel(false);
        }
        tasks.clear();
        try {
            getService().awaitTermination(2, TimeUnit.SECONDS);
        } catch (InterruptedException ex) {
            getServer().getLogger().error("Error halting scheduler service", ex);
        }
    }

    @Override
    public Server getServer() {
        return server;
    }

    @Override
    public int getLastId() {
        return id;
    }

    public ScheduledExecutorService getService() {
        if (es == null || es.isShutdown()) {
            es = Executors.newScheduledThreadPool(getServer().getServerConfiguration().getCountThreads());
        }
        return es;
    }
}