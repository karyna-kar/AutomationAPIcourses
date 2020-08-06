import files.LibraryAPIPayloads;
import io.restassured.RestAssured;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LibraryAPIParseMethods;
import pages.ParseMethods;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class LibraryAPITest {
    String bookID;

    //Add new Book Parameterization
    @Test(dataProvider = "BooksData")
    public void AddBookParamsTest(String isbn, String aisle)
    {
        RestAssured.baseURI = "http://216.10.245.166";
        String testResponse = given()
                .contentType("application/json")
                .body(LibraryAPIPayloads.AddBookPayload(isbn, aisle))
                .when().post("/Library/Addbook.php")
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().response().asString();

        bookID = LibraryAPIParseMethods.getBookID(testResponse);
        System.out.print(bookID);
    }

    @DataProvider(name="BooksData")
    public Object[][] getData()
    {
        return new Object[][] {{"test1", "101"}, {"test2", "102"}, {"test3", "103"}};
    }

    //Add new Book
    @Test
    public void AddBookTest()
    {
        RestAssured.baseURI = "http://216.10.245.166";
        String testResponse = given()
                .contentType("application/json")
                .body(LibraryAPIPayloads.AddBookPayload("aaa", "11175"))
                .when().post("/Library/Addbook.php")
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().response().asString();

        bookID = LibraryAPIParseMethods.getBookID(testResponse);
        System.out.print(bookID);
    }

    //Get a Book
    @Test (dependsOnMethods={"AddBookTest"})
    public void GetBookTest(){
        RestAssured.baseURI = "http://216.10.245.166";
        String testResponse = given()
                .log().all()
                .queryParam("ID", bookID)
                .when().get("/Library/GetBook.php")
                .then()
                .log().all()
                .assertThat().statusCode(200)
               // .assertThat().body("address", equalTo(""))
                .extract().response().asString();

    }

    //Delete a Book
    @Test (dependsOnMethods={"GetBookTest"})
    public void DeleteBookTest(){
        RestAssured.baseURI = "http://216.10.245.166";
        String test_response = given()
                .contentType("application/json")
                .body(LibraryAPIPayloads. DeleteBookPayload(bookID))
                .when().post("/Library/DeleteBook.php")
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body("msg", equalTo("book is successfully deleted"))
                .extract().response().asString();
    }
}

