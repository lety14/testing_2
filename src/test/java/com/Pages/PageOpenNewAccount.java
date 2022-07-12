package com.Pages;

import com.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * PageOpenNewAccount Class
 *
 * @author Leticia
 * @version 1.0
 */
public class PageOpenNewAccount extends BasePage {

    WebDriverWait wait = new WebDriverWait(_driver, Duration.ofMillis(1000));

    protected static final String buttonOpenNewAccountCssSelector = "a[href='/parabank/openaccount.htm']";
    protected static final String typeNewAccountID = "type";
    protected static final String optionOneCssSelector = "option[value='1']";
    protected static final String buttonOpenNewAccountRequestCssSelector = "input[value='Open New Account']";
    protected static final String messageSuccessNewAccountXPath = "//p[normalize-space()='Congratulations, your account is now open.']";



    public void clickButton(By locator) {
        WebElement button = getWebElement(locator);
        wait.until(ExpectedConditions.elementToBeClickable(button));
        button.click();
    }

    public void selectOption(By locatorSelector, By locatorOption) {
        WebElement selector = getWebElement(locatorSelector);
        wait.until(ExpectedConditions.elementToBeClickable(selector));
        selector.click();
        WebElement option = getWebElement(locatorOption);
        wait.until(ExpectedConditions.elementToBeClickable(option));
        option.click();
    }

    public void redirectTo(By locator) {
        WebElement link = getWebElement(locator);
        wait.until(ExpectedConditions.visibilityOf(link));
        link.click();
    }

    public String textVisibilityOnPage(By locator) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        WebElement text = getWebElement(locator);
        return text.getText();
    }

    /* ====================================================================== */
    /* ====================== OPEN NEW ACCOUNT METHODS ====================== */
    /* ====================================================================== */

    public void redirectToOpenNewAccountPage() {
        redirectTo(By.cssSelector(buttonOpenNewAccountCssSelector));
    }

    public void selectAccountType() {
        selectOption(By.id(typeNewAccountID), By.cssSelector(optionOneCssSelector));
    }

    public void openNewAccountRequest() {
        clickButton(By.cssSelector(buttonOpenNewAccountRequestCssSelector));
    }

    public String validateOpenNewAccountSuccess() {
        return textVisibilityOnPage(By.xpath(messageSuccessNewAccountXPath));
    }
}
