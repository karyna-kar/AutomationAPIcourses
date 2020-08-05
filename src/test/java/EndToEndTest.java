import files.Payloads;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ParseMethods;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class EndToEndTest {
    String placeID;
    String newAddress = "70 Summer walk TEST, USA";

    //Step1 - Adding new item + extract respond
    @Test
    public void AddNewItemTest()
    {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String test_response = given()
                .log().all()
                .queryParam("key", "qaclick123")
                .contentType("application/json")
                .body(Payloads.AddItemPayload())
                .when().post("/maps/api/place/add/json")
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().response().asString();

        placeID = ParseMethods.getPlaceID(test_response);
    }

    //Step2 - Adding new item + extract respond
    @Test(dependsOnMethods={"AddNewItemTest"})
    public void UpdateItemTest()
    {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        given()
                .log().all()
                .queryParam("key", "qaclick123")
                .contentType("application/json")
                .body(Payloads.UpdateItemPayload(placeID, newAddress))
                .when().put("/maps/api/place/update/json")
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body("msg", equalTo("Address successfully updated"));
    }

    //Step3 - Getting new item + extract respond
    @Test (dependsOnMethods={"UpdateItemTest"})
    public void GetItemTest()
    {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String reponse = given()
                .log().all()
                .queryParam("key", "qaclick123")
                .queryParam("place_id", placeID)
                .when().get("/maps/api/place/get/json")
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body("address", equalTo(newAddress))
                .extract().response().asString();

        String actualAddress= ParseMethods.getAddress(reponse);
        Assert.assertEquals(actualAddress, newAddress);
    }

}
