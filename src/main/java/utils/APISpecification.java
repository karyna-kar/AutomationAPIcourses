package utils;

import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class APISpecification {
    public static RequestSpecification getRequestSpecification() {

        PreemptiveBasicAuthScheme auth = new PreemptiveBasicAuthScheme();
        auth.setUserName(Property.getProperty("auth_UserName"));
        auth.setPassword(Property.getProperty("auth_Password"));

        return new RequestSpecBuilder()
                .setBaseUri(EndPoints.BASEURL)
                .setAuth(auth)
                .log(LogDetail.ALL)
                .build();
    }

    public static ResponseSpecification getResponseSpecification() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }
}
