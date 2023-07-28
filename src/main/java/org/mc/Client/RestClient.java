package org.mc.Client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;
import org.json.JSONArray;


public class RestClient {
    HttpClient httpClient = HttpClient.newHttpClient();

    public HttpResponse<String> getCountryData(String searchParameter, String searchParaValue) {
        String baseURL = "https://restcountries.com/v3.1";
        String endPointURL;
        HttpResponse<String> responseString = null;
        // Set the end point based on search parameter
        if(searchParameter.equals("getByName")){
            endPointURL = baseURL+"/name/"+searchParaValue+"/?fullText=true";
        }
        else {
            endPointURL = baseURL+"/alpha?codes="+searchParaValue;
        }

        try {
            responseString = executeGetCall(endPointURL);
            int respStatusCode = getStatusCode(responseString);

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
                .GET()
                // .version(HttpClient.Version.HTTP_2)
                .build();
        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        return getResponse;
    }

    public String returnCountryCapital(String responseString) {
        String updatedResponse = responseString.substring(1, responseString.length()-1);
        JSONObject responseJSON = new JSONObject(updatedResponse);
        JSONArray capitalArr = (JSONArray) responseJSON.get("capital");
        return capitalArr.get(0).toString();
    }

    public int getStatusCode(HttpResponse<String> responseString){
        return responseString.statusCode();
    }



}
