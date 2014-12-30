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

  public static Object getFoodName(Object input, int num)
  {
      Object reportData = ((JSONObject) input).get("list");
      Object foodsData = ((JSONObject) reportData).get("item");
      Object foodsArray = ((JSONArray) foodsData).get(num);
      Object foodName = ((JSONObject) foodsArray).get("name");
      return foodName;
  }

  public static Object getCalorieCount(Object input)
  {
      Object reportData = ((JSONObject) input).get("list");
      Object foodsData = ((JSONObject) reportData).get("item");
      Object foodsArray = ((JSONArray) foodsData).get(0);
      Object nutrientData = ((JSONObject) foodsArray).get("nutrients");
      Object nutrientArray = ((JSONArray) nutrientData).get(0);
      Object calorieCount = ((JSONObject) nutrientArray).get("value");
      return calorieCount;
  }

  public static Object getServingSize(Object input)
  {
      /*Object reportData = ((JSONObject) input).get("report");
      Object foodsData = ((JSONObject) reportData).get("foods");
      Object foodsArray = ((JSONArray) foodsData).get(0);
      Object servingSize = ((JSONObject) foodsArray).get("measure");
      return servingSize;*/
      return 0;
  }

	public static void main(String[] args)
	{
    JSONParser parser = new JSONParser();
    try 
    {
      String s = issueGetRequest("http://api.data.gov/usda/ndb/search/?format=json&q=cheese&max=25&offset=0&api_key=DEMO_KEY");
      Object parsedData = parser.parse(s);
      //Object servingSize = getServingSize(parsedData);
      //Object numCalories = getCalorieCount(parsedData);
      for (int i = 0; i < 25; i++)
      {
	Object foodName = getFoodName(parsedData, i);
	System.out.println("Name: " + foodName);
      }
      //System.out.println("Calories: " + numCalories);
      //System.out.println("Serving Size: " + servingSize);
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
