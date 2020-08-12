import files.LibraryAPIPayloads;
import io.restassured.RestAssured;
import org.testng.annotations.Test;
import pages.LibraryAPIParseMethods;
import pages.OAuthParseMethods;

import static io.restassured.RestAssured.given;

public class OAuth {
    private String token;

   //Step 1 - Get Authorization Code
   //Open on browser this URL - https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php
   //Enter the credentials and parse the response URL to the method below
    private String authCode = OAuthParseMethods.getAuthCode("https://rahulshettyacademy.com/getCourse.php?code=4/3AGJd1WRVHjqF8LAa5UnTYc0TsyxDRauHSYQzlX6dV0pUxQcDe_kaUCd1Sx_BJlQaynVKkDEAotHdGjLdV2pKNs&scope=email%20https://www.googleapis.com/auth/userinfo.email%20openid&authuser=0&hd=mf.grsu.by&prompt=consent");

    //Step 2 - Get Token
    @Test
    public void getToken()
    {
        RestAssured.baseURI = "https://www.googleapis.com";
        String accessTokenResponse = given().urlEncodingEnabled(false)
                .queryParam("code", authCode)
                .queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParam("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
                .queryParam("grant_type", "authorization_code")
                .when().post("/oauth2/v4/token")
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().response().asString();

        System.out.print(authCode);
        token = OAuthParseMethods.getTokenValue(accessTokenResponse);
        System.out.print(token);
    }


    //Step 3 - Sent Access Token
    @Test (dependsOnMethods={"getToken"})
    public void sendToken()
    {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String testResponse = given()
                .queryParam("access_token", token)
                .when().get("/getCourse.php")
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().response().asString();
    }
}
