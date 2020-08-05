import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class FirstTest {

    //Add new item - StatusCode
    @Test
    public void AddNewItemStatusCodeTest()
    {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        given()
                .log().all()
                .queryParam("key", "qaclick123")
                .contentType("application/json")
                .body("{\n" +
                        "  \"location\": {\n" +
                        "    \"lat\": -38.383494,\n" +
                        "    \"lng\": 33.427362\n" +
                        "  },\n" +
                        "  \"accuracy\": 50,\n" +
                        "  \"name\": \"Frontline house\",\n" +
                        "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                        "  \"address\": \"29, side layout, cohen 09\",\n" +
                        "  \"types\": [\n" +
                        "    \"shoe park\",\n" +
                        "    \"shop\"\n" +
                        "  ],\n" +
                        "  \"website\": \"http://google.com\",\n" +
                        "  \"language\": \"French-IN\"\n" +
                        "}")
                .when().post("/maps/api/place/add/json")
                .then()
                    .log().all()
                    .assertThat().statusCode(200);
    }

    //Add new item - Respond Body
    @Test
    public void AddNewItemRespondBodyTest()
    {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        given()
                .log().all()
                .queryParam("key", "qaclick123")
                .contentType("application/json")
                .body("{\n" +
                        "  \"location\": {\n" +
                        "    \"lat\": -38.383494,\n" +
                        "    \"lng\": 33.427362\n" +
                        "  },\n" +
                        "  \"accuracy\": 50,\n" +
                        "  \"name\": \"Frontline house\",\n" +
                        "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                        "  \"address\": \"29, side layout, cohen 09\",\n" +
                        "  \"types\": [\n" +
                        "    \"shoe park\",\n" +
                        "    \"shop\"\n" +
                        "  ],\n" +
                        "  \"website\": \"http://google.com\",\n" +
                        "  \"language\": \"French-IN\"\n" +
                        "}")
                .when().post("/maps/api/place/add/json")
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body("scope", equalTo("APP"))
                .header("Server", "Apache/2.4.18 (Ubuntu)");
    }

}
