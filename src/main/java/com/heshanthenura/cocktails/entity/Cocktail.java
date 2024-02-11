package com.heshanthenura.cocktails.entity;

import java.util.List;

public class Cocktail {

    int id;
    String drinkName;
    String drinkAlternate;
    List<String> tags;
    String video;
    String category;
    String IBA;
    String alcoholic;
    String glass;
    List<String> instructions;
    String imgUrl;
    List<String> ingredients;
    List<String> measurements;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public String getDrinkAlternate() {
        return drinkAlternate;
    }

    public void setDrinkAlternate(String drinkAlternate) {
        this.drinkAlternate = drinkAlternate;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIBA() {
        return IBA;
    }

    public void setIBA(String IBA) {
        this.IBA = IBA;
    }

    public String getAlcoholic() {
        return alcoholic;
    }

    public void setAlcoholic(String alcoholic) {
        this.alcoholic = alcoholic;
    }

    public String getGlass() {
        return glass;
    }

    public void setGlass(String glass) {
        this.glass = glass;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<String> measurements) {
        this.measurements = measurements;
    }

    @Override
    public String toString() {
        return "CocktailTest{" +
                "id=" + id +
                ", drinkName='" + drinkName + '\'' +
                ", drinkAlternate='" + drinkAlternate + '\'' +
                ", tags=" + tags +
                ", video='" + video + '\'' +
                ", category='" + category + '\'' +
                ", IBA='" + IBA + '\'' +
                ", alcoholic='" + alcoholic + '\'' +
                ", glass='" + glass + '\'' +
                ", instructions=" + instructions +
                ", imgUrl='" + imgUrl + '\'' +
                ", ingredients=" + ingredients +
                ", measurements=" + measurements +
                '}';
    }
}
