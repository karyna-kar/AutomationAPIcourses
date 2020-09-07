package pages;

import io.restassured.response.Response;
import io.restassured.path.xml.XmlPath;

public class Parser {
    public static int getCountryID(Response response) {
        String resp = response.andReturn().asString();
        XmlPath xmlPath = new XmlPath(resp);
        return Integer.parseInt(xmlPath.get("ob:Openbravo.Country.id").toString());
    }
}
