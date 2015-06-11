package net.devwool.cyanwool.api.scheduler;

public interface Task extends Runnable {

    public boolean cancel();

    public boolean isCancelled();

    // public boolean isDone();

    public int getID();
}