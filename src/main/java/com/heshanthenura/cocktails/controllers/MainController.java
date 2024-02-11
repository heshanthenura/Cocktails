package com.heshanthenura.cocktails.controllers;

import com.heshanthenura.cocktails.MainApplication;
import com.heshanthenura.cocktails.entity.Cocktail;
import com.heshanthenura.cocktails.services.APIService;
import com.heshanthenura.cocktails.services.JSONService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
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

    @FXML
    private ScrollPane scrollPane;

    Logger logger = Logger.getLogger("info");


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Platform.runLater(() -> {
            new Thread(() -> {

                try {
                    for (Cocktail cocktail : new JSONService().getDrinks(new APIService().getJsonString("https://www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita"))) {
                        new Thread(() -> {
                            Platform.runLater(() -> {
                                FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("card.fxml"));
                                AnchorPane cardRoot = null;
                                try {
                                    cardRoot = loader.load();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                CardController cardController = loader.getController();
                                cardController.drinkNameLbl.setText(cocktail.getDrinkName());
                                cardController.alcoholicLbl.setText(cocktail.getAlcoholic());
                                cardController.imgView.setImage(new Image(cocktail.getImgUrl()));
                                flowPane.getChildren().add(cardRoot);
                            });

                        }).start();

                    }
                    ;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }


            }).start();


        });
    }


}
