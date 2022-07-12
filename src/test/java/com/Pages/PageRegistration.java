package com.Pages;

import com.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * PageRegistration Class
 *
 * @author Leticia
 * @version 1.0
 */
public class PageRegistration extends BasePage {

    WebDriverWait wait = new WebDriverWait(_driver, Duration.ofMillis(1000));

    protected static final String buttonRedirectToRegisterXPath = "//a[normalize-space()='Register']";
    protected static final String inputFirstNameID = "customer.firstName";
    protected static final String inputLastNameID = "customer.lastName";
    protected static final String inputAddressID = "customer.address.street";
    protected static final String inputCityID = "customer.address.city";
    protected static final String inputStateID = "customer.address.state";
    protected static final String inputZipCodeID = "customer.address.zipCode";
    protected static final String inputPhoneID = "customer.phoneNumber";
    protected static final String inputSSNID = "customer.ssn";
    protected static final String inputUserNameID = "customer.username";
    protected static final String inputPasswordID = "customer.password";
    protected static final String inputPasswordConfirmID = "repeatedPassword";
    protected static final String buttonRegisterCssSelector = "input[value='Register']";
    protected static final String successRegistrationMessageCssSelector = "div[id='rightPanel'] p";

    public void fillInput(By locator, String input) {
        WebElement inputField = getWebElement(locator);
        inputField.clear();
        inputField.sendKeys(input);
        wait.until(ExpectedConditions.textToBePresentInElementValue(inputField, input));
    }

    public void clickButton(By locator) {
        WebElement button = getWebElement(locator);
        wait.until(ExpectedConditions.elementToBeClickable(button));
        button.click();
    }

    public String textVisibilityOnPage(By locator) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        WebElement text = getWebElement(locator);
        return text.getText();
    }

    /* ====================================================================== */
    /* ======================== REGISTRATION METHODS ======================== */
    /* ====================================================================== */
    public void redirectToRegisterPage() {
        WebElement buttonRedirectToRegistration = getWebElement(By.xpath(buttonRedirectToRegisterXPath));
        wait.until(ExpectedConditions.visibilityOf(buttonRedirectToRegistration));
        buttonRedirectToRegistration.click();
    }

    public void fillRegistrationForm(String firstName, String lastName, String address, String city, String state, String zipCode, String phone, String ssn, String userName, String password, String passwordConfirm) {
        fillInput(By.id(inputFirstNameID), firstName);
        fillInput(By.id(inputLastNameID), lastName);
        fillInput(By.id(inputAddressID), address);
        fillInput(By.id(inputCityID), city);
        fillInput(By.id(inputStateID), state);
        fillInput(By.id(inputZipCodeID), zipCode);
        fillInput(By.id(inputPhoneID), phone);
        fillInput(By.id(inputSSNID), ssn);
        fillInput(By.id(inputUserNameID), userName);
        fillInput(By.id(inputPasswordID), password);
        fillInput(By.id(inputPasswordConfirmID), passwordConfirm);
    }

    public void registrationRequest() {
        clickButton(By.cssSelector(buttonRegisterCssSelector));
    }

    public String validateRegistrationRequestSuccess() {
        return textVisibilityOnPage(By.cssSelector(successRegistrationMessageCssSelector));
    }
}