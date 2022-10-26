package segundaVez.library;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class DynamicJson {

    @Test(dataProvider = "BooksData")
    public void addBook(String um,String dois, String tres, String quatro) {
        baseURI = "http://216.10.245.166";
        String payload =
                given()
                .header("Content-Type","application/json")
                .body(Payload.addBook(um,dois,tres,quatro))
                .when()
                .post("/Library/Addbook.php")
                .then()
                .assertThat().statusCode(200)
                .extract().response().asString();

       JsonPath jsonPath =  ReusableMethod.rawToJson(payload);

      String id =  jsonPath.get("ID");
      System.out.println(id);

    }
    //array - collection of elements
    //multidimensional array = collection of arrays

    @DataProvider(name = "BooksData")
    public Object[][] getData() {
       return new Object[][]
               {
                {"teste","2222","211314","00000"},
                {"teste1","1111","9999","767565"},
        };
    }
}
