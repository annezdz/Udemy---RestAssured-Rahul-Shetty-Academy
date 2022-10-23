package segundaVez.validatingRestAPIResponse;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.openqa.selenium.json.Json;
import org.testng.Assert;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
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
       String response =  given()
                //esse log é válido para o Input
                .log()
                .all()
                .queryParam("key", "qaclick123")
                .header("Content-Type","application/json")
                .body(Payload.addPlace())
                //chama o post, passando o resource
                .when().post("maps/api/place/add/json")
                //vamos validar o Response
                .then()
                //log para o response
//                .log()
//                .all()
                .assertThat()
                .statusCode(200)
                //valida se o Response contém o campo scope e valida se é APP
                .body("scope",equalTo("APP"))
                //validar o servidor é importante para validar se a Response vem do servidor correto
                //Server - header
                .header("server","Apache/2.4.41 (Ubuntu)")
                 //Extrair o Response como String
                .extract().response().asString();

                //Pega a String e transforma em JSON
                 JsonPath jsonPath = new JsonPath(response);
                 String id = jsonPath.getString("place_id");
                 System.out.println(id);


                 //Update Place - verifica código 200 e mensagem de retorno quando é atualizado com sucesso

        String newAddress = "Bernardo Reiter, Brazil";

        given()
                .log().all()
                .queryParam("key","qaclick123")
                .header("Content-Type","application/json")
                .body("{\n" +
                        "\"place_id\":\"" +  id + "\",\n" +
                        "\"address\":\"" + newAddress +"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}\n")
                .when()
                .put("/maps/api/place/update/json")
                .then()
                .assertThat().statusCode(200)
                .body("msg",equalTo("Address successfully updated"));

        //Get place
        //Aqui como não enviamos nenhum body (por se tratar de um get), não precisamos do header, precisamos apenas do placeID


        //Salvar a resposta como String
        String getPlaceResponse =  given()
                .log().all()
                .queryParam("key","qaclick123")
                .queryParam("place_id",id)
                .when()
                .get("maps/api/place/get/json")
                .then()
                .assertThat().log().all()
                .statusCode(200)
                .extract().response().asString();

        //Pega a String e transforma em JSON

        JsonPath jasonPath = ReusableMethod.rawToJson(getPlaceResponse);

        String actualAddress = jasonPath.getString("address");
        System.out.println(actualAddress);
  //      Assert.assertEquals(actualAddress,"Pacific");

    }
}
