package secao4;


import files.Payload;
import files.ReusableMEthods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class First {
    /***
     * Validate if add place API isworking as expected
     * given - all inputs details
     * when - submit the API - resource, http, method
     * Then - validate the response
     * content of the file to String -> content of file can convert into Byte -> Byte data to String
     * */

    public static void main(String[] args) throws IOException {
        baseURI = "https://rahulshettyacademy.com";
       String response =  given()
                .log()
                .all()
                .queryParam("key","click123")
                .header("Content-Type","application/json")
              //  .body(Payload.addPlace())
               //Instead call the method Payload.addPlace, we can use files externally
               .body(new String(Files.readAllBytes(Paths.get("C:\\Users\\anicolle\\Downloads\\addPlace.json"))))
                .when()
                .post("/maps/api/place/add/json")
                .then()
                .assertThat()
                .statusCode(200)
                .body("scope",equalTo("APP"))
                .header("server","Apache/2.4.41 (Ubuntu)")// validate Server Resonse is very important (hackers)
                .extract().response().asString();
        System.out.println(response);
        JsonPath jasonPath = new JsonPath(response);  //for parsing JSON
        String placeId =  jasonPath.getString("place_id");
        System.out.println(placeId);

        ;

        //Add place -> Update place with new Address -> Get place to validate if new address is present in response


        //Update Place

        final String street = "Rua das Palmeiras";

        given()
                .log()
                .all()
                .queryParam("key","qaclick123")
                .header("Content-Type","application/json")
                .body(Payload.updatePlace(placeId, street))
                .when()
                .put("maps/api/place/get/json")
                .then().assertThat().statusCode(200)
//                .body("msg", equalTo(" "))
        ;

        //Get place
        //Não precisa do header no GET
     String getPlaceResonse = given()
                .log()
                .all()
                .queryParam("key","qaclick123")
                .queryParam("place_id",placeId)
                .when()
                .get("maps/api/place/get/json")
                .then()
                .assertThat()
                .log()
                .all()
                .statusCode(200)
             .extract().response().asString();
       JsonPath jsonPath =  ReusableMEthods.rawToJson(getPlaceResonse);
       String newAddress =  jsonPath.getString("address");
       System.out.println(newAddress);
        Assert.assertEquals(street,newAddress);
    }
}
