package segundaVez.complexJson;

import files.Payload;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SumValidation {

    JsonPath jsonPath = new JsonPath(Payload.coursePrice());

    int countCourses =  jsonPath.getInt("courses.size()");
    int amount = jsonPath.getInt("dashboard.purchaseAmount");


    @Test
    public void validateValues() {
        int total = 0;
        int copies;
        int price;
        for(int i =0; i < countCourses; i++) {
            copies = jsonPath.getInt("courses.copies.get(" + i + ")");
            price = jsonPath.getInt("courses.price.get(" + i + ")");

            total += copies * price;

        }
        Assert.assertEquals(amount, total, "Valores iguais");
    }
}
