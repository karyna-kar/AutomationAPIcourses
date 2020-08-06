package pages;

import io.restassured.path.json.JsonPath;

public class LibraryAPIParseMethods {

    public static String getBookID(String response)
    {
        JsonPath js = new JsonPath(response);
        String bookID = js.get("ID");
        return bookID;
    }
}
