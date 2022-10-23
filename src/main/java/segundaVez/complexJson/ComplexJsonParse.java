package segundaVez.complexJson;

import io.restassured.path.json.JsonPath;

import java.util.ArrayList;
import java.util.List;

public class ComplexJsonParse {
    public static void main(String[] args) {

        JsonPath jasonPath = new JsonPath(Payload.coursePrice());

        //Print Number of courses returned by API with json size() method
        int count  = jasonPath.getInt("courses.size()");
        System.out.println(count);

        //print purchase amount

        double amount = jasonPath.getInt("dashboard.purchaseAmount");
        System.out.println(amount);

        //print title of first course

        String firstCourse = jasonPath.get("courses[0].title");
        System.out.println(firstCourse);

        //print all courses and respective values
        List<String> coursesTitle = new ArrayList<>();
        List<String> coursesPrices = new ArrayList<>();
        for(int i = 0; i < count; i++) {
            coursesPrices.add(jasonPath.get("courses[" + i + "].price").toString());
            coursesPrices.add(jasonPath.get("courses[" + i + "].title"));

        }
        for(String course : coursesTitle) {
            System.out.println(course);
        }
        for(String course : coursesPrices) {
            System.out.println(course);
        }
    }
}
