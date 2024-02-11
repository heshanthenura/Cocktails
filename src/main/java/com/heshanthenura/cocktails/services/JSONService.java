package com.heshanthenura.cocktails.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.heshanthenura.cocktails.entity.Cocktail;

import java.util.ArrayList;
import java.util.List;

public class JSONService {

    List<Cocktail> cocktails = new ArrayList<>();

    public List<Cocktail> getDrinks(String jsonString) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonString);
        ArrayNode arrayNode = (ArrayNode) jsonNode.get("drinks");
        for (JsonNode node : arrayNode) {

            List<String> ingredients = new ArrayList<>();
            List<String> measurements = new ArrayList<>();
            Cocktail cocktail = new Cocktail();
            cocktail.setId(node.get("idDrink").asInt());
            cocktail.setDrinkName(node.get("strDrink").asText());
            cocktail.setTags(List.of(node.get("strTags").asText().split(",")));
            cocktail.setVideo(node.get("strVideo").asText());
            cocktail.setCategory(node.get("strCategory").asText());
            cocktail.setIBA(node.get("strIBA").asText());
            cocktail.setAlcoholic(node.get("strAlcoholic").asText());
            cocktail.setGlass(node.get("strGlass").asText());
            cocktail.setInstructions(List.of(node.get("strInstructions").asText().split("\\.")));
            cocktail.setImgUrl(node.get("strDrinkThumb").asText());

            for (int i = 1; i <= 15; i++) {
                JsonNode ingredientNode = node.get("strIngredient" + i);
                ingredients.add(ingredientNode.asText());
            }
            cocktail.setIngredients(ingredients);

            for (int i = 1; i <= 15; i++) {
                JsonNode ingredientNode = node.get("strMeasure" + i);
                measurements.add(ingredientNode.asText());
            }
            cocktail.setMeasurements(measurements);


            cocktails.add(cocktail);
        }

        return cocktails;
    }
}
