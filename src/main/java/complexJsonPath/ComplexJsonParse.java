package complexJsonPath;

import files.Payload;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

public class ComplexJsonParse {
    public static void main(String[] args) {

        //While de API is not not yet ready we're using Mock JSON
        JsonPath jsonPath = new JsonPath(Payload.coursePrice());

        //Print numbers of courses returned by API

       int countCourses =  jsonPath.getInt("courses.size()");
        System.out.println(countCourses);

        //Print purchase amount

        int amount = jsonPath.getInt("dashboard.purchaseAmount");
        System.out.println(amount);

        //Print title first course

        String firstCourse = jsonPath.getString("courses.title.get(0)");
        //OU

        String firstCourse1 = jsonPath.get("courses[0].title");

        System.out.println(firstCourse);
        System.out.println(firstCourse1);

        //Print add course titles and their respective prices

        for(int i = 0; i < countCourses; i++) {
            System.out.println(jsonPath.getString("courses.title.get(" + i + ")"));
            System.out.println(jsonPath.getInt("courses.price.get(" + i + ")".toString()));
        }

        System.out.println("Print no of copies sold by RPA course");
        int copies;
        int price;
        for(int i = 0; i < countCourses; i++) {
            String courseTitles = jsonPath.getString("courses.title.get(" + i + ")");
            if(courseTitles.equalsIgnoreCase("RPA")) {
                copies = jsonPath.getInt("courses.copies.get(" + i + ")");
                System.out.println(copies);

                break;
            }
        }

        System.out.println("Verify if sum of all course prices matches with purchase" );
        int total = 0;
        for(int i =0; i < countCourses; i++) {
            copies = jsonPath.getInt("courses.copies.get(" + i + ")");
            price = jsonPath.getInt("courses.price.get(" + i + ")");

            total += copies * price;

        }
        Assert.assertEquals(amount, total, "Valores iguais");


    }

}
