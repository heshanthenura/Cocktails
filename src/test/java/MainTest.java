import java.io.IOException;
import java.net.URISyntaxException;

public class MainTest {
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
       new JSONServiceTest().getDrinks(new API().getJsonString("https://www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita"));

    }
}
