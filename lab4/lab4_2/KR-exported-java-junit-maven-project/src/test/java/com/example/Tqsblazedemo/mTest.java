package com.example.Tqsblazedemo;

import static java.lang.invoke.MethodHandles.lookup;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;

class mTest {
        WebDriver driver;
        static final Logger log = org.slf4j.LoggerFactory.getLogger(lookup().lookupClass());

        @BeforeEach
        void setUp() {
                // System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
                FirefoxOptions options = new FirefoxOptions()
                                .addPreference("browser.startup.homepage", "https://www.google.com")
                                .setHeadless(true);
                driver = new FirefoxDriver(options);
        }

        @Test
        public void testUntitledTestCase() throws Exception {
                driver.get("https://blazedemo.com/index.php");
                new Select(driver.findElement(By.name("fromPort"))).selectByVisibleText("Philadelphia");
                new Select(driver.findElement(By.name("toPort"))).selectByVisibleText("Berlin");
                driver.findElement(By.xpath("//input[@value='Find Flights']")).click();
                driver.get("https://blazedemo.com/reserve.php");
                driver.findElement(By.xpath("//input[@value='Choose This Flight']")).click();
                driver.get("https://blazedemo.com/purchase.php");
                driver.findElement(By.id("inputName")).click();
                driver.findElement(By.id("inputName")).clear();
                driver.findElement(By.id("inputName")).sendKeys("assa");
                driver
                                .findElement(
                                                By.xpath(
                                                                "(.//*[normalize-space(text()) and normalize-space(.)='Name on Card'])[1]/following::label[1]"))
                                .click();
                driver.findElement(By.xpath("//input[@value='Purchase Flight']")).click();
                driver.get("https://blazedemo.com/confirmation.php");
                driver.findElement(
                                By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='home'])[1]/following::h1[1]"))
                                .click();
                driver.findElement(
                                By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='home'])[1]/following::h1[1]"))
                                .click();
                // ERROR: Caught exception [ERROR: Unsupported command [doubleClick |
                // xpath=(.//*[normalize-space(text()) and
                // normalize-space(.)='home'])[1]/following::h1[1] | ]]
                driver.findElement(
                                By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='home'])[1]/following::div[2]"))
                                .click();
                assertEquals("BlazeDemo Confirmation", driver.getTitle());
                // ERROR: Caught exception [unknown command []]
        }

        @AfterEach
        void teardown() {
                driver.quit();
        }
}