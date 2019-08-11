import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoadPageTest {

    private WebDriver driver = new ChromeDriver();

    @Before
    public void setDriver() {
        driver.get("localhost:4200");
        driver.manage().window().maximize();
    }

    @After
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void LoadPageTitleTest() {
        // Check if page title is correct
        Assert.assertEquals("ExpApp", driver.getTitle());
    }
}
