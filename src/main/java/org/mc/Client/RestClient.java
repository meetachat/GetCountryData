package org.mc.Client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import org.mc.utilities.CheckUserInput;
import org.json.JSONObject;
import org.json.JSONArray;


public class RestClient {
    HttpClient httpClient = HttpClient.newHttpClient();
    CheckUserInput checkUserInput = new CheckUserInput();
    public HttpResponse<String> getCountryData(String searchParameter, String searchParaValue) {
        String baseURL = "https://restcountries.com/v3.1";
        String endPointURL;
        HttpResponse<String> responseString = null;
        // Set the end point based on search parameter
        searchParaValue = checkUserInput.cleanUserInput(searchParaValue);
        if(searchParameter.equals("getByName")){
            if(searchParaValue.contains(" ")){
                searchParaValue=searchParaValue.replace(" ", "%20");
            }
            endPointURL = baseURL+"/name/"+searchParaValue+"/?fullText=true";
        }
        else {
            endPointURL = baseURL+"/alpha?codes="+searchParaValue;
        }

        try {
            responseString = executeGetCall(endPointURL);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return responseString;
    }

    public HttpResponse<String> executeGetCall(String serviceURL) throws IOException, InterruptedException {
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(serviceURL))
                // .header("Content-type","application/json")
                .header("Accept","application/json" )
                .timeout(Duration.ofSeconds(5))
                .GET()
                // .version(HttpClient.Version.HTTP_2)
                .build();
        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        //Check for 500 errors
        isEndPointAvailable(getResponse);
        return getResponse;
    }

    //This function parses the response json to get the value of capital
    //additional methods can be added to get other data like currency, languages, etc.
    public String returnCountryCapital(String responseString) {
        String updatedResponse = responseString.substring(1, responseString.length()-1);
        JSONObject responseJSON = new JSONObject(updatedResponse);
        JSONArray capitalArr = (JSONArray) responseJSON.get("capital");
        return capitalArr.get(0).toString();
    }

    public void isEndPointAvailable(HttpResponse<String> getResponse){
        if(String.valueOf(getResponse.statusCode()).startsWith("5")){
            throw new RuntimeException("The webservice is unavailable "+getResponse.statusCode());
        }
    }





}
