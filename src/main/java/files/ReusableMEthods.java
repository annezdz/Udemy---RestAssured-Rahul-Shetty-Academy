package files;

import io.restassured.path.json.JsonPath;

public class ReusableMEthods {

    public static JsonPath rawToJson(String response) {
        JsonPath jsonPath = new JsonPath(response);
        return jsonPath;
    }

}
