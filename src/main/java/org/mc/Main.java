package org.mc;
import org.mc.Client.RestClient;
import org.mc.utilities.CheckUserInput;

import java.util.Scanner;
import java.net.http.HttpResponse;

public class Main {

    public static void main(String[] args) {
        boolean continueSearch = true;
        RestClient myClient = new RestClient();
        CheckUserInput checkUserInput = new CheckUserInput();
        Scanner userInput = new Scanner(System.in);
        while (continueSearch) {
            String searchParameter, searchParaValue;
            System.out.println("Would you like to search by country name or code?(Please enter 'Name' or 'Code')");
            searchParameter = userInput.next();
            searchParameter = checkUserInput.validateSearchParaValue(searchParameter);
            
            if(searchParameter.equals("invalidEntry")) {
                System.out.println("Please enter a valid value (Name/Code).Exiting application");
                continueSearch = false;
                //break;
            }
            else {
                if (searchParameter.equals("getByCode")) {
                    System.out.println("Please enter the Country code: ");
                    searchParaValue = userInput.next();
                    if (!checkUserInput.isCountryCodeValid(searchParaValue)) {
                        System.out.println("Invalid value entered for country code.Exiting application");
                        continueSearch = false;
                    }
                    else{
                        printCountryInfo(searchParameter,searchParaValue);
                    }

                }
                else if (searchParameter.equals("getByName")) {
                    System.out.println("Please enter the Country name: ");
                    searchParaValue = userInput.next();
                    if (!checkUserInput.isCountryNameValid(searchParaValue)) {
                        System.out.println("Invalid value entered for country name.Exiting application");
                        continueSearch = false;
                    }
                    else{
                        printCountryInfo(searchParameter,searchParaValue);
                    }
                }

            }
            System.out.println("Do you want to continue?(enter y/n)");
            String enterAnotherCountry = userInput.next();
            continueSearch = enterAnotherCountry.equalsIgnoreCase("y");
        }
        userInput.close();
    }

   public static void printCountryInfo(String searchParameter, String searchParaValue){
        RestClient restClient = new RestClient();
        HttpResponse<String> responseString = restClient.getCountryData(searchParameter,searchParaValue);
        if(responseString.statusCode() == 200) {
            String countryCapital = restClient.returnCountryCapital(responseString.body());
            System.out.println("The capital of " + searchParaValue + " is " + countryCapital);
        } else{
            System.out.println("Unable to retrieve information about " + searchParaValue);
        }

   }
}




