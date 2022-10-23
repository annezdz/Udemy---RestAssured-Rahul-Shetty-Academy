package DinamicPayloads;

import com.google.gson.Gson;
import files.Payload;
import files.ReusableMEthods;
import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class DinamicExample {

    @Test(dataProvider = "BooksData")
    public void addBook(String isbn, String aisle) {
      baseURI = "https://rahulshettyacademy.com/";
        String response = given()
                .header("Content-Type", "application/json")
                .body(Payload.addBook("Java 8",isbn,aisle, "Eduardo Marzano"))
                .when()
                .post("Library/Addbook.php")
                .then()
                .assertThat().statusCode(200)
                .extract().response().asString();

       JsonPath jsonPath =  ReusableMEthods.rawToJson(response);
       String id = jsonPath.get("ID");
        System.out.println(id);

    }

    @DataProvider(name = "BooksData")
    public Object[][] getData() {
       return new Object[][] {
               {"ooooo","9363"},
               {"aaaaaa","9292"},
               {"iiiiii","9191"}

        };
    }
}
