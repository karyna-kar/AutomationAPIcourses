package files;

public class LibraryAPIPayloads {

    public static String AddBookPayload(String isbn, String aisle){
        return "{\n" +
                "\n" +
                "\"name\":\"Learn Appium Automation with Java\",\n" +
                "\"isbn\":\""+isbn+"\",\n" +
                "\"aisle\":\""+aisle+"\",\n" +
                "\"author\":\"John foe\"\n" +
                "}";
    }

    public static String DeleteBookPayload(String id){
        return "{\n" +
                " \"ID\" : \""+id+"\"\n" +
                "}";
    }

}
