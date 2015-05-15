package net.devwool.cyanwool.core;

import net.devwool.cyanwool.api.CyanWool;

public class Starter {

    public static void main(String[] args) {
        CWServer server = new CWServer();
        CyanWool.initServer(server);
    }

}