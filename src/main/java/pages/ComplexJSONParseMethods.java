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

    public static Integer getCopiesOfCourse(String response, String course){
        JsonPath js = new JsonPath(response);
        int copies =0;
        int count = getTotalAmountCourses(response);
        for (int i = 0; i<count; i++)
        {
            if ((js.getString("courses["+i+"].title")).equalsIgnoreCase(course)) {
                copies = js.getInt("courses[" + i + "].copies");
            }
        }
        return copies;
    }

    public static Integer getSumCourse(String response){
        JsonPath js = new JsonPath(response);
        int sum =0;
        int count = getTotalAmountCourses(response);
        for (int i = 0; i<count; i++)
        {
           sum=sum + (js.getInt("courses[" + i + "].price")*js.getInt("courses[" + i + "].copies"));
        }
        return sum;
    }

}
