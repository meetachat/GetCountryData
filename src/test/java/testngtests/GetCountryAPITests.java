package testngtests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.mc.Client.RestClient;

import java.net.http.HttpResponse;

public class GetCountryAPITests {
    RestClient restClient = new RestClient();
    HttpResponse<String> httpResponseString = null;


    @BeforeMethod
    public void setUp(){
        System.out.println("TestNG Tests");
    }

    @Test(groups = {"Get By Country Name"})
    public void getCapitalByNameStatusCode(){
        System.out.println("Validating status code for Happy Path - search by name");
        httpResponseString = restClient.getCountryData("getByName", "France");
        Assert.assertEquals(httpResponseString.statusCode(),200,"Status Code is not the expected value");
    }

    @Test(dependsOnMethods = "getCapitalByNameStatusCode",groups = {"Get By Country Name"})
    public void getCapitalByNameHappyPath(){
        System.out.println("Validating capital for Happy Path - search by Name");
        //HttpResponse<String>httpResponseString = restClient.getCountryData("getByName", "France");
        String actualCapital = restClient.returnCountryCapital(httpResponseString.body());
        Assert.assertEquals(actualCapital.toLowerCase(),"paris","Status Code is not the expected value");
    }

    @Test(groups = {"Get By Country Name"})
    public void getCapitalByNameInvalidNameStatusCode(){
        System.out.println("Validating capital for Invalid Country Name - search by Name");
        HttpResponse<String>httpResponseString = restClient.getCountryData("getByName", "ABCD");
        Assert.assertEquals(httpResponseString.statusCode(),404,"Status Code is not the expected value");
    }

    @Test(groups = {"Get By Country Code"})
    public void getCapitalByCodeStatusCode(){
        System.out.println("Validating status code for Happy Path - search by name");
        httpResponseString = restClient.getCountryData("getByCode", "FR");
        Assert.assertEquals(httpResponseString.statusCode(),200,"Status Code is not the expected value");
    }
    @Test(dependsOnMethods = "getCapitalByCodeStatusCode",groups = {"Get By Country Code"})
    public void getCapitalByCodeHappyPath(){
        System.out.println("Validating capital for Happy Path - search by Code");
        String actualCapitalValue = restClient.returnCountryCapital(httpResponseString.body());
        Assert.assertEquals(actualCapitalValue.toLowerCase(),"paris", "The value of capital does not match expected value when searching by Code");
    }

    @Test(groups = {"Get By Country Name"})
    public void getCapitalByNameWithLeadingSpaces(){
        System.out.println("Validating capital for leading spaces in name");
        HttpResponse<String>httpResponseString = restClient.getCountryData("getByName", "    Germany");
        String actualCapitalValue = restClient.returnCountryCapital(httpResponseString.body());
        Assert.assertEquals(actualCapitalValue.toLowerCase(),"berlin", "The value of capital does not match expected value when searching by Code");
    }

    @Test(groups = {"Get By Country Name"})
    public void getCapitalByNameWithTrailingSpaces(){
        System.out.println("Validating capital for leading spaces in name");
        HttpResponse<String>httpResponseString = restClient.getCountryData("getByName", "Germany    ");
        String actualCapitalValue = restClient.returnCountryCapital(httpResponseString.body());
        Assert.assertEquals(actualCapitalValue.toLowerCase(),"berlin", "The value of capital does not match expected value when searching by Code");
    }

    @Test(groups = {"Get By Country Name"})
    public void getCapitalByNameWithSpacesInName(){
        System.out.println("Validating capital for leading spaces in name");
        HttpResponse<String>httpResponseString = restClient.getCountryData("getByName", "United Arab Emirates");
        String actualCapitalValue = restClient.returnCountryCapital(httpResponseString.body());
        Assert.assertEquals(actualCapitalValue.toLowerCase(),"abu dhabi", "The value of capital does not match expected value when searching by Code");
    }

    @Test(groups = {"Get By Country Code"})
    public void getCapitalByCodeWithLeadingSpaces(){
        System.out.println("Validating capital for leading spaces in name");
        HttpResponse<String>httpResponseString = restClient.getCountryData("getByCode", "    UG");
        String actualCapitalValue = restClient.returnCountryCapital(httpResponseString.body());
        Assert.assertEquals(actualCapitalValue.toLowerCase(),"kampala", "The value of capital does not match expected value when searching by Code");
    }

    @Test(groups = {"Get By Country Code"})
    public void getCapitalByCodeWithTrailingSpaces(){
        System.out.println("Validating capital for leading spaces in name");
        HttpResponse<String>httpResponseString = restClient.getCountryData("getByCode", "800    ");
        String actualCapitalValue = restClient.returnCountryCapital(httpResponseString.body());
        Assert.assertEquals(actualCapitalValue.toLowerCase(),"kampala", "The value of capital does not match expected value when searching by Code");
    }
}
