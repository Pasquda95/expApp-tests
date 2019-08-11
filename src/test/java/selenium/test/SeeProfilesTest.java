package selenium.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class SeeProfilesTest {

    private WebDriver driver = new ChromeDriver();

    @Before
    public void setDriver() {
        driver.get("localhost:4200/profiles");
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
        driver.get("localhost:4200");
        driver.manage().window().maximize();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

    private void goToProfiles() {
        driver.findElement(By.cssSelector("li.nav-item:nth-child(2) > a:nth-child(1)")).click();
    }

    @Test
    public void EmptyProfilesTest() {
        // check if profiles page is empty at startup
        String element = driver.findElement(By.cssSelector(".empty > h4:nth-child(1)")).getText();
        Assert.assertEquals("There're no profiles to see.", element);
    }

    @Test
    public void NameIsSavedCorrectlyTest() {
        createProfile();

        goToProfiles();

        String elementText = driver.findElement(By.cssSelector(".card-columns")).getText();
        String[] tmp = elementText.split("\n");
        String name = tmp[0];

        Assert.assertEquals("john doe", name.toLowerCase());
    }

    @Test
    public void RateProfileTest() {
        createProfile();
        goToProfiles();

        // click on 3rd star in profile
        driver.findElement(By.xpath("//app-rating/div/i[3]")).click();

        Assert.assertEquals("star", driver.findElement(By.xpath("//app-rating/div/i[1]")).getText());
        Assert.assertEquals("star", driver.findElement(By.xpath("//app-rating/div/i[2]")).getText());
        Assert.assertEquals("star", driver.findElement(By.xpath("//app-rating/div/i[3]")).getText());
        Assert.assertEquals("star_border", driver.findElement(By.xpath("//app-rating/div/i[4]")).getText());
        Assert.assertEquals("star_border", driver.findElement(By.xpath("//app-rating/div/i[5]")).getText());
    }
}