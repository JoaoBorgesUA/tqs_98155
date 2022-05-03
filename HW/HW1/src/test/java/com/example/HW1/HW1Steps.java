package com.example.HW1;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class HW1Steps {

    private WebDriver driver;

    @When("I navigate to {string}")
    public void iNavigateTo(String url) {
        driver = WebDriverManager.chromedriver().create();
        driver.get(url);
    }

    @And("I select the searchbar and type {string}")
    public void iSearch(String c) {
        driver.findElement(By.tagName("input")).sendKeys(c);

    }

    @And("I click on the Submit button")
    public void iPressEnter() {
        driver.findElement(By.tagName("button")).click();
    }

    @Then("the title of the page should be {string}")
    public void TitleOfthePage(String result) {
        assertEquals(result, driver.getTitle());
    }

    @And("there should be a card with the title {string} and value {string}")
    public void NewCasesCards(String title, String value) {
        assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/p[1]")).getText().contains(title));
        assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/p[2]")).getText().contains(value));
    }
}
