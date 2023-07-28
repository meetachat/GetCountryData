package org.mc.utilities;

public class CheckUserInput {
    public String validateSearchParaValue(String searchParaValue) {
        if(!(searchParaValue.matches("[a-zA-Z]*"))){
            return "invalidEntry";
        }
        if(searchParaValue.equalsIgnoreCase("code")) {
            return "getByCode";
        } else if(searchParaValue.equalsIgnoreCase("name")) {
            return "getByName";
        } else {
            return "invalidEntry";
        }
    }

    public boolean isCountryCodeValid(String countryCodeValue) {
        if (countryCodeValue.length() < 2 | countryCodeValue.length() > 3) {
            return false;
        }
        if (!countryCodeValue.matches("[a-zA-Z0-9]*")) {
            return false;
        }
        return true;
    }

    public boolean isCountryNameValid(String countryNameValue){
        if(countryNameValue.length()<4){
            return false;
        }
        if(!countryNameValue.matches("[a-zA-Z]*")) {
            return false;
        }
        return true;
    }

}
