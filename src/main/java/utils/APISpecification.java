package utils;

import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class APISpecification {
    public static RequestSpecification getRequestSpecification() {

        PreemptiveBasicAuthScheme auth = new PreemptiveBasicAuthScheme();
        auth.setUserName(Property.getProperty("auth_UserName"));
        auth.setPassword(Property.getProperty("auth_Password"));

        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setBaseUri(EndPoints.BASEURL)
                .setAuth(auth)
                .log(LogDetail.METHOD)
                .log(LogDetail.URI)
                .log(LogDetail.HEADERS)
                .log(LogDetail.BODY)
                .build();
      //  Logs.info(reqSpec.log().all().toString());

        return reqSpec;
    }

    public static ResponseSpecification getResponseSpecification() {
        ResponseSpecification resSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .log(LogDetail.ALL)
                .build();

       // Logs.info(resSpec.log().all().toString());
        return resSpec;
    }
}
