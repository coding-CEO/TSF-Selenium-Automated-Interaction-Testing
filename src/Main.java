import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import utils.Utils;

import java.time.Duration;
import java.util.List;

public class Main {

    private static JavascriptExecutor jsExecutor;
    private static Actions action;
    private static final String navButtonsXPath = "/html/body/div[1]/div/div[2]/nav/div[2]/nav/ul";

    public static void main(String[] args) {

        // Setup basic Things
        System.setProperty("webdriver.chrome.driver", "src/utils/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        jsExecutor = (JavascriptExecutor) driver;
        action = new Actions(driver);

        try {
            driver.manage().window().maximize();

            // Load Website
            driver.get("https://www.thesparksfoundationsingapore.org/");

            // Testing begins
            startTesting(driver);

            // Testing Done !
            System.out.println("Interaction Testing Successful !");

            Thread.sleep(5000); // wait before closing program

        }catch (Exception e) {
            System.out.println("Program Crashed !");
            System.out.println("Reason - " + e.getMessage());

        }finally {
            driver.quit();
        }
    }

    private static void startTesting(WebDriver driver) throws Exception {
        // Test 1)
        Utils.scroll(jsExecutor, 600);
        testYoutubeVideoInteraction(driver);

        // Test 2)
        Utils.scroll(jsExecutor, -600);
        testDropDownNavButtons(driver);

        // Test 3)
        testContactUsPage(driver);
    }

    private static void testYoutubeVideoInteraction(WebDriver driver) throws Exception {
        WebElement introYoutubeVideo = driver.findElement(By.id("youtube-video"));
        introYoutubeVideo.click(); // Start Youtube video
        Thread.sleep(5000); // wait for some seconds
        introYoutubeVideo.click(); // Pause Youtube video
        System.out.println("Test 1) Intro Youtube Video interacting properly");
    }

    private static void testDropDownNavButtons(WebDriver driver) throws Exception {
        String navButtonsPath = navButtonsXPath + "/li[contains(@class, 'dropdown')]";
        List<WebElement> navButtons = driver.findElements(By.xpath(navButtonsPath));

        for(WebElement navButton : navButtons) {
            navButton.click();
            Thread.sleep(1000);
        }

        System.out.println("Test 2) Drop Down Nav Buttons are interacting properly");
    }

    private static void testContactUsPage(WebDriver driver) throws Exception {
        String contactPageNavElementXPath = navButtonsXPath + "/li[last()]";
        WebElement contactPageNavElement = driver.findElement(By.xpath(contactPageNavElementXPath));
        contactPageNavElement.click();

        // Now we are in the contact us page
        Utils.scroll(jsExecutor, 500);
        Thread.sleep(3000);

        WebElement mapDivElement = driver.findElement(By.className("map-agileits"));

        action.doubleClick(mapDivElement).perform();
        Thread.sleep(2000);
        action.doubleClick(mapDivElement).perform();
        Thread.sleep(3000);

        driver.navigate().back();

        System.out.println("Test 3) Contact Us page interacting properly");
    }
}
