package com.heshanthenura.cocktails.controllers;


import com.heshanthenura.cocktails.entity.IngredientAmount;
import com.heshanthenura.cocktails.services.AddCard;
import com.heshanthenura.cocktails.services.Communicator;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    public Button detailsCloseBtn;

    @FXML
    public Text detailsDrinkLbl;

    @FXML
    public ImageView detailsImgView;

    @FXML
    public StackPane detailsPane;

    @FXML
    public FlowPane flowPane;

    @FXML
    public VBox ingredientsVbox;

    @FXML
    public VBox instructionsVbox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {

            new Communicator().setMainController(this);

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

            detailsCloseBtn.setOnMouseClicked(e->{
                detailsPane.setVisible(false);
            });




        });
    }



    //    https://www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita
    //    https://www.thecocktaildb.com/api/json/v1/1/random.php


}
