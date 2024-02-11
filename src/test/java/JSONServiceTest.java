import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.ArrayList;
import java.util.List;

public class JSONServiceTest {

    List<CocktailTest> cocktails = new ArrayList<>();

    void getDrinks(String jsonString) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonString);
        ArrayNode arrayNode = (ArrayNode) jsonNode.get("drinks");
        for (JsonNode node : arrayNode) {

            List<String> ingredients = new ArrayList<>();
            List<String> measurements = new ArrayList<>();
            CocktailTest cocktailTest = new CocktailTest();
            cocktailTest.setId(node.get("idDrink").asInt());
            cocktailTest.setDrinkName(node.get("strDrink").asText());
            cocktailTest.setTags(List.of(node.get("strTags").asText().split(",")));
            cocktailTest.setVideo(node.get("strVideo").asText());
            cocktailTest.setCategory(node.get("strCategory").asText());
            cocktailTest.setIBA(node.get("strIBA").asText());
            cocktailTest.setAlcoholic(node.get("strAlcoholic").asText());
            cocktailTest.setGlass(node.get("strGlass").asText());
            cocktailTest.setInstructions(List.of(node.get("strInstructions").asText().split("\\.")));
            cocktailTest.setImgUrl(node.get("strDrinkThumb").asText());

            for (int i = 1; i <= 15; i++) {
                JsonNode ingredientNode = node.get("strIngredient" + i);
                ingredients.add(ingredientNode.asText());
            }
            cocktailTest.setIngredients(ingredients);

            for (int i = 1; i <= 15; i++) {
                JsonNode ingredientNode = node.get("strMeasure" + i);
                measurements.add(ingredientNode.asText());
            }
            cocktailTest.setMeasurements(measurements);



            cocktails.add(cocktailTest);
        }

        for (CocktailTest cocktailTest : cocktails) {
            System.out.println(cocktailTest.toString());
            System.out.println();
        }

    }
}
