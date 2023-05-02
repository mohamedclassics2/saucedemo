package com.sauce.step_definitions;

import com.sauce.pages.*;
import com.sauce.utilities.ConfigurationReader;
import com.sauce.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Steps {
    LoginPage loginPage = new LoginPage();
    ProductsPage productsPage = new ProductsPage();
    YourCartPage yourCartPage = new YourCartPage();
    YourInformationPage yourInformationPage = new YourInformationPage();
    OverviewPage overviewPage = new OverviewPage();

    @Given("user is on login page")
    public void user_is_on_login_page() {
        Driver.get().get(ConfigurationReader.get("url"));
    }

    @When("user enters valid username and valid password")
    public void user_enters_valid_username_and_valid_password() {
        loginPage.username.clear();
        loginPage.username.sendKeys(ConfigurationReader.get("validUsername"));
        loginPage.password.clear();
        loginPage.password.sendKeys(ConfigurationReader.get("ValidPassword"));
        loginPage.login.click();
    }

    @Then("user is on {string} page")
    public void user_is_on_page(String actualPage) {
        System.out.println("actualPage = " + actualPage);

        switch (actualPage) {
            case "Products":
                String expectedProductURL = "https://www.saucedemo.com/inventory.html";
                String actualURL = Driver.get().getCurrentUrl();
                assertEquals(expectedProductURL, actualURL);
                break;
            case "Checkout: Your Information":
                String expectedCheckoutURL = "https://www.saucedemo.com/checkout-step-one.html";
                String actualcheckoutURL = Driver.get().getCurrentUrl();
                assertEquals(expectedCheckoutURL, actualcheckoutURL);
                break;
            case "Overview":
                String expectedOverviewURL = "https://www.saucedemo.com/checkout-step-two.html";
                String actualOverviewURL = Driver.get().getCurrentUrl();
                assertEquals(expectedOverviewURL, actualOverviewURL);
                break;
            case "Complete":
                String expectedCompleteURL = "https://www.saucedemo.com/checkout-complete.html";
                String actualCompleteURL = Driver.get().getCurrentUrl();
                assertEquals(expectedCompleteURL, actualCompleteURL);
            default:
                System.out.println("URL error");
        }
    }

    @When("user enters invalid username and invalid password")
    public void user_enters_invalid_username_and_invalid_password() {
        loginPage.username.clear();
        loginPage.username.sendKeys(ConfigurationReader.get("InValidUsername"));
        loginPage.password.clear();
        loginPage.password.sendKeys(ConfigurationReader.get("InValidPassword"));
        loginPage.login.click();
    }

    @Then("user sees the login error message")
    public void user_sees_the_login_error_message() {
        assertTrue(loginPage.loginErrorMessage.isDisplayed());
    }

    @Given("user is logged in  on {string} page")
    public void user_is_logged_in_on_page(String products) {
        Driver.get().get(ConfigurationReader.get("url"));
        loginPage.username.clear();
        loginPage.username.sendKeys(ConfigurationReader.get("validUsername"));
        loginPage.password.clear();
        loginPage.password.sendKeys(ConfigurationReader.get("ValidPassword"));
        loginPage.login.click();
    }

    @When("user adds {string} to the cart")
    public void user_adds_to_the_cart(String item) throws InterruptedException {
        productsPage.backpack.click();
        Thread.sleep(3000);
    }

    @When("user clicks on {string}")
    public void user_clicks_on(String button) throws InterruptedException {
        System.out.println("button = " + button);
        switch (button) {
            case "Cart":
                productsPage.cart.click();
                Thread.sleep(3000);
                break;
            case "checkout":
                yourCartPage.checkout.click();
                Thread.sleep(3000);
                break;
            case "Finish":
                overviewPage.finish.click();
                Thread.sleep(3000);
                break;
            default:
                System.out.println("No item in cart");
        }
    }

    @Then("user sees the {string} for the {string}")
    public void user_sees_the_for_the(String rightPrice, String item) {
        String expectedBAckpackPrice = "$29.99";
        String actualBackpackPrice = productsPage.price.getText();
        assertEquals(expectedBAckpackPrice, actualBackpackPrice);
    }

    @Then("user enters {string}")
    public void user_enters(String value) throws InterruptedException {
        System.out.println("value = " + value);
        switch (value) {
            case "name":
                yourInformationPage.firstName.sendKeys(ConfigurationReader.get("purchaserName"));
                Thread.sleep(3000);
                break;
            case "surname":
                yourInformationPage.lastName.sendKeys(ConfigurationReader.get("purchaserSurname"));
                Thread.sleep(3000);
                break;
            case "postcode":
                yourInformationPage.postcode.sendKeys(ConfigurationReader.get("purchaserPostcode"));
                Thread.sleep(3000);
                break;
            default:
                System.out.println("Missing data");
        }

    }

    @Then("user clicks continue")
    public void user_clicks_continue() {
        yourInformationPage.continueButton.click();

    }

}
