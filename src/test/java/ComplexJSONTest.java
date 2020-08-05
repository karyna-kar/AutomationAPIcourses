import files.ComplexJSONPayloads;
import org.testng.annotations.Test;
import pages.ComplexJSONParseMethods;

import java.util.HashMap;
import java.util.Map;

public class ComplexJSONTest {
    String response = ComplexJSONPayloads.TestResponse();

    //Print No of courses returned by API
    @Test
    public void NumberOfCoursesTest()
    {
        int count = ComplexJSONParseMethods.getTotalAmountCourses(response);
        System.out.print(count);
    }

    //Print Purchase Amount
    @Test
    public void PurchaseAmountTest()
    {
      int amount = ComplexJSONParseMethods.getPurchaseAmount(response);
      System.out.print(amount);
    }

    //Print Title of the first course
    @Test
    public void FirstCourseTitleTest()
    {
        String title = ComplexJSONParseMethods.getFirstCourseTitle(response);
        System.out.print(title);
    }

    //Print All course titles and their respective Prices
    @Test
    public void AllCourseTitlesPricesTest()
    {
        HashMap<String, Integer> courses  = ComplexJSONParseMethods.getAllCourseTitlesPrices(response);
        for (Map.Entry<String, Integer> entry: courses.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

    //Print no of copies sold by RPA Course
    @Test
    public void RPACopiesTest()
    {

    }
}
