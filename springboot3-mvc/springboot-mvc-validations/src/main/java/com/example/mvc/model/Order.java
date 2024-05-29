//tag::all[]
//tag::allButValidation[]
package com.example.mvc.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class Order {

    String id;
    //end::allButValidation[]
    @NotBlank(message = "Name is required")
    //tag::allButValidation[]
    private String name;
    @NotNull(message = "Choose type of deliverey")
    //tag::allButValidation[]
    private Type type = null;
    //end::allButValidation[]
    @NotBlank(message = "Street is required")
    //tag::allButValidation[]
    private String street;
    @NotBlank(message = "City is required")
    //tag::allButValidation[]
    private String city;
    //end::allButValidation[]
    @NotBlank(message = "State is required")
    //tag::allButValidation[]
    private String state;
    //end::allButValidation[]
    @NotBlank(message = "Zip code is required")
    //tag::allButValidation[]
    private String zip;
    //end::allButValidation[]
    //  @CreditCardNumber(message = "Not a valid credit card number")
    //tag::allButValidation[]
    private String ccNumber;
    //end::allButValidation[]
    //  @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
    //           message = "Must be formatted MM/YY")
    //tag::allButValidation[]
    private String ccExpiration;
    //end::allButValidation[]
    //  @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    //tag::allButValidation[]
    private int ccCVV;
    //end::allButValidation[]

    public String getId() {
        return id;
    }

    // -----------------------

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public String getCcExpiration() {
        return ccExpiration;
    }

    public void setCcExpiration(String ccExpiration) {
        this.ccExpiration = ccExpiration;
    }

    public int getCcCVV() {
        return ccCVV;
    }

    public void setCcCVV(int ccCVV) {
        this.ccCVV = ccCVV;
    }

    public @NotNull(message = "Choose type of deliverey") Type getType() {
        return type;
    }

    public void setType(@NotNull(message = "Choose type of deliverey") Type type) {
        this.type = type;
    }

    public static enum Type {
        FAST, SLOW, VERYSLOW, SUPERFAST, JUSTNOW
    }
}
//end::allButValidation[]
//end::all[]
