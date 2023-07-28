package org.mc.utilities;

public class CheckUserInput {
    public String validateSearchParaValue(String searchParaValue) {
        //Validate that searchParaValue contains alphabets only
        if(!(searchParaValue.matches("[a-zA-Z]*"))){
            return "invalidEntry";
        }
        // return right value to main as per search parameter
        if(searchParaValue.equalsIgnoreCase("code")) {
            return "getByCode";
        } else if(searchParaValue.equalsIgnoreCase("name")) {
            return "getByName";
        } else {
            return "invalidEntry";
        }
    }

    public boolean isCountryCodeValid(String countryCodeValue) {
        //validate that thCountry code value is either 2 or 3 characters
        if (countryCodeValue.length() < 2 | countryCodeValue.length() > 3) {
            return false;
        }
        // validate that it only has alphabets and/or numbers
        if(!countryCodeValue.matches("[a-zA-Z0-9]*")) {
            return false;
        }
        return true;
    }

    public boolean isCountryNameValid(String countryNameValue){
        //validate that min length is 4 (no country has less than 4 characters in name)
        if(countryNameValue.length()<4){
            return false;
        }
        // validate that it only has alphabets
        if(!countryNameValue.matches("[a-zA-Z]*")) {
            return false;
        }
        return true;
    }

}
