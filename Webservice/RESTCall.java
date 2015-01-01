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

    public static Object getServingSize(Object input) {
        Object reportData = ((JSONObject) input).get("report");
        Object foodsData = ((JSONObject) reportData).get("foods");
        Object foodsArray = ((JSONArray) foodsData).get(0);
        Object servingSize = ((JSONObject) foodsArray).get("measure");
        return servingSize;
    }

    public static Object getCalories(Object input) {
        Object reportData = ((JSONObject) input).get("report");
        Object foodsData = ((JSONObject) reportData).get("foods");
        Object foodsArray = ((JSONArray) foodsData).get(0);
        Object nutrientsData = ((JSONObject) foodsArray).get("nutrients");
        Object nutrientsArray = ((JSONArray) nutrientsData).get(0);
        Object value = ((JSONObject) nutrientsArray).get("value");
        return value;
    }

    public static Object getName(Object input) {
        Object listData = ((JSONObject) input).get("list");
        Object itemData = ((JSONObject) listData).get("item");
        Object itemArray = ((JSONArray) itemData).get(0);
        Object name = ((JSONObject) itemArray).get("name");
        return name;
    }

    public static Object getNDB(Object input) {
        Object listData = ((JSONObject) input).get("list");
        Object itemData = ((JSONObject) listData).get("item");
        Object itemArray = ((JSONArray) itemData).get(0);
        Object ndb = ((JSONObject) itemArray).get("ndbno");
        return ndb;
    }

    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        try {
            String name = issueGetRequest("http://api.data.gov/usda/ndb/search/?format=json&q=papa%20johns&max=25&offset=0&api_key=3hVnhFvj1VAagD29p9Q5b5MeYenARhmAvyX2suCf");
            Object parsedName = parser.parse(name);
            Object foodName = getName(parsedName);
            Object ndbno = getNDB(parsedName);
            System.out.println("Name: " + foodName);

            String s = issueGetRequest("http://api.data.gov/usda/ndb/nutrients/?format=json&api_key=3hVnhFvj1VAagD29p9Q5b5MeYenARhmAvyX2suCf&nutrients=208&ndbno=" + ndbno + "&max=1");
            Object parsedData = parser.parse(s);
            Object servingSize = getServingSize(parsedData);
            Object calories = getCalories(parsedData);
            System.out.println("SERVING SIZE: " + servingSize);
            System.out.println("CALORIES: " + calories);
        } catch (ParseException e) {
            System.out.println("Error parsing JSON");
        } catch (IOException e) {
            System.out.println("Error during GET request.");
        }
    }
}
