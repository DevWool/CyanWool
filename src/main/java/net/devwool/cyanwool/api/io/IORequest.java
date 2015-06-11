package net.devwool.cyanwool.api.io;

public interface IORequest {

    public enum RequestType {
        READ, WRITE;
    }

    public void update();

}