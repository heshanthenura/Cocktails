package com.heshanthenura.cocktails.controllers;

import com.heshanthenura.cocktails.entity.Cocktail;
import com.heshanthenura.cocktails.services.Communicator;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class CardController implements Initializable {
    @FXML
    public Text alcoholicLbl;

    @FXML
    public ImageView imgView;

    @FXML
    public HBox ingredientsContainer;

    @FXML
    public Text drinkNameLbl;

    @FXML
    private AnchorPane cardRoot;

    public Cocktail cocktail;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            cardRoot.setOnMouseClicked(e -> {
                System.out.println(cocktail.getId());
                System.out.println(cocktail.getDrinkName());
                Communicator.getMainController().detailsImgView.setImage(null);
                Communicator.getMainController().detailsDrinkLbl.setText(cocktail.getDrinkName());
                Communicator.getMainController().detailsPane.setVisible(true);


                for (int i = 0; i < cocktail.getIngredients().size(); i++) {
                    if (!cocktail.getIngredients().get(i).equals("null")) {
                        HBox hBox = new HBox();
                        hBox.setAlignment(Pos.CENTER_LEFT);

                        Text ing = new Text(cocktail.getIngredients().get(i) + " : ");
                        Text amo = new Text(cocktail.getMeasurements().get(i));

                        Image img = new Image("https://www.thecocktaildb.com/images/ingredients/" + cocktail.getIngredients().get(i) + "-Small.png");
                        ImageView imageView = new ImageView(img);

                        ing.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
                        amo.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
                        hBox.getChildren().addAll(imageView, ing, amo);
                        Communicator.getMainController().ingredientsVbox.getChildren().add(hBox);
                    }
                }

                for (int i = 0; i < cocktail.getInstructions().size(); i++) {
                    if (!cocktail.getInstructions().get(i).equals("null")) {
                        HBox hBox = new HBox();
                        hBox.setAlignment(Pos.CENTER_LEFT);
                        Text ins = new Text(cocktail.getInstructions().get(i));
                        ins.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
                        hBox.getChildren().addAll(ins);
                        Communicator.getMainController().instructionsVbox.getChildren().add(hBox);
                    }
                }

                Task<Image> loadImageTask = new Task<>() {
                    @Override
                    protected Image call() throws Exception {
                        return new Image(cocktail.getImgUrl());
                    }
                };

                loadImageTask.setOnSucceeded(event -> {
                    Communicator.getMainController().detailsImgView.setImage(loadImageTask.getValue());
                });

                loadImageTask.setOnFailed(event -> {
                    System.out.println("Failed to load image: " + event.getSource().getException());
                });

                new Thread(loadImageTask).start();
            });
        });
    }
}
