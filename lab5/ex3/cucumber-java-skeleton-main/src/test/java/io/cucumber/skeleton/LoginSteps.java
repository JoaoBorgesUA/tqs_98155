package io.cucumber.skeleton;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

    private WebDriver driver;

    @When("I navigate to {string}")
    public void iNavigateTo(String url) {
        driver = WebDriverManager.chromedriver().create();
        driver.get(url);
    }

    @And("I login with the username {string} and password {string}")
    public void iLogin(String username, String password) {
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

    }

    @And("I click Submit")
    public void iPressEnter() {
        driver.findElement(By.cssSelector("button")).click();
    }

    @Then("I should be see the message {string}")
    public void iShouldSee(String result) {
        try {
            driver.findElement(
                    By.xpath("//*[contains(text(), '" + result + "')]"));
        } catch (NoSuchElementException e) {
            throw new AssertionError(
                    "\"" + result + "\" not available in results");
        } finally {
            driver.quit();
        }
    }

    @And("I click to arrive in {string}")
    public void iClickToArrive(String city) {
        Select selector = new Select(driver.findElement(By.name("toPort")));
        selector.selectByValue(city);
    }

    @And("I click Find Flights")
    public void iClickFindFlights() {
        driver.findElement(By.tagName("input")).click();
    }

    @And("I choose the first flight")
    public void iChooseFirstFlight() {
        driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[1]/td[1]/input")).click();
    }

    @Then("the the Total Cost should be {int}")
    public void assertFlightNumber(String cost) {
        try {
            WebElement element = driver.findElement(By.xpath("/html/body/div[2]/p[5]"));
            Assertions.assertTrue(element.getText().contains(cost));
        } catch (NoSuchElementException e) {
            throw new AssertionError(
                    "\"" + cost + "\" not available in results");
        } finally {
            driver.quit();
        }
    }

}