package selenium.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoadPage {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("localhost:4200");
    }
}
