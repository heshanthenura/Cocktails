import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class APITest {

//        https://www.thecocktaildb.com/api/json/v1/1/random.php


    public static void main(String[] args) {
        try {

            URI uri = new URI("https://www.thecocktaildb.com/api/json/v1/1/random.php");

            HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();

            connection.setRequestMethod("GET");


            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();


            System.out.println("Response: " + response.toString());


            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
