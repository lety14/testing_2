package com.Pages;

import com.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * PageAccountOverview Class
 *
 * @author Leticia
 * @version 1.0
 */
public class PageAccountOverview extends BasePage {

    WebDriverWait wait = new WebDriverWait(_driver, Duration.ofMillis(1000));

    protected static final String accountOverviewCssSelector = "a[href='/parabank/overview.htm']";
    protected static final String messageAccountsOverviewCssSelector = "td[colspan='3']";


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
    /* ====================== ACCOUNT OVERVIEW METHODS ====================== */
    /* ====================================================================== */

    public void redirectToAccountOverviewPage() {
        redirectTo(By.cssSelector(accountOverviewCssSelector));
    }

    public String messageAccountOverview() {
        return textVisibilityOnPage(By.cssSelector(messageAccountsOverviewCssSelector));
    }
}
