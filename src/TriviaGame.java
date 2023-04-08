import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.*;

public class TriviaGame {

    private static final String API_BASE_URL = "https://the-trivia-api.com/api/questions/";
    private static final String API_KEY = "your_api_key_here";

    public static void main(String[] args) throws IOException, JSONException {
        // Get 10 random trivia questions from the API
        JSONArray questions = getTriviaQuestions(10);

        // Display the questions and answers
        for (int i = 0; i < questions.length(); i++) {
            JSONObject question = questions.getJSONObject(i);
            System.out.println("Question " + (i+1) + ": " + question.getString("question"));
            System.out.println("Answer: " + question.getString("answer"));
            System.out.println();
        }
    }

    private static JSONArray getTriviaQuestions(int numQuestions) throws IOException, JSONException {
        // Build the URL for the API request
        String urlString = API_BASE_URL + "?apiKey=" + API_KEY + "&count=" + numQuestions;

        // Send the API request and get the response
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        // Parse the JSON response and extract the questions
        JSONObject jsonResponse = new JSONObject(response.toString());
        return jsonResponse.getJSONArray("results");
    }
}
