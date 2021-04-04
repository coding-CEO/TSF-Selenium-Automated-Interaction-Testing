import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.Utils;

import java.util.List;

public class Main {

    private static JavascriptExecutor jsExecutor;
    private static final String navButtonsXPath = "/html/body/div[1]/div/div[2]/nav/div[2]/nav/ul";

    public static void main(String[] args) {

        // Setup basic Things
        System.setProperty("webdriver.chrome.driver", "src/utils/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        jsExecutor = (JavascriptExecutor) driver;

        try {
            driver.manage().window().maximize();

            // Load Website
            driver.get("https://www.thesparksfoundationsingapore.org/");

            // Testing begins
            Utils.scroll(jsExecutor, 600);
            testYoutubeVideoInteraction(driver); //Test 1)

            Utils.scroll(jsExecutor, -600);
            testNavButtons(driver); //Test 2)

            // Testing Done !
            System.out.println("Interaction Testing Successful !");

            Thread.sleep(3000); // wait before closing program

        }catch (Exception e) {
            System.out.println("Program Crashed !");
            System.out.println("Reason - " + e.getMessage());

        }finally {
            driver.quit();
        }
    }

    private static void testYoutubeVideoInteraction(WebDriver driver) throws Exception {
        WebElement introYoutubeVideo = driver.findElement(By.id("youtube-video"));
        introYoutubeVideo.click(); // Start Youtube video
        Thread.sleep(5000); // wait for some seconds
        introYoutubeVideo.click(); // Pause Youtube video
        System.out.println("Test 1) Intro Youtube Video interacting properly");
    }

    private static void testNavButtons(WebDriver driver) throws Exception {
        String navButtonsPath = navButtonsXPath + "/li";
        List<WebElement> navButtons = driver.findElements(By.xpath(navButtonsPath));

        for(WebElement navButton : navButtons) {
            navButton.click();
            Thread.sleep(1000);
        }

        // Last button in Contact Page.
        // So, we should be in contact page now
        Utils.scroll(jsExecutor, 450);
        Thread.sleep(1500);

        driver.navigate().back();
        System.out.println("Test 2) Nav Buttons are interacting properly");
    }
}
