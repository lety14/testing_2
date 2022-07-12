package com.Pages;

import com.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * PageAccountActivity Class
 *
 * @author Leticia
 * @version 1.0
 */
public class PageAccountActivity extends BasePage {

    WebDriverWait wait = new WebDriverWait(_driver, Duration.ofMillis(1000));

    protected static final String accountCssSelector = "a[class='ng-binding']";
    protected static final String titleAccountDetailsCssSelector = "div[ng-if='showDetails'] h1[class='title']";
    protected static final String selectorActivityPeriodId = "month";
    protected static final String optionActivityPeriodCssSelector = "select[id='month'] option[value='All']";
    protected static final String selectorAccountTypeId = "transactionType";
    protected static final String optionAccountTypeCssSelector = "select[id='transactionType'] option[value='All']";
    protected static final String buttonAccountDetailsRequestCssSelector = "input[value='Go']";


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

    public String textVisibilityOnPage(By locator) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        WebElement text = getWebElement(locator);
        return text.getText();
    }


    /* ====================================================================== */
    /* ====================== ACCOUNT ACTIVITY METHODS ====================== */
    /* ====================================================================== */

    public void clickAccountNumber() {
        clickButton(By.cssSelector(accountCssSelector));
    }

    public String titleVisible() {
        return textVisibilityOnPage(By.cssSelector(titleAccountDetailsCssSelector));
    }

    public void selectActivityPeriod() {
        selectOption(By.id(selectorActivityPeriodId), By.cssSelector(optionActivityPeriodCssSelector));
    }

    public void selectTypeAccount() {
        selectOption(By.id(selectorAccountTypeId), By.cssSelector(optionAccountTypeCssSelector));
    }
    public void accountDetailsRequest() {
        clickButton(By.cssSelector(buttonAccountDetailsRequestCssSelector));
    }
}
