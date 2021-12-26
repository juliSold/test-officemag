package ru.zenicko.officemag.helper;

import com.github.javafaker.Faker;

import java.util.Locale;

public class TestData {
    private String region;
    private String town;
    private String firstName;
    private String lastName;
    private String codePhoneNumber;
    private String phoneNumber;
    private String email;
    private String password;
    private boolean budget;
    private boolean agreeRules;
    private boolean agreeSpam;


    private TestData() {
        region = "Тамбовская область";
        town = "Тамбов";
    }

    private TestData(String region, String town) {
        this.region = region;
        this.town = town;
    }

//    public TestData existUser() {
//        firstName = "John";
//        lastName = "Snow";
//        codePhoneNumber = "999";
//        phoneNumber = "1234567";
//        email = "testuser34543@mail.ru";
//        password = "5d123456";
//        budget = false;
//        agreeRules = true;
//        agreeSpam = false;
//
//        return this;
//    }

    private TestData newUser(String local) {
        Faker faker = new Faker(new Locale(local));
        String tel = faker.phoneNumber().phoneNumber();

        firstName = faker.name().firstName();
        lastName = "TestUser";
        codePhoneNumber = tel.substring(3,6);
        phoneNumber = tel.substring(7).substring(0,3) + tel.substring(7).substring(4, 6) + tel.substring(7).substring(7);
        email = new Faker(new Locale("en-US")).internet().emailAddress();
        password = faker.internet().password(6, 8);
        budget = false;
        agreeRules = true;
        agreeSpam = false;

        return this;
    }

    static public TestData dataFactory(String local) {
        TestData data = new TestData();
        data.newUser(local);

        return data;
    }

    static public TestData dataFactory(String local, String region, String town) {
        TestData data = new TestData(region, town);
        data.newUser(local);

        return data;
    }

    public String getRegion() {
        return region;
    }

    public String getTown() {
        return town;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCodePhoneNumber() {
        return codePhoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean getBudget() {
        return budget;
    }

    public boolean getAgreeRules() {
        return agreeRules;
    }

    public boolean getAgreeSpam() {
        return agreeSpam;
    }

}


