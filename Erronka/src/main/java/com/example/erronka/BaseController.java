package com.example.erronka;

import javafx.stage.Stage;

public class BaseController {
    protected Stage usingStage;

    protected Stage getUsingStage() {
        return usingStage;
    }

    public void setUsingStage(Stage usingStage) {
        this.usingStage = usingStage;
    }
}
