package com.form;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormTest {
    
    private WebDriver driver;
    private DriverFactory setUp;
    private Main chrome;

    @BeforeEach
    public void setUpChrome() {
        setUp = new DriverFactory(driver);
        driver = setUp.initiateDriver();
        chrome = new Main(driver);
        chrome.implicitlyWait(10);
    }

    @AfterEach
    public void closeChrome() {
        driver.quit();
    }

    // SF-30 - TC-1 - Verify dropdown options
    @Test
    public void verifyDropdownFunctionality() {

        chrome.navigateToForm();
        chrome.selectDropdown(1);
        // Verify selected dropdown
        assertTrue(chrome.verifyDropdown("Full-time employment"));
        // Verify you can swap dropdown option
        chrome.selectDropdown(2);
        // Verify swaped dropdown option is selected
        assertTrue(chrome.verifyDropdown("Part-time employment"));

        // Verifying other options
        chrome.selectDropdown(3);

        assertTrue(chrome.verifyDropdown("Freelance"));

        chrome.selectDropdown(4);

        assertTrue(chrome.verifyDropdown("I'm not currently working"));
    }

    // SF-30 - TC-2 - Verify checkboxes
    @Test
    public void verifyCheckboxesFunctionality() {

        chrome.navigateToForm();
        chrome.click(By.id("movies-check"));
        // Verify selected value is checked
        assertTrue(chrome.verifySelected(By.id("movies-check")));
        // Verify multiple values can be selected
        chrome.click(By.id("videogames-check"));
        chrome.click(By.id("comics-check"));
        // Verify multiple values are still selected
        assertTrue(chrome.verifySelected(By.id("movies-check")));
        assertTrue(chrome.verifySelected(By.id("videogames-check")));
        assertTrue(chrome.verifySelected(By.id("comics-check")));
        // Select an already checked value
        chrome.click(By.id("videogames-check"));
        // Verify value is unselected
        assertFalse(chrome.verifySelected(By.id("videogames-check")));
    }

    // SF-30 - TC-3 - Verify textarea
    @Test
    public void verifyTextareaFunctionality() {

        chrome.navigateToForm();
        chrome.sendStrings(By.id("other-media"), "This is a test string");
        // Verify string is sent
        assertTrue(chrome.verifyTextarea(By.id("other-media"), "This is a test string"));
    }

    // SF-30 - TC-4 - Verify radio buttons
    @Test
    public void verifyRadioButtonFunctionality() {
        
        chrome.navigateToForm();
        chrome.click(By.id("definitely-radio"));
        // Verify value is selected
        assertTrue(chrome.verifySelected(By.id("definitely-radio")));
        // Select another value
        chrome.click(By.id("maybe-radio"));
        // Verify proper values are selected/unselected
        assertFalse(chrome.verifySelected(By.id("definitely-radio")));
        assertTrue(chrome.verifySelected(By.id("maybe-radio")));
        // Select another value
        chrome.click(By.id("not-really-radio"));
        // Verify proper values are selected/unselected
        assertFalse(chrome.verifySelected(By.id("definitely-radio")));
        assertFalse(chrome.verifySelected(By.id("maybe-radio")));
        assertTrue(chrome.verifySelected(By.id("not-really-radio")));
    }
}
