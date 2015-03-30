import java.util.*;
import java.lang.Exception;
import java.io.*;
import java.net.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class RESTCall {
    public static String issueGetRequest(String urlQuery) throws IOException {
        URL url = new URL(urlQuery);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        if (connection.getResponseCode() != 200)
            throw new IOException(connection.getResponseMessage());

        // Buffer the result into a string
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        reader.close();

        connection.disconnect();
        return sb.toString();
    }

    public static Object getServingSize(Object input, int index) {
        Object reportData = ((JSONObject) input).get("report");
        Object foodsData = ((JSONObject) reportData).get("foods");
        Object foodsArray = ((JSONArray) foodsData).get(index);
        Object servingSize = ((JSONObject) foodsArray).get("measure");
        return servingSize;
    }

    public static Object getCalories(Object input, int index) {
        Object reportData = ((JSONObject) input).get("report");
        Object foodsData = ((JSONObject) reportData).get("foods");
        Object foodsArray = ((JSONArray) foodsData).get(index);
        Object nutrientsData = ((JSONObject) foodsArray).get("nutrients");
        Object nutrientsArray = ((JSONArray) nutrientsData).get(index);
        Object value = ((JSONObject) nutrientsArray).get("value");
        return value;
    }

    public static Object getName(Object input, int index) {
        Object listData = ((JSONObject) input).get("list");
        Object itemData = ((JSONObject) listData).get("item");
        Object itemArray = ((JSONArray) itemData).get(index);
        Object name = ((JSONObject) itemArray).get("name");
        return name;
    }

    public static Object getNDB(Object input, int index) {
        Object listData = ((JSONObject) input).get("list");
        Object itemData = ((JSONObject) listData).get("item");
        Object itemArray = ((JSONArray) itemData).get(index);
        Object ndb = ((JSONObject) itemArray).get("ndbno");
        return ndb;
    } 

    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        for (int i = 0; i < 20; i++)
        {
            try {
                String name = issueGetRequest("http://api.data.gov/usda/ndb/search/?format=json&q=papa%20johns&max=25&offset=0&api_key=3hVnhFvj1VAagD29p9Q5b5MeYenARhmAvyX2suCf");
                Object parsedName = parser.parse(name);
                Object foodName = getName(parsedName, i);
                Object ndbno = getNDB(parsedName, i);
                System.out.println("Name: " + foodName);
                
                Object parsedData = parser.parse(issueGetRequest("http://api.data.gov/usda/ndb/nutrients/?format=json&api_key=3hVnhFvj1VAagD29p9Q5b5MeYenARhmAvyX2suCf&nutrients=208&ndbno=" + ndbno + "&max=1"));
                Object servingSize = getServingSize(parsedData, 0);
                Object calories = getCalories(parsedData, 0);
                System.out.println("SERVING SIZE: " + servingSize);
                System.out.println("CALORIES: " + calories);
            } catch (ParseException | IOException | IndexOutOfBoundsException e) {
                if (e instanceof ParseException)
                    System.out.println("Error parsing JSON");
                else if (e instanceof IOException)
                    System.out.println("Error during GET request.");
                else if (e instanceof IndexOutOfBoundsException)
                    break;
            }
        }
    }
}
