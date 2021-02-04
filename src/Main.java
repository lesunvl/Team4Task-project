import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    //nothing to push. Just testing
    public static void main(String[] args) {

        // Step 1 //  Login as administrator
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\17173\\OneDrive\\Documents\\For Learning\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://orangehrm-demo-6x.orangehrmlive.com/auth/login");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        // Step 2 //
        driver.findElement(By.xpath("//span[text()='Admin']")).click();
        driver.findElement(By.xpath("//a[@class='collapsible-header waves-effect waves-orange']/span[text()='Announcements']")).click();
        driver.findElement(By.xpath("//a[@id='menu_news_viewNewsList']/span[text()='News']")).click();

//        Alert alert = driver.switchTo().alert();
//        alert.accept();

        // step 4 A // Add News Item

        driver.switchTo().frame("noncoreIframe");
        driver.findElement(By.name("news[topic]")).sendKeys("Congrats Team4");

        // Step 4 B // Switch to description frame

        driver.switchTo().frame("news_description_ifr");
        driver.findElement(By.id("tinymce")).sendKeys("Promotion was awarded to Team4");

        // Step 5 // click NEXT


        driver.switchTo().defaultContent();
        driver.switchTo().frame("noncoreIframe");
        driver.findElement(By.xpath("//button[@btn-type='new']")).click();


        // Step 6 // Check Publish to "All user roles" option

        Thread.sleep(3000);
        driver.switchTo().defaultContent();
        driver.switchTo().frame("noncoreIframe");
        driver.findElement(By.xpath("//label[@for='news_publish_all']")).click();

        // Step 7 // Publish

        driver.findElement(By.xpath("//button[@class='modal-action waves-effect action-btn btn right cancel-btn publish-btn']")).click();


    }
}