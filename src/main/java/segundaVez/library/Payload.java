package segundaVez.library;

public class Payload {
    public static String addBook(String name, String isbn, String aisle, String author) {
        return "{\n" +
                "\n" +
                "\"name\":\""+ name +"\",\n" +
                "\"isbn\":\""+ isbn +"\",\n" +
                "\"aisle\":\"" + aisle +"\",\n" +
                "\"author\":\""+ author +"\"\n" +
                "}\n";
    }
}
