package com.Pages;

import com.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * PageTransferFounds Class
 *
 * @author Leticia
 * @version 1.0
 */
public class PageTransferFounds extends BasePage {

    WebDriverWait wait = new WebDriverWait(_driver, Duration.ofMillis(1000));

    protected static final String transferFoundsCssSelector = "a[href='/parabank/transfer.htm']";
    protected static final String messageTransferFoundsXPath = "//h1[normalize-space()='Transfer Complete!']";
    protected static final String inputAmountId = "amount";
    protected static final String selectorFromAccountId = "fromAccountId";
    protected static final String selectorToAccountId = "toAccountId";
    protected static final String buttonTransferFoundsRequestCssSelector = "input[value='Transfer']";

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
    /* ====================== TRANSFER FOUNDS METHODS ======================= */
    /* ====================================================================== */
    public void redirectToTransferFoundsPage() {
        redirectTo(By.cssSelector(transferFoundsCssSelector));
    }

    public void fillAmountInput(String amount) {
        fillInput(By.id(inputAmountId), amount);
    }
    public void selectFromAccount() {
        Select selector = new Select(_driver.findElement(By.id(selectorFromAccountId)));
        selector.selectByIndex(0);
    }

    public void selectToAccount() {
        Select selector = new Select(_driver.findElement(By.id(selectorToAccountId)));
        selector.selectByIndex(0);
    }

    public void transferFoundsRequest() {
        clickButton(By.cssSelector(buttonTransferFoundsRequestCssSelector));
    }

    public String validateTransferFoundsRequestSuccess() {
        return textVisibilityOnPage(By.xpath(messageTransferFoundsXPath));
    }
}