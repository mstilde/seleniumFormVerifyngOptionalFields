package com.form;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Main {
    
    private WebDriver driver;

    public Main(WebDriver driver) {
        this.driver = driver;
    }

    public void implicitlyWait(int seconds) {
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    // Navigate to Form website
    public void navigateToForm() {
        driver.get("https://mstilde.github.io/BasicFormProject/");
    }

    // Send string
    public void sendStrings(By locator, String string) {
        driver.findElement(locator).sendKeys(string);
    }

    // Click element
    public void click(By locator) {
        driver.findElement(locator).click();
    }

    // Select Dropdown
    public void selectDropdown(int index) {
        WebElement dropdown = driver.findElement(By.id("dropdown"));
        Select select = new Select(dropdown);
        select.selectByIndex(index);
    }

    // Verify Dropdown
    public boolean verifyDropdown(String index) {
        WebElement dropdownElement = driver.findElement(By.id("dropdown"));
        Select dropdown = new Select(dropdownElement);
        String selectedOption = dropdown.getFirstSelectedOption().getText();
        if (selectedOption.equals(index)) {
            return true;
        } else {
            return false;
        }
    }

    // Verify Checkbox/Radio Button is selected
    public boolean verifySelected(By locator) {
        WebElement selected = driver.findElement(locator);
        if (selected.isSelected()) {
            return true;
        } else {
            return false;
        }
    }

    // Verify Textarea
    public boolean verifyTextarea(By locator, String text) {
        WebElement textarea = driver.findElement(locator);
        String textSent = textarea.getAttribute("value");
        if (textSent.equals(text)) {
            return true;
        } else {
            return false;
        }
    }
}
