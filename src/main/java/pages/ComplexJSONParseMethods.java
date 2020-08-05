package pages;

import io.restassured.path.json.JsonPath;

import java.util.HashMap;

public class ComplexJSONParseMethods {
    public static Integer getTotalAmountCourses(String response)
    {
        JsonPath js = new JsonPath(response);
        int count = js.getInt("courses.size()");
        return count;
    }

    public static Integer getPurchaseAmount(String response)
    {
        JsonPath js = new JsonPath(response);
        int amount = js.getInt("dashboard.purchaseAmount");
        return amount;
    }

    public static String getFirstCourseTitle(String response)
    {
        JsonPath js = new JsonPath(response);
        String title = js.get("courses[0].title");
        return title;
    }

    public static HashMap<String, Integer> getAllCourseTitlesPrices(String response)
    {
        JsonPath js = new JsonPath(response);
        HashMap<String, Integer> courses = new HashMap<String, Integer>();
        int count = getTotalAmountCourses(response);
        for (int i = 0; i<count; i++)
        {
            courses.put(js.getString("courses["+i+"].title"), js.getInt("courses["+i+"].price"));
        }
        return courses;
    }
    public static Integer getRPACopies(String response){
        JsonPath js = new JsonPath(response);
        int title = js.get("courses[2].title");
        return title;

    }

}
