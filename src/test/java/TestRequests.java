import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Parser;
import utils.APISpecification;
import utils.EndPoints;
import utils.Logs;

import static io.restassured.RestAssured.given;

public class TestRequests {

    private static final RequestSpecification requestSpec = APISpecification.getRequestSpecification();
    private static final ResponseSpecification responseSpec = APISpecification.getResponseSpecification();

    private int CountryID;
    private Response response = null;


    // Search all Countries
    @Test
    public void searchCountryTest() {
        response = given()
                .spec(requestSpec)
                .expect().spec(responseSpec)
                .when()
                .get(EndPoints.COUNTRY);
        Assert.assertNotEquals(Parser.getTotalNumbersOfCountries(response), 0);
        CountryID = Parser.getCountryID(response);
        Logs.info("Request: " + requestSpec.log().method().toString());
    }

    // Search Countries by ID
    @Test (dependsOnMethods={"searchCountryTest"})
    public void searchCountryByIDTest() {
               response =  given()
                .spec(requestSpec)
                .expect().spec(responseSpec)
                .when()
                .get(EndPoints.COUNTRY + "/" + CountryID);

         Assert.assertEquals(Parser.getCountryID(response), 100);
    }

    // Search Total Number of County regions
    @Test// (dependsOnMethods={"searchCountryByIDTest"})
    public void checkRegionListTest() {
        response =  given()
                .spec(requestSpec)
                .expect().spec(responseSpec)
              //  .log().all()
                .when()
                .get(EndPoints.COUNTRY + "/" + 100);

      //  Assert.assertNotEquals(Parser.getTotalNumbersOfRegions(response), 0);
        Assert.assertEquals(Parser.getTotalNumbersOfRegions(response), 0);
    }

}
