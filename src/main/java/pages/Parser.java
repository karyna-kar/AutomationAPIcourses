package pages;

import io.restassured.response.Response;
import io.restassured.path.xml.XmlPath;

public class Parser {

    public static int getTotalNumbersOfCountries(Response response) {
        String resp = response.andReturn().asString();
        XmlPath xmlPath = new XmlPath(resp);

        int a= xmlPath.get("ob:Openbravo.Country.size()");
        return a;
    }

    public static int getCountryID(Response response) {
        String resp = response.andReturn().asString();
        XmlPath xmlPath = new XmlPath(resp);
        return Integer.parseInt(xmlPath.get("ob:Openbravo.Country[0].id").toString());
    }

    public static int getTotalNumbersOfRegions(Response response)
    {
        String resp = response.andReturn().asString();
        XmlPath xmlPath = new XmlPath(resp);
        return Integer.parseInt(xmlPath.get("ob:Openbravo.Country[0].regionList.size()").toString());
    }
}
