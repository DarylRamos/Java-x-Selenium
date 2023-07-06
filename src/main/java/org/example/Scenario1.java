package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.service.DriverFinder;

import java.io.File;

public class Scenario1{
    public static void main(String[] args) {
        //LOG IN USING STANDARD USER

        // Initializing Chrome Driver
        String driverLocation = DriverFinder.getPath(ChromeDriverService.createDefaultService(), new ChromeOptions());
        ChromeDriverService service = new ChromeDriverService.Builder().usingDriverExecutable(new File(driverLocation)).build();
        WebDriver driver = new ChromeDriver(service);

        // Website Navigation
        driver.get("https://www.saucedemo.com/");

        // Initializing variables for elements needed in login using IDs
        WebElement userInput = driver.findElement(By.id("user-name"));
        WebElement passInput = driver.findElement(By.id("password"));
        WebElement loginBtn = driver.findElement(By.id("login-button"));

        // Input for login details and Login button click
        userInput.sendKeys("standard_user");
        passInput.sendKeys("secret_sauce");
        loginBtn.click();

        // VERIFY THAT USER IS ABLE TO NAVIGATE TO HOME PAGE

        // Getting the ID of one element in the home page
        WebElement inventoryElement = driver.findElement(By.className("inventory_list"));

        // Output the result based on the presence of the element
        if (inventoryElement.isDisplayed()) {
            System.out.println("Logged in successfully!");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // LOG OUT

            // Initialize the ID of burger menu button and clicking it
            WebElement menuBtn = driver.findElement(By.id("react-burger-menu-btn"));
            menuBtn.click();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Initialize the ID of logout button and clicking it
            WebElement logoutButton = driver.findElement(By.id("logout_sidebar_link"));
            logoutButton.click();

            // VERIFY THAT THE USER IS BACK TO THE LOGIN PAGE
            WebElement loginElement = driver.findElement(By.className("login_wrapper-inner"));
            if (loginElement.isDisplayed()){
                System.out.println("Logged out successfully!");
            } else {
                System.out.println("Logout failed!");
            }
        } else {
            System.out.println("Login failed!");
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