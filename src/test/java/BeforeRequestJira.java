import files.Payloads;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;
import pages.JIRAParseMethods;

import static io.restassured.RestAssured.given;

public class BeforeRequestJira {
    String cookie;
    @BeforeTest
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080/";
         String response =    given()
                .log().all()
                .contentType("application/json")
                .body("{ \"username\": \"karyna.karmaza\", \"password\": \"25011994kar\" }")
                .when().post("/rest/auth/1/session")
                .then()
                .log().all()
                .assertThat().statusCode(200)
                 .extract().response().asString();

        cookie = JIRAParseMethods.getSessionName(response)+'='+JIRAParseMethods.getSessionValue(response);
    }
    public String get(){
        return this.cookie;
    }
}
