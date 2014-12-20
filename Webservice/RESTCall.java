import java.util.*;
import java.lang.Exception;
import java.io.*;
import java.net.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class RESTCall
{
    public static String issueGetRequest(String urlQuery) throws IOException 
    {
      URL url = new URL(urlQuery);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();

      if (connection.getResponseCode() != 200)
        throw new IOException(connection.getResponseMessage());
        
      // Buffer the result into a string
      BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      StringBuilder sb = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) 
      {
        sb.append(line);
      }
      reader.close();

    connection.disconnect();
    return sb.toString();
  }

	public static void main(String[] args)
	{
    JSONParser parser = new JSONParser();
    try 
    {
      String s = issueGetRequest("http://api.data.gov/usda/ndb/nutrients/?format=json&api_key=3hVnhFvj1VAagD29p9Q5b5MeYenARhmAvyX2suCf&nutrients=208&max=1");
      Object result = parser.parse(s);
      JSONObject jsonObj = new JSONObject((JSONObject) result);
      JSONObject report = (JSONObject)jsonObj.get("report");
      JSONArray reportArr = (JSONArray) report.get("foods");
      JSONObject foods = (JSONObject) reportArr.get(0);
      JSONArray nutrients = (JSONArray) foods.get("nutrients");
      JSONObject calories = (JSONObject) nutrients.get(0);
      System.out.println(calories.get("value") + " calories");
    }
    catch (ParseException e)
    {
        System.out.println("Error parsing JSON");
    }
    catch (IOException e)
    {
        System.out.println("Error during GET request."); 
    }
	}
}
