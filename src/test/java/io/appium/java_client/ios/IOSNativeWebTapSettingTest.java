package io.appium.java_client.ios;

import static org.junit.Assert.assertTrue;

import java.time.Duration;

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IOSNativeWebTapSettingTest extends BaseSafariTest {

    @Test
    @Ignore("https://github.com/appium/appium/issues/17014")
    public void nativeWebTapSettingTest() {
        assertTrue(driver.isBrowser());
        driver.get("https://saucelabs.com/test/guinea-pig");

        // do a click with nativeWebTap turned on, and assert we get to the right page
        driver.nativeWebTap(true);
        WebElement el = driver.findElement(By.id("i am a link"));
        el.click();
        assertTrue(new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.titleIs("I am another page title - Sauce Labs")));
        driver.navigate().back();

        // now do a click with it turned off and assert the same behavior
        assertTrue(new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.titleIs("I am a page title - Sauce Labs")));
        driver.nativeWebTap(false);
        el = driver.findElement(By.id("i am a link"));
        el.click();
        assertTrue(new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.titleIs("I am another page title - Sauce Labs")));
    }
}
