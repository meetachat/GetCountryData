package org.mc;
import org.mc.Client.RestClient;
import org.mc.utilities.CheckUserInput;

import java.util.Scanner;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) {

        CheckUserInput checkUserInput = new CheckUserInput();

        //the condition to execute while loop
        boolean continueSearch = true;
        Scanner userInput = new Scanner(System.in);

        while (continueSearch) {
            String searchParameter;
            String searchParaValue = "";
            //Get user input on whether to search by Country or Code
            System.out.println("Would you like to search by country name or code?(Please enter 'Name' or 'Code')");
            searchParameter = userInput.next();
            //Checking the user input for invalid values
            searchParameter = checkUserInput.validateSearchParaValue(searchParameter);
            //if value is invalid - numeric, Not name or code, etc. print message and end iteration
            if(searchParameter.equals("invalidEntry")) {
                System.out.println("Please enter a valid value (Name/Code).Exiting application");
            }
            // if user input has valid values, get the search value for Country Code
            else {
                if (searchParameter.equals("getByCode")) {
                    //user input for search value
                    System.out.println("Please enter the Country code: ");
                    searchParaValue = userInput.next().strip();
                    // check search value for invalid inputs
                    if (!checkUserInput.isCountryCodeValid(searchParaValue)) {
                        System.out.println("Invalid value entered for country code.Exiting application");
                    }
                    else{
                        // if search value is valid, return capital
                        printCountryInfo(searchParameter,searchParaValue);
                    }

                }
                // if user input has valid values, get the search value for Country Name
                else if (searchParameter.equals("getByName")) {
                    System.out.println("Please enter the Country name: ");
                    userInput.useDelimiter("\\n");
                    searchParaValue = userInput.next().strip();
                    // check user input for invalid values
                    if (!checkUserInput.isCountryNameValid(searchParaValue)) {
                        System.out.println("Invalid value entered for country name.Exiting application");
                    }
                    else{
                        printCountryInfo(searchParameter,searchParaValue);
                    }
                }
            }
            //get user input if user wants to continue.
            System.out.println("Do you want to continue?(enter y/n)");
            String enterAnotherCountry = userInput.next();
            //Set the control condition for while loop
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




