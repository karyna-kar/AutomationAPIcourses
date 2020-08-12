package pages;

import io.restassured.path.json.JsonPath;

public class OAuthParseMethods {

    public static String getAuthCode(String url)
    {
        return  (url.split("code=")[1]).split("&scope")[0];
    }

    public static String getTokenValue(String response)
    {
        JsonPath js = new JsonPath(response);
        return js.get("access_token");
    }
}


