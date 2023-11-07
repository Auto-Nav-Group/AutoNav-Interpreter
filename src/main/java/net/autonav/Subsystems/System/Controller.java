package net.autonav.Subsystems.System;

public class Controller {
    private static Controller instance;

    private Controllers controller;

    private Controller() {
        controller = Controllers.MANUAL;
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }
    
    public Controllers getController() {
        return controller;
    }

    public void setController(Controllers controller) {
        this.controller = controller;
    }

    static enum Controllers {
        AUTO,
        MANUAL;
    }
}
