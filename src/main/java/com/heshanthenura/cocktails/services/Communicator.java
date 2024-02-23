package com.heshanthenura.cocktails.services;

import com.heshanthenura.cocktails.controllers.CardController;
import com.heshanthenura.cocktails.controllers.MainController;
import com.heshanthenura.cocktails.entity.Cocktail;

public class Communicator {
    static MainController mainController;
    static CardController cardController;
    static int cocktailID;

    public static int getCocktailID() {
        return cocktailID;
    }

    public static void setCocktailID(int cocktailID) {
        Communicator.cocktailID = cocktailID;
    }

    public static MainController getMainController() {
        return mainController;
    }

    public static void setMainController(MainController mainController) {
        Communicator.mainController = mainController;
    }

    public static CardController getCardController() {
        return cardController;
    }

    public static void setCardController(CardController cardController) {
        Communicator.cardController = cardController;
    }
}
