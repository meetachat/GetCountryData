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

    @Test
    public void getCapitalByNameStatusCode(){
        System.out.println("Validating status code for Happy Path - search by name");
        httpResponseString = restClient.getCountryData("getByName", "France");
        Assert.assertEquals(httpResponseString.statusCode(),200,"Status Code is not the expected value");
    }

    @Test(dependsOnMethods = "getCapitalByNameStatusCode")
    public void getCapitalByNameHappyPath(){
        System.out.println("Validating capital for Happy Path - search by Name");
        //HttpResponse<String>httpResponseString = restClient.getCountryData("getByName", "France");
        String actualCapital = restClient.returnCountryCapital(httpResponseString.body());
        Assert.assertEquals(actualCapital.toLowerCase(),"paris","Status Code is not the expected value");
    }

    @Test
    public void getCapitalByNameInvalidNameStatusCode(){
        System.out.println("Validating capital for Invalid Country Name - search by Name");
        HttpResponse<String>httpResponseString = restClient.getCountryData("getByName", "ABCD");
        Assert.assertEquals(httpResponseString.statusCode(),404,"Status Code is not the expected value");
    }

    @Test
    public void getCapitalByCodeStatusCode(){
        System.out.println("Validating status code for Happy Path - search by name");
        HttpResponse<String> httpResponseString = restClient.getCountryData("getByCode", "FR");
        Assert.assertEquals(httpResponseString.statusCode(),200,"Status Code is not the expected value");
    }
    @Test(dependsOnMethods = "getCapitalByCodeStatusCode")
    public void getCapitalByCodeHappyPath(){
        System.out.println("Validating capital for Happy Path - search by Code");
        String actualCapitalValue = restClient.returnCountryCapital(httpResponseString.body());
        Assert.assertEquals(actualCapitalValue.toLowerCase(),"paris", "The value of capital does not match expected value when searching by Code");
    }
}
