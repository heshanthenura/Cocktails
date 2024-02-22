package com.heshanthenura.cocktails.controllers;


import com.heshanthenura.cocktails.MainApplication;
import com.heshanthenura.cocktails.entity.Cocktail;
import com.heshanthenura.cocktails.services.APIService;
import com.heshanthenura.cocktails.services.AddCard;
import com.heshanthenura.cocktails.services.JSONService;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class MainController implements Initializable {

    @FXML
    private FlowPane flowPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            for (int i = 0; i < 20; i++) {
                Task<Void> task = new AddCard().createAddCardTask(flowPane, "https://www.thecocktaildb.com/api/json/v1/1/random.php");
                task.setOnSucceeded(event -> {
                    System.out.println("Task completed successfully.");
                });
                task.setOnFailed(event -> {
                    System.out.println("Task failed."+event);
                });
                new Thread(task).start();
            }
        });
    }


    //    https://www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita
    //    https://www.thecocktaildb.com/api/json/v1/1/random.php


}
