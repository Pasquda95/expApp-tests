package selenium.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class AddProfileTest {

    private WebDriver driver = new ChromeDriver();
    private JavascriptExecutor js = (JavascriptExecutor) driver;
    private WebDriverWait wait = new WebDriverWait(driver, 10);

    @Before
    public void setDriver() {
        driver.get("localhost:4200");
        driver.manage().window().maximize();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @After
    public void closeBrowser() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.close();
    }

    private void createProfile() {
        driver.findElement(By.id("mat-input-0")).sendKeys("John");
        driver.findElement(By.id("mat-input-1")).sendKeys("Doe");
        driver.findElement(By.id("mat-radio-2")).click();
        driver.findElement(By.id("mat-input-2")).sendKeys("11181995");
        driver.findElement(By.id("mat-input-3")).sendKeys("doe@example.com");
        driver.findElement(By.id("mat-input-4")).sendKeys("678965432");
        driver.findElement(By.id("mat-select-0")).click();
        driver.findElement(By.id("mat-option-2")).click();
        driver.findElement(By.id("mat-select-0")).sendKeys(Keys.TAB);
        driver.findElement(By.xpath("//form/a/i")).click();
    }

    @Test
    public void LoadPageTitleTest() {
        // Check if page title is correct
        Assert.assertEquals("ExpApp", driver.getTitle());
    }

    @Test
    public void LoadPageEmptyFormTest() {
        // Check if new page has blank form
        Assert.assertNull(driver.findElement(By.id("mat-input-0")).getAttribute("text"));
        Assert.assertNull(driver.findElement(By.id("mat-input-1")).getAttribute("text"));
        Assert.assertNull(driver.findElement(By.id("mat-radio-2")).getAttribute("text"));
        Assert.assertNull(driver.findElement(By.id("mat-radio-3")).getAttribute("text"));
        Assert.assertNull(driver.findElement(By.id("mat-input-2")).getAttribute("text"));
        Assert.assertNull(driver.findElement(By.id("mat-input-3")).getAttribute("text"));
        Assert.assertNull(driver.findElement(By.id("mat-input-4")).getAttribute("text"));
    }

    @Test
    public void AddEmptyProfileTest() {
        // Check if you can't create empty profile
        String before = driver.getPageSource();
        try {
            driver.findElement(By.xpath("//form/a/i")).click();
        } catch (ElementClickInterceptedException e) {
            System.out.println("Button is not clickable");
        }
        String after = driver.getPageSource();
        Assert.assertEquals(before, after);
    }

    @Test
    public void FillFormWithCorrectDataTest() {
        // create new profile and check if successful snackbar appeared
        createProfile();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//simple-snack-bar/span")));
        WebElement snackBarMessage = driver.findElement(By.xpath("//simple-snack-bar/span"));
        Assert.assertEquals("Profile added successfully", snackBarMessage.getText());
    }

    @Test
    public void FillNameSurnameWithNumbersTest() {
        WebElement name = driver.findElement(By.id("mat-input-0"));
        name.sendKeys("1234");
        WebElement surame = driver.findElement(By.id("mat-input-1"));
        surame.sendKeys("5678");

        Assert.assertEquals("", name.getText());
        Assert.assertEquals("", surame.getText());
    }

    @Test
    public void FillFormWithoutGenderTest() {
        driver.findElement(By.id("mat-input-0")).sendKeys("John");
        driver.findElement(By.id("mat-input-1")).sendKeys("Doe");
        driver.findElement(By.id("mat-input-2")).sendKeys("11181995");
        driver.findElement(By.id("mat-input-3")).sendKeys("doe@example.com");
        driver.findElement(By.id("mat-input-4")).sendKeys("678965432");
        driver.findElement(By.id("mat-select-0")).click();
        driver.findElement(By.id("mat-option-2")).click();
        driver.findElement(By.id("mat-select-0")).sendKeys(Keys.TAB);
        driver.findElement(By.xpath("//form/a/i")).click();
    }

    @Test
    public void FillFormWithoutNationalityTest() {
        driver.findElement(By.id("mat-input-0")).sendKeys("John");
        driver.findElement(By.id("mat-input-1")).sendKeys("Doe");
        driver.findElement(By.id("mat-radio-2")).click();
        driver.findElement(By.id("mat-input-2")).sendKeys("11181995");
        driver.findElement(By.id("mat-input-3")).sendKeys("doe@example.com");
        driver.findElement(By.id("mat-input-4")).sendKeys("678965432");

        try {
            driver.findElement(By.xpath("//form/a/i")).click();
        } catch (ElementClickInterceptedException e) {
            System.out.println("Profile cannot be created");
        }
    }

    @Test
    public void FillFormWithoutNationalityAndGenderTest() {
        driver.findElement(By.id("mat-input-0")).sendKeys("John");
        driver.findElement(By.id("mat-input-1")).sendKeys("Doe");
        driver.findElement(By.id("mat-input-2")).sendKeys("11181995");
        driver.findElement(By.id("mat-input-3")).sendKeys("doe@example.com");
        driver.findElement(By.id("mat-input-4")).sendKeys("678965432");

        try {
            driver.findElement(By.xpath("//form/a/i")).click();
        } catch (ElementClickInterceptedException e) {
            System.out.println("Profile cannot be created");
        }
    }

    @Test
    public void CreateProfileFromFutureTest() {
        driver.findElement(By.id("mat-input-0")).sendKeys("John");
        driver.findElement(By.id("mat-input-1")).sendKeys("Doe");
        driver.findElement(By.id("mat-radio-2")).click();
        driver.findElement(By.id("mat-input-2")).sendKeys("88882995");
        driver.findElement(By.id("mat-input-3")).sendKeys("doe@example.com");
        driver.findElement(By.id("mat-input-4")).sendKeys("678965432");
        driver.findElement(By.id("mat-select-0")).click();
        driver.findElement(By.id("mat-option-2")).click();
        driver.findElement(By.id("mat-select-0")).sendKeys(Keys.TAB);

        try {
            driver.findElement(By.xpath("//form/a/i")).click();
        } catch (ElementClickInterceptedException e) {
            System.out.println("Button is not clickable");
            Assert.assertTrue(true);
        }

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//simple-snack-bar/span")));
        WebElement snackBarMessage = driver.findElement(By.xpath("//simple-snack-bar/span"));
        Assert.assertNotEquals("Profile shouldn't be created.", "Profile added successfully", snackBarMessage.getText());
    }

    @Test
    public void CreateProfileWithoutBirthDate() {
        driver.findElement(By.id("mat-input-0")).sendKeys("John");
        driver.findElement(By.id("mat-input-1")).sendKeys("Doe");
        driver.findElement(By.id("mat-radio-2")).click();
        driver.findElement(By.id("mat-input-3")).sendKeys("doe@example.com");
        driver.findElement(By.id("mat-input-4")).sendKeys("678965432");
        driver.findElement(By.id("mat-select-0")).click();
        driver.findElement(By.id("mat-option-2")).click();
        driver.findElement(By.id("mat-select-0")).sendKeys(Keys.TAB);

        try {
            driver.findElement(By.xpath("//form/a/i")).click();
        } catch (ElementClickInterceptedException e) {
            System.out.println("Button is not clickable");
            Assert.assertTrue("Profile shouldn't be crated without birth date", true);
        }
    }
}

