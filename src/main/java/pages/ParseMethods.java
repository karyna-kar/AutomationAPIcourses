package pages;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ParseMethods {

    public static JsonObject getJsonObjectFromResponse(Response response) {
        return new JsonParser().parse(response.getBody().asString()).getAsJsonObject();
    }

    public static String getPlaceID(String response)
    {
        JsonPath js = new JsonPath(response);
        return js.get("place_id");
    }

    public static String getAddress(String response)
    {
        JsonPath js = new JsonPath(response);
        return js.get("address");
    }
}
