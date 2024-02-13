package com.heshanthenura.cocktails.controllers;

import com.heshanthenura.cocktails.MainApplication;
import com.heshanthenura.cocktails.entity.Cocktail;
import com.heshanthenura.cocktails.services.APIService;
import com.heshanthenura.cocktails.services.JSONService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
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

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button refreshBtn;

    @FXML
    private TextField searchBarInp;

    Logger logger = Logger.getLogger("info");


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Platform.runLater(() -> {

            refreshBtn.setOnMouseClicked((event) -> {
                flowPane.getChildren().clear();
            });

            searchBarInp.setOnKeyTyped(e->{
                flowPane.getChildren().clear();
                System.out.println(searchBarInp.getText());
                String s = "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=" + searchBarInp.getText();
                getCocktails(s.toString());
            });

            new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    getCocktails("https://www.thecocktaildb.com/api/json/v1/1/random.php");
                }
            }).start();

        });
    }
//    https://www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita
    //    https://www.thecocktaildb.com/api/json/v1/1/random.php
    void getCocktails(String url) {
        new Thread(() -> {
            try {
                for (Cocktail cocktail : new JSONService().getDrinks(new APIService().getJsonString(url))) {
                    new Thread(() -> {
                        Platform.runLater(() -> {
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
                            flowPane.getChildren().add(cardRoot);
                        });
                    }).start();
                }
            } catch (IOException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }


}
