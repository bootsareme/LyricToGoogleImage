import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.HttpURLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * <h1>GoogleSearchAPI Class</h1>
 * <p>
 * Provides interface to Google Search REST API
 * and decodes JSON images.
 * </p>
 *
 * @author  Vincent Zhang
 * @version 1.0
 * @since   2021-9-12
 */
public class GoogleSearchAPI {

    private String _apiKey, _query, _link;
    private static int counter = 0;

    public GoogleSearchAPI(String apiKey, String query) {
        this._apiKey = apiKey;
        this._query = query;
    }

    /**
     * <h2>searchImage Method</h2>
     * <p>
     * Searches images by establishing HTTP connection with REST API.
     * Proceeds to parse JSON to get the first image link.
     * </p>
     */
    public void searchImage() {
        try {
            URL url = new URL("https://www.googleapis.com/customsearch/v1?key=" + this._apiKey + "&cx=a65b5b6714ff3433c&q="
                    + this._query + "&searchType=image&num=1");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            if (connection.getResponseCode() != 200)
                throw new RuntimeException("HttpResponseCode: " + connection.getResponseCode());
            else {
                StringBuilder inline = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                // Write all the JSON data into a string using a scanner
                while (scanner.hasNext())
                    inline.append(scanner.nextLine());

                // Using the JSON simple library parse the string into a json object
                JSONParser parse = new JSONParser();
                JSONObject data_obj = (JSONObject) parse.parse(inline.toString());
                JSONArray arr = (JSONArray) data_obj.get("items");

                for (Object o : arr) {
                    JSONObject new_obj = (JSONObject) o;
                    this._link = new_obj.get("link").toString();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * <h2>downloadImageFromLink Method</h2>
     * <p>
     * Downloads image from link and parent it
     * to the appropriate folder.
     * </p>
     * @param outputFolder Folder where downloaded file is stored.
     */
    public void downloadImageFromLink(File outputFolder, boolean isLightWeight, String lyric) {
        try {
            InputStream input = new URL(this._link).openStream();
            String filename;

            if (!isLightWeight) {
                filename = counter + ".png";
                counter++;
            } else filename = lyric + ".png";

            Files.copy(input, Paths.get(outputFolder.getAbsolutePath() + "/" + filename));
            input.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}