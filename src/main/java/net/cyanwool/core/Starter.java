package net.cyanwool.core;

import net.cyanwool.api.CyanWool;

public class Starter {

    public static void main(String[] args) {
        CWServer server = new CWServer();
        CyanWool.initServer(server);
    }

}