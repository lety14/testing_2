package com.backend;

import io.restassured.RestAssured;


import io.restassured.specification.RequestSpecification;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Map;


public class APIRest {
    private static RequestSpecification request;
    String username = "EricaGreen2020";
    String password = "Hola*123123123";

    public APIRest() {
    }

    @BeforeAll
    public static void beforeAll() {
        RestAssured.baseURI = "https://parabank.parasoft.com/parabank";
        request = RestAssured.given();
    }

    @Test
    @DisplayName("Open Registration Page")
    public void RegistrationGET() {
        request
                .when()
                .get("/register.htm")
                .then().statusCode(200)
                .log().all();
    }

    @Test
    @DisplayName("Open New Account")
    public void OpenNewAccountPOST() {
        Integer customerId = 12212;
        Integer newAccountType = 1;
        Integer fromAccountId = 23445;

        request
                .auth().basic(username, password)
                .queryParams(Map.of("customerId", customerId,"newAccountType", newAccountType,"fromAccountId", fromAccountId))
                .when()
                .post("services_proxy/bank/createAccount")
                .then().statusCode(200)
                .assertThat().body("customerId", Is.is(12212))
                .log().all();
    }


    @Test
    @DisplayName("Login Success POST")
    public void LoginSuccessPOST() {
        request
                .contentType("application/x-www-form-urlencoded; charset")
                .queryParams("grant_type", "password")
                .queryParams("username", username)
                .queryParams("password", password)
                .when()
                .post("/login.htm")
                .then()
                .assertThat().header("location", Is.is("https://parabank.parasoft.com/parabank/overview.htm"))
                .log().all();
    }

    @Test
    @DisplayName("Founds Transfer POST")
    public void FoundsTransferPOST() {
        Integer fromAccountId = 13212;
        Integer toAccountId = 14142;
        Integer amount = 2343;

        request
                .auth().basic(username, password)
                .queryParams(Map.of("fromAccountId", fromAccountId,"toAccountId",toAccountId,"amount",amount))
                .when()
                .post("/services_proxy/bank/transfer")
                .then().statusCode(200)
                .log().all();
    }

    @Test
    @DisplayName("Account Activity POST")
    public void AccountActivityPOST() {
        request
                .auth().basic(username, password)
                .when()
                .post("/services_proxy/bank/accounts/13212/transaction/month/All/type/All")
                .then().statusCode(200)
                .log().all();
    }}




