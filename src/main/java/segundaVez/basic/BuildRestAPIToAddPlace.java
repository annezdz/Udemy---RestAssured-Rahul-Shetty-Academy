package segundaVez.basic;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class BuildRestAPIToAddPlace {

    public static void main(String[] args) {

        /**
         * given - all input details
         * when - submit the API - resource HTTP method
         * then - validate the response
         *
         * */
        //passar baseURI  e em given() passar os Params, header e body

        baseURI = "https://rahulshettyacademy.com";
        given()
                //esse log é válido para o Input
                .log()
                .all()
                .queryParam("key", "qaclick123")
                .header("Content-Type","application/json")
                .body("{\n" +
                        "  \"location\": {\n" +
                        "    \"lat\": -38.383494,\n" +
                        "    \"lng\": 33.427362\n" +
                        "  },\n" +
                        "  \"accuracy\": 50,\n" +
                        "  \"name\": \"Frontline house\",\n" +
                        "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                        "  \"address\": \"29, side layout, cohen 09\",\n" +
                        "  \"types\": [\n" +
                        "    \"shoe park\",\n" +
                        "    \"shop\"\n" +
                        "  ],\n" +
                        "  \"website\": \"http://google.com\",\n" +
                        "  \"language\": \"French-IN\"\n" +
                        "}")
                //chama o post, passando o resource
                .when().post("maps/api/place/add/json")
                //vamos validar o Response
                .then()
                //log para o response
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                //valida se o Response contém o campo scope e valida se é APP
                .body("scope",equalTo("APP"))
                .header("server","Apache/2.4.41 (Ubuntu)");
                //validar o servidor é importante para validar se a Response vem do servidor correto
                        //Server - header




    }
}
