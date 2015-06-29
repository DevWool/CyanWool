package net.devwool.cyanwool.api.scheduler;

import java.util.concurrent.Future;

public interface Task {

    public boolean cancel();

    public boolean isCancelled();

    public boolean isDone();

    public int getID();

    public Future<?> getScheduledFuture();
}