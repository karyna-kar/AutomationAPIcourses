import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Parser;
import utils.APISpecification;
import utils.EndPoints;

import static io.restassured.RestAssured.given;

public class TestRequests {

    private static RequestSpecification requestSpec = APISpecification.getRequestSpecification();
    private static ResponseSpecification responseSpec = APISpecification.getResponseSpecification();

    private int CountryID = 101;
    private Response response = null;


    // Search for Artist
    @Test
    public void searchCountryTest() {
               response =  given()
                .spec(requestSpec)
                .expect().spec(responseSpec)
                .log().all()
                .when()
                .get(EndPoints.COUNTRY + "/" + CountryID);

         Assert.assertEquals(Parser.getCountryID(response), 101);
    }
}
