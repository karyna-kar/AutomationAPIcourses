package pages;

import io.restassured.response.Response;
import io.restassured.path.xml.XmlPath;
import utils.Logs;

public class Parser {

    public static int getTotalNumbersOfCountries(Response response) {
        String resp = response.andReturn().asString();
        XmlPath xmlPath = new XmlPath(resp);
        int countries= xmlPath.get("ob:Openbravo.Country.size()");
        Logs.info("TotalNumbersOfCountries from search: " + countries);
        return countries;
    }

    public static int getCountryID(Response response) {
        String resp = response.andReturn().asString();
        XmlPath xmlPath = new XmlPath(resp);
        int countryID = Integer.parseInt(xmlPath.get("ob:Openbravo.Country[0].id").toString());
        Logs.info("CountryID from search: " + countryID);
        return countryID;
    }

    public static int getTotalNumbersOfRegions(Response response)
    {
        String resp = response.andReturn().asString();
        XmlPath xmlPath = new XmlPath(resp);
        int regions = Integer.parseInt(xmlPath.get("ob:Openbravo.Country[0].regionList.size()").toString());
        Logs.info("CountryID from search: " + regions);
        return regions;
    }
}
