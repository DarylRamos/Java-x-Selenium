package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.service.DriverFinder;

import java.io.File;

public class Scenario2{
    public static void main(String[] args) {
        // LOG IN USING LOCKED OUT USER

        // Initializing Chrome Driver
        String driverLocation = DriverFinder.getPath(ChromeDriverService.createDefaultService(), new ChromeOptions());
        ChromeDriverService service = new ChromeDriverService.Builder().usingDriverExecutable(new File(driverLocation)).build();
        WebDriver driver = new ChromeDriver(service);

        // Website Navigation
        driver.get("https://www.saucedemo.com/");

        // Initializing variables for elements needed in login using IDs
        WebElement usernameInput = driver.findElement(By.id("user-name"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        // Input for login details and Login button click
        usernameInput.sendKeys("locked_out_user");
        passwordInput.sendKeys("secret_sauce");
        loginButton.click();

        // VERIFY ERROR MESSAGE
        WebElement lockedoutElement = driver.findElement(By.className("error-message-container"));

        // Output the result based on the presence of the element
        if (lockedoutElement.isDisplayed()) {
            System.out.println("Epic sadface: Sorry, this user has been locked out.");
        } else {
            System.out.println("Epic happyface: This user has not been locked out.");
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Close the browser and the service
        driver.quit();
        service.stop();
    }
}
