package pages;

import io.restassured.path.json.JsonPath;

public class JIRAParseMethods {
    public static String getSessionValue(String response)
    {
        JsonPath js = new JsonPath(response);
        return js.get("session.value");
    }
    public static String getSessionName(String response)
    {
        JsonPath js = new JsonPath(response);
        return js.get("session.name");
    }
    public static String getIssueID(String response)
    {
        JsonPath js = new JsonPath(response);
        return js.get("id");
    }

    public static String getCommentID(String response)
    {
        JsonPath js = new JsonPath(response);
        return js.get("id");
    }


    public static String getAttachmentID(String response)
    {
        JsonPath js = new JsonPath(response);
        return js.get("[0].id");
    }
}
