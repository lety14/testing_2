package com.Test;

import com.Base.BasePage;
import com.Base.BaseTest;
import com.Pages.*;
import com.aventstack.extentreports.Status;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;


/**
 * Test Class
 *
 * @author Leticia
 * @version 1.0
 */
public class Test extends BaseTest {
    private static WebDriver driver;


    @BeforeAll
    static void setUp() {
        BasePage _basePage = new BasePage();
        _basePage.openApp();
        driver = _basePage.getDriver();
    }

    @org.junit.jupiter.api.Test
    @Tag("smoke")
    @DisplayName("Registration process, open a new account, account overview , founds transfer, account activity.")
    public void testLoginAndAddToCart()  {
        test = extent.createTest("Exam-testing2.");
        test.log(Status.INFO, "Test Started!");
        PageOpenNewAccount PageOpenNewAccount = new PageOpenNewAccount();
        PageRegistration PageRegistration = new PageRegistration();
        PageTransferFounds PageTransferfounds = new PageTransferFounds();
        PageAccountActivity PageAccountActivity = new PageAccountActivity();
        PageAccountOverview PageAccountOverview = new PageAccountOverview();

        PageRegistration.redirectToRegisterPage();
        try {
            test.log(Status.INFO, "1.- Test Registration");
            test.log(Status.PASS, "Open Registration Page");
            PageRegistration.fillRegistrationForm("Erica", "Green",
                    "Evergreen Avenue 233", "Rosario",
                    "Santa Fe", "2020", "123123123",
                    "123123", "EricaGreen2020",
                    "Hola*123123123", "Hola*123123123");
            test.log(Status.PASS, "Add Registration Information");
            PageRegistration.registrationRequest();
            test.log(Status.PASS, "Send Request To Register New User");
            String messageRegistration = PageRegistration.validateRegistrationRequestSuccess();
            assertTrue(messageRegistration.equals("Your account was created successfully. You are now logged in."));
            test.log(Status.PASS, "Check Successfully Registration");
            //
            test.log(Status.INFO, "2.- Test Open New Account");
            PageOpenNewAccount.redirectToOpenNewAccountPage();
            test.log(Status.PASS, "Redirected to open new account page");
            PageOpenNewAccount.selectAccountType();
            test.log(Status.PASS, "Selected account type 'SAVINGS'");
            PageOpenNewAccount.openNewAccountRequest();
            test.log(Status.PASS, "Request open new account");
            String messageNewAccount = PageOpenNewAccount.validateOpenNewAccountSuccess();
            assertTrue(messageNewAccount.equals("Congratulations, your account is now open."));
            test.log(Status.PASS, "Check Successfully Open New Account");
            //
            test.log(Status.INFO, "3.- Test Account Overview");
            PageAccountOverview.redirectToAccountOverviewPage();
            test.log(Status.PASS, "Redirected to account overview page");
            String messageAccountsOverview = PageAccountOverview.messageAccountOverview();
            assertTrue(messageAccountsOverview.equals("*Balance includes deposits that may be subject to holds"));
            test.log(Status.PASS, "Check Successfully Account Overview");
            //
            test.log(Status.INFO, "4.- Test Transfer Founds");
            PageTransferfounds.redirectToTransferFoundsPage();
            test.log(Status.PASS, "Redirected to transfer founds page");
            Thread.sleep(2000);
            PageTransferfounds.fillAmountInput("20000");
            test.log(Status.PASS, "Fill amount input");
            PageTransferfounds.selectFromAccount();
            test.log(Status.PASS, "Redirected to transfer founds page");
            PageTransferfounds.selectToAccount();
            test.log(Status.PASS, "Redirected to transfer founds page");
            PageTransferfounds.transferFoundsRequest();
            test.log(Status.PASS, "Request transfer founds");
            String messageTransferFounds = PageTransferfounds.validateTransferFoundsRequestSuccess();
            assertTrue(messageTransferFounds.contains("Transfer Complete!"));
            test.log(Status.PASS, "Check Successfully Transfer Founds");
            //
            test.log(Status.INFO, "5.- Test Account Details");
            PageAccountOverview.redirectToAccountOverviewPage();
            test.log(Status.PASS, "Redirected to account overview page");
            String messageAccountsOverview2 = PageAccountOverview.messageAccountOverview();
            assertTrue(messageAccountsOverview2.equals("*Balance includes deposits that may be subject to holds"));
            test.log(Status.PASS, "Check Successfully Account Overview");
            PageAccountActivity.clickAccountNumber();
            test.log(Status.PASS, "Click account number");
            String titleAccountDetails = PageAccountActivity.titleVisible();
            assertTrue(titleAccountDetails.equals("Account Details"));
            test.log(Status.PASS, "Check Successfully Account Details Tittle");
            PageAccountActivity.selectActivityPeriod();
            test.log(Status.PASS, "Selected period activity 'all'");
            PageAccountActivity.selectTypeAccount();
            test.log(Status.PASS, "Selected account type 'all'");
            PageAccountActivity.accountDetailsRequest();
            test.log(Status.PASS, "Request view account details");
        } catch (Exception e) {
            test.log(Status.FAIL, "The test has failed!");
        }
    }


    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}
