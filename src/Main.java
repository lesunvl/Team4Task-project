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


    }
}