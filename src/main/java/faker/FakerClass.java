package faker;

import com.github.javafaker.Faker;
import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import static io.restassured.RestAssured.*;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class FakerClass {

    static Faker faker = new Faker();


    public String createFakeName() {
        Faker faker = new Faker();
        return faker.name().fullName();
    }

    public static String createName(char sex) {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        if(sex == 'f' || sex == 'F') {
            while (!firstName.endsWith("a")) {
                firstName = faker.name().firstName();
            }
        } else if(sex == 'm' || sex == 'M'){
            while(!firstName.endsWith("o")) {
                firstName = faker.name().firstName();
            }
        }
        return firstName + " " + lastName;
    }


    public static void createAddressFaker() {
        Faker brFaker = new Faker(new Locale("pt", "BR"));
        String cep ;
        String response1 = null;
        do {
            cep = String.valueOf(brFaker.number().numberBetween(30000000L,39999999L));
//            cep = brFaker.address().zipCode().replace("-", "");
            baseURI = "https://viacep.com.br/ws/" + cep + "/json";
             response1 = given()
                    .header("Content-Type", "application/json")
                    .when()
                    .queryParams("erro", false)
                    .get()
                    .then()
                    .extract().response().asString();
            JsonPath jsonPath = new JsonPath(response1);
        } while (!response1.contains("cep"));

    }

    private static String generateDigits(String digitsBase) {
        StringBuilder sbCpfNumber = new StringBuilder(digitsBase);
        int total = 0;
        int multiple = digitsBase.length() + 1;
        for (char digit : digitsBase.toCharArray()) {
            long parcial = (long) Integer.parseInt(String.valueOf(digit)) * (multiple--);
            total += parcial;
        }
        int resto = Integer.parseInt(String.valueOf(Math.abs(total % 11)));
        if (resto < 2) {
            resto = 0;
        } else {
            resto = 11 - resto;
        }
        sbCpfNumber.append(resto);
        if (sbCpfNumber.length() < 11) {
            return generateDigits(sbCpfNumber.toString());
        }
        return sbCpfNumber.toString();

    }

    public static String generateValidCPF(){
        Random r = new Random();
        StringBuilder sbCpfNumber = new StringBuilder();
        for(int i = 0; i < 9; i++){
            sbCpfNumber.append(r.nextInt(9));
        }
        return generateDigits(sbCpfNumber.toString());
    }


    public static String generatePhoneNumber() {
        return faker.regexify("\\([0-9]{2}\\) [0-9]?[0-9]{4}-[0-9]{4}");
    }

    public static void main(String[] args) throws ParseException {

        Faker faker = new Faker();
//        System.out.println(createName('F'));
        System.out.println(generateValidCPF());
//        String streetName = faker.address().streetName();
//        String firstName = faker.name().firstName();
//        String lastName = faker.name().lastName();
//        String fullName = faker.name().fullName();
//        String number = faker.address().buildingNumber();
//        String city = faker.address().city();
//        String country = faker.address().country();
//
        Date birth = faker.date().birthday(18,100);
        SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println(dtf.format(birth));;
        System.out.println(generatePhoneNumber());
        generatePhoneNumber();
//
//
//        System.out.println(birth.toString());
//        System.out.println(formatBirth.toString());
//        System.out.println(fullName);

        createAddressFaker();
    }
}
