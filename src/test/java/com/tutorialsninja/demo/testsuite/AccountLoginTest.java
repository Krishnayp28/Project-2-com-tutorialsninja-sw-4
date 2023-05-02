package com.tutorialsninja.demo.testsuite;

import com.tutorialsninja.demo.pages.*;
import com.tutorialsninja.demo.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AccountLoginTest extends BaseTest {

    HomePage homePage = new HomePage();
    AccountRegisterPage accountregisterPage = new AccountRegisterPage();
    AccountCreationPage accountCreationPage = new AccountCreationPage();
    MyAccountPage myaccountPage = new MyAccountPage();
    AccountLoginPage  accountloginPage = new AccountLoginPage();
    LogoutPage logoutPage = new LogoutPage();

    @Test
    public void verifyUserShouldNavigateToRegisterPageSuccessfully() {
        //1.1 Click on My Account Link.
        homePage.clickOnMyAccount();
        //1.2 Call the method “selectMyAccountOptions” method and pass the parameter “Register”
        homePage.selectMyAccountOptions("Register");
        //1.3 Verify the text “Register Account”.
        Assert.assertEquals(homePage.getRegisterAccountText(), "Register Account", "Register page not displayed");
    }

    @Test
    public void verifyUserShouldNavigateToLoginPageSuccessfully() {
        //2.1 Click on My Account Link.
        homePage.clickOnMyAccount();
        //2.2 Call the method “selectMyAccountOptions” method and pass the parameter “Register”
        homePage.selectMyAccountOptions("Login");
        //2.3 Verify the text “Register Account”.
        Assert.assertEquals(homePage.getLoginAccountText(), "Returning Customer", "Login page not displayed");
    }

    @Test
    public void verifyThatUserRegisterAccountSuccessfully()  {
        // Click on My Account Link.
        homePage.clickOnMyAccount();
        // Call the method “selectMyAccountOptions” method and pass the parameter “Register”
        homePage.selectMyAccountOptions("Register");

        // Enter First Name
        accountregisterPage.sendFirstName("Jigna");
        //Enter Last Name
        accountregisterPage.sendLastName("Patel");
        //Enter Email
        accountregisterPage.sendEmail("jignayp");
        // Enter Telephone
        accountregisterPage.sendTelephone("0782566505");
        // Enter Password
        accountregisterPage.sendPassword("123jigna");
        // Enter Password Confirm
        accountregisterPage.sendConfirmPassword("jigna123");
        // Select Subscribe Yes radio button
        accountregisterPage.clickOnSubscribeToNewsletter();
        // Click on Privacy Policy check box
        accountregisterPage.clickOnPrivacyPolicy();
        // Click on Continue button
        accountregisterPage.clickOnContinueAfterPrivacyButton();
        // Verify the message “Your Account Has Been Created!”
        Assert.assertEquals(accountCreationPage.getAccountCreationMessage(), "Your Account Has Been Created!", "account not created");
        // Click on Continue button
        accountCreationPage.clickOnContinueAfterAccountCreation();
        // Click on My Account Link.
        myaccountPage.clickOnMyAccountLink();
        //Call the method “selectMyAccountOptions” method and pass the parameter “Logout”
        myaccountPage.selectMyAccountOption("Logout");
        //Verify the text “Account Logout”
        Assert.assertEquals(logoutPage.getTextFromLogout(), "Account Logout", "not logged out");
        // Click on Continue button
        myaccountPage.clickOnContinueAfterLogout();

    }

    @Test
    public void verifyThatUserShouldLoginAndLogoutSuccessfully() {
        //Click on My Account Link.
        homePage.clickOnMyAccount();
        //Call the method “selectMyAccountOptions” method and pass the parameter “Register”
        homePage.selectMyAccountOptions("Login");
        //enter email
        accountloginPage.sendEmail("jignayp@gmail.com");
        //enter password
        accountloginPage.sendPassword("123jigna");
        //click login
        accountloginPage.clickOnLogin();
        //verify if expected equals actual
        Assert.assertEquals(myaccountPage.getMyAccountText(), "My Account", "Not on my account");
        //click my account
        myaccountPage.clickOnMyAccountLink();
        //choose logout
        homePage.selectMyAccountOptions("Logout");
        //verify if logout has occurred
        Assert.assertEquals(logoutPage.getTextFromLogout(), "Account Logout", "Not logged out");
    }
}
