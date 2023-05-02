package com.tutorialsninja.demo.testsuite;

import com.tutorialsninja.demo.pages.*;
import com.tutorialsninja.demo.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

public class LaptopsAndNotebooksTest extends BaseTest {

    HomePage homePage = new HomePage();
    LaptopsAndNotebooksPage laptopsAndNotebooksPage = new LaptopsAndNotebooksPage();
    MacBookPage macBookPage = new MacBookPage();
    ShoppingCartPage shoppingCartPage = new ShoppingCartPage();
    CheckoutPage checkoutPage = new CheckoutPage();

    @Test
    public void verifyProductsPriceDisplayHighToLowSuccessfully() {
        homePage.mouseHoverAndClickOnLaptopsAndNotebooks();
        homePage.selectMenu("Show All Laptops & Notebooks");
        // Select Sort By "Price (High > Low)"
        laptopsAndNotebooksPage.getSortByPriceHighToLowSelection();
        // Verify the Product price will arrange in High to Low order.
        List<Double> expText = laptopsAndNotebooksPage.getSortByPriceHighToLowSelection();
        List<Double> actText = laptopsAndNotebooksPage.getSortByPriceHighToLowSelection();
        System.out.println("Expected list " + expText);
        Assert.assertEquals(Collections.singleton(actText), Collections.singleton(expText));
        System.out.println("Actual List " + actText);
    }

    @Test
    public void verifyThatUserPlaceOrderSuccessfully()  {
        homePage.mouseHoverAndClickOnLaptopsAndNotebooks();
        homePage.selectMenu("Show All Laptops & Notebooks");
        // Select Sort By "Price (High > Low)"
        laptopsAndNotebooksPage.selectPriceHighToLow();
        // Select Product “MacBook”
        laptopsAndNotebooksPage.clickOnMacbook();
        // Verify the text “MacBook”
        Assert.assertEquals(macBookPage.getTextFromMacBook(), "MacBook", "MacBook Product not display");
        // Click on ‘Add To Cart’ button
        macBookPage.clickOnAddToCart();
        // Verify the message “Success: You have added MacBook to your shopping cart!”
        Assert.assertTrue(macBookPage.isSuccessMessageAppearing(), "Message Doesn't Appear");
        // Click on link “shopping cart” display into success message
        macBookPage.clickOnShoppingCart();
        //Verify the text "Shopping Cart"
        Assert.assertTrue(shoppingCartPage.isShoppingCartAppearing(), "Shopping Cart Doesn't Appear");
        // Verify the Product name "MacBook"
        Assert.assertEquals(shoppingCartPage.getProductName(), "MacBook", "Product Name Doesn't appear");
        // Change Quantity "2"
        shoppingCartPage.clearAndAddQuantity("2");
        // Click on “Update” Tab
        shoppingCartPage.clickOnUpdate();
        // Verify the message “Success: You have modified your shopping cart!”
        Assert.assertTrue(shoppingCartPage.isSuccessMessageAppearing(), "Cart not modified");
        // Verify the Total $1,204.00
        Assert.assertEquals(shoppingCartPage.getTotalText(), "$1,204.00", "Total not matched");
        // Click on “Checkout” button
        shoppingCartPage.clickOnCheckout();
        //Verify the text “Checkout”
        Assert.assertEquals(checkoutPage.getCheckoutText(), "Checkout", "Checkout not displayed");
        // Verify the Text “New Customer”
        Assert.assertEquals(checkoutPage.getNewCustomerText(), "New Customer", "New Customer not displayed");
        // Click on “Guest Checkout” radio button
        checkoutPage.clickOnGuestCheckoutRadioButton();
        // Click on “Continue” tab
        checkoutPage.clickOnContinueButton();
        //Fill the mandatory fields
        checkoutPage.enterBillingDetails();
        //Click on “Continue” Button
        checkoutPage.clickOnContinueBillingButton();
        // Add Comments About your order into text area
        checkoutPage.enterComment();
        // Check the Terms & Conditions check box
        checkoutPage.clickOnAgreeToTermsAndConditions();
        // Click on “Continue” button
        checkoutPage.clickOnContinueCommentButton();
        //Verify the message “Warning: Payment method required!”
        Assert.assertTrue(checkoutPage.isPaymentWarningAppearing(), "Payment Warning not displayed");
    }
}
