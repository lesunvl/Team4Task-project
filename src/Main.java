import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Main {

    //nothing to push. Just testing
    public static void main(String[] args) {

        // Step 1 //  Login as administrator
        System.setProperty("webdriver.chrome.driver", "/Users/juliaumar/Desktop/Project/libs/chromedriver");
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://orangehrm-demo-6x.orangehrmlive.com/auth/login");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        // Step 2 //
        driver.findElement(By.xpath("//span[text()='Admin']")).click();
        driver.findElement(By.xpath("//a[@class='collapsible-header waves-effect waves-orange']/span[text()='Announcements']")).click();
        driver.findElement(By.xpath("//a[@id='menu_news_viewNewsList']/span[text()='News']")).click();

        driver.switchTo().frame("noncoreIframe");
        driver.findElement(By.id("list_item_add")).click();
        //---Step 9-Verify news is displayed--
        String header = "Julia Test-" + new Random().nextInt(1000);
        driver.findElement(By.name("news[topic]")).sendKeys(header);
        driver.switchTo().frame("news_description_ifr");
        driver.findElement(By.id("tinymce")).sendKeys("Promotion was awarded to Team4");
        driver.switchTo().parentFrame();

        driver.findElement(By.id("nextBtn")).click();
        driver.findElement(By.cssSelector("[for='news_publish_all']")).click();
        driver.findElement(By.className("publish-btn")).click();
        List<WebElement> newRow = driver.findElements(By.xpath("//tr[@class='dataRaw']/td[2]"));

        for (int i = 0; i < newRow.size(); i++) {
            if (newRow.get(i).getText().equals(header)) {
                Assert.assertTrue(true, "header is found");
                break;
            }

            if (i == newRow.size() - 1) {
                Assert.assertTrue(false, "header is not found");
            }
        }

        driver.quit();
    }
}