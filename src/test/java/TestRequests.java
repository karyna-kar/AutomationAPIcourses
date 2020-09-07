import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.APISpecification;
import utils.EndPoints;
import static io.restassured.RestAssured.given;

public class TestRequests {

    private static RequestSpecification requestSpec = APISpecification.getRequestSpecification();
    private static ResponseSpecification responseSpec = APISpecification.getResponseSpecification();

    private int CountryID = 101;
    // Search for Artist
    @Test
    public void searchCountryTest()
    {
        Response response = given()
                .spec(requestSpec)
                .expect().spec(responseSpec)
                .log().all()
                .when()
                .get(EndPoints.COUNTRY+"/"+CountryID);

       // Assert.assertNotEquals(getTotalCountFromSearch(response), 0);
        //artistID =  Parser.getAuthorIDFromSearch(response);
    }
}
