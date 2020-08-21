import files.JIRAPayloads;
import io.restassured.RestAssured;
import org.testng.annotations.Test;
import pages.JIRAParseMethods;

import static io.restassured.RestAssured.given;

public class JiraAPITest extends BeforeRequestJira {
    private String IssueID;
    private String CommentD;
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

        CommentD = JIRAParseMethods.getCommentID(response);
    }

    //Delete a comment of the issue
    @Test (enabled = false, dependsOnMethods={"addCommentTest"})
    public void deleteCommentTest() {
        RestAssured.baseURI = "http://localhost:8080";
        String response = given()
                .log().all()
                .header("cookie", cookie)
                .contentType("application/json")
                .when().delete("/rest/api/2/issue/"+IssueID+"/comment/"+CommentD)
                .then()
                .log().all()
                .assertThat().statusCode(204)
                .extract().response().asString();
    }

        //Delete issue
    @Test (enabled = false, dependsOnMethods={"deleteCommentTest"})
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
