import files.JIRAPayloads;
import io.restassured.RestAssured;
import org.testng.annotations.Test;
import pages.JIRAParseMethods;

import java.io.File;

import static io.restassured.RestAssured.given;

public class JiraAPITest extends BeforeRequestJira {
    private String IssueID;
    private String CommentID;
    private String AttachmentID;
    //Create an issue
    @Test
    public void createIssueTest() {
        RestAssured.baseURI = "http://localhost:8080";
        String response =    given()
                .log().all()
                .header("cookie", cookie)
                .contentType("application/json")
                .body(JIRAPayloads.createIssuePayload("Fifth issue"))
                .when().post("/rest/api/2/issue")
                .then()
                .log().all()
                .assertThat().statusCode(201)
                .extract().response().asString();

        IssueID = JIRAParseMethods.getIssueID(response);
    }

    //Add a comment to the issue
    @Test (dependsOnMethods={"createIssueTest"})
    public void addCommentTest() {
        RestAssured.baseURI = "http://localhost:8080";
        String response = given()
                .log().all()
                .header("cookie", cookie)
                .contentType("application/json")
                .body(JIRAPayloads.createIssueCommentPayload())
                .when().post("/rest/api/2/issue/"+IssueID+"/comment")
                .then()
                .log().all()
                .assertThat().statusCode(201)
                .extract().response().asString();

        CommentID = JIRAParseMethods.getCommentID(response);
    }

    //Add Attachment to the issue
    @Test(dependsOnMethods={"addCommentTest"})
    public void addAttachmentTest()
    {
        RestAssured.baseURI = "http://localhost:8080";
        String response = given()
                .log().all()
                .header("cookie", cookie)
                .header("X-Atlassian-Token", "no-check")
                .contentType("multipart/form-data")
                //.body(JIRAPayloads.createIssueCommentPayload())
                .multiPart("file", new File("jira.txt"))
                .when().post("/rest/api/2/issue/"+IssueID+"/attachments")
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().response().asString();

        AttachmentID = JIRAParseMethods.getAttachmentID(response);
    }

    //Get created issue
    @Test(dependsOnMethods={"addAttachmentTest"})
    public void getIssueTest()
    {
        RestAssured.baseURI = "http://localhost:8080";
        String response = given()
                .log().all()
                .header("cookie", cookie)
                .contentType("application/json")
                .when().get("/rest/api/2/issue/"+IssueID)
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().response().asString();
    }



    //Get created comment
    @Test(dependsOnMethods={"getIssueTest"})
    public void getCommentTest()
    {
        RestAssured.baseURI = "http://localhost:8080";
        String response = given()
                .log().all()
                .header("cookie", cookie)
                .contentType("application/json")
                .when().get("/rest/api/2/issue/"+IssueID+"/comment")
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().response().asString();
    }


    //Get Attachment
    @Test(dependsOnMethods={"getCommentTest"})
    public void getAttachmentTest()
    {
        RestAssured.baseURI = "http://localhost:8080";
        String response = given()
                .log().all()
                .header("cookie", cookie)
                .contentType("application/json")
                .when().get("/rest/api/2/attachment/"+AttachmentID)
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().response().asString();
    }

    //Delete a comment of the issue
    @Test (dependsOnMethods={"getAttachmentTest"})
    public void deleteCommentTest() {
        RestAssured.baseURI = "http://localhost:8080";
        String response = given()
                .log().all()
                .header("cookie", cookie)
                .contentType("application/json")
                .when().delete("/rest/api/2/issue/"+IssueID+"/comment/"+ CommentID)
                .then()
                .log().all()
                .assertThat().statusCode(204)
                .extract().response().asString();
    }

    //Delete an attachment of the issue
    @Test (dependsOnMethods={"deleteCommentTest"})
    public void deleteAttachmentTest() {
        RestAssured.baseURI = "http://localhost:8080";
        String response = given()
                .log().all()
                .header("cookie", cookie)
                .contentType("application/json")
                .when().delete("/rest/api/2/attachment/"+AttachmentID)
                .then()
                .log().all()
                .assertThat().statusCode(204)
                .extract().response().asString();
    }

    //Delete issue
    @Test (dependsOnMethods={"deleteAttachmentTest"})
    public void deleteIssueTest() {
        RestAssured.baseURI = "http://localhost:8080";
        String response =    given()
                .log().all()
                .header("cookie", cookie)
                .contentType("application/json")
                //.body(JIRAPayloads.CreateIssuePayload("Second issue"))
                .when().delete("/rest/api/2/issue/"+IssueID)
                .then()
                .log().all()
                .assertThat().statusCode(204)
                .extract().response().asString();
    }
}
