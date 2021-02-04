import java.util.*;
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
        // Step 3 //
        Map<String, List<String>> mapNews = new LinkedHashMap<>();

        //header, publish date, role, is file attacked
        for (WebElement parent : driver.findElements(By.className("newsList"))) {
            parent.click();
            String header = parent.findElement(By.id("header")).getText();
            String publishedDate = parent.findElement(By.className("publish-date")).getText().replace("Published on ", "");
            String role = "N/A";
            String fileAttached = "No";

            try {
                driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
                String temp = parent.findElement(By.xpath("//*[contains(text(), ' Team')]")).getText();
                driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            } catch (Exception e) {
            }

            try {
                driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
                parent.findElement(By.tagName("img")).getText();
                fileAttached = "Yes";
                driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            } catch (Exception e) {
            }

            mapNews.put(header, Arrays.asList(publishedDate, role, fileAttached));
            parent.click();
        }

        for (Map.Entry<String, List<String>> map : mapNews.entrySet()) {
            System.out.println(map.getKey() + " | " + map.getValue());
        }
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