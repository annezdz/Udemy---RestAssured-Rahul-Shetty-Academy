package segundaVez.basic;

import io.restassured.RestAssured;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class BuildRestAPIToAddPlace {

    public static void main(String[] args) throws IOException {

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
                .body(new String(Files.readAllBytes(Paths.get("C:\\Users\\anicolle\\OneDrive - everis\\Documentos\\Automacao\\json.txt"))))
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
