package com.heshanthenura.cocktails.services;


import com.heshanthenura.cocktails.MainApplication;
import com.heshanthenura.cocktails.controllers.CardController;
import com.heshanthenura.cocktails.entity.Cocktail;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.net.URISyntaxException;

public class AddCard {


    public void addCard(FlowPane flowPane, String url) {
        try {
            for (Cocktail cocktail : new JSONService().getDrinks(new APIService().getJsonString(url))) {
                FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("card.fxml"));
                AnchorPane cardRoot;
                try {
                    cardRoot = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                CardController cardController = loader.getController();
                cardController.drinkNameLbl.setText(cocktail.getDrinkName());
                cardController.alcoholicLbl.setText(cocktail.getAlcoholic());
                cardController.imgView.setImage(new Image(cocktail.getImgUrl()));
                cardController.cocktail=cocktail;
                Platform.runLater(()->{
                    flowPane.getChildren().add(cardRoot);
                });
            }
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }


    public Task<Void> createAddCardTask(FlowPane flowPane, String url) {
        return new Task<>() {
            @Override
            protected Void call() throws Exception {
                addCard(flowPane, url);
                return null;
            }
        };
    }


}
