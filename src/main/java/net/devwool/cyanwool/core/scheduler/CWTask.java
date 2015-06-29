package net.devwool.cyanwool.core.scheduler;

import java.util.concurrent.ScheduledFuture;

import net.devwool.cyanwool.api.scheduler.Task;

public class CWTask implements Task {

    private ScheduledFuture<?> scheduledFuture;
    private int id;

    public CWTask(int id, ScheduledFuture<?> scheduledFuture) {
        this.id = id;
        this.scheduledFuture = scheduledFuture;
    }

    public boolean cancel(boolean mayInterruptIfRunning) {
        return this.scheduledFuture.cancel(mayInterruptIfRunning);
    }

    @Override
    public boolean cancel() {
        return cancel(false);
    }

    @Override
    public ScheduledFuture<?> getScheduledFuture() {
        return this.scheduledFuture;
    }

    @Override
    public boolean isCancelled() {
        return getScheduledFuture().isCancelled();
    }

    @Override
    public boolean isDone() {
        return getScheduledFuture().isDone();
    }

    @Override
    public int getID() {
        return id;
    }

}
