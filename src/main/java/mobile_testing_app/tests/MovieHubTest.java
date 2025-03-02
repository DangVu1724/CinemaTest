package mobile_testing_app.tests;

import static mobile_testing_app.utils.AppiumUtils.scrollUsingCoordinatesDown;
import static mobile_testing_app.utils.AppiumUtils.scrollUsingCoordinatesLeftCustom;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import mobile_testing_app.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.Collections;

public class MovieHubTest extends BaseTest {

    public MovieHubTest(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }

    public void runMovieHubTests() {
        try {
            goToHomePageMenu();
            testMovieHub();
        } catch (Exception e) {
            System.err.println("Error during MovieHub tests: " + e.getMessage());
        }
    }

    private void testMovieHub() {
        String movieHubSectionXpath = "//androidx.recyclerview.widget.RecyclerView[@resource-id='com.cgv.cinema.vn:id/rcv_home']/android.view.ViewGroup[3]";
        boolean sectionFound = false;
        int maxDownSwipes = 5;
        int swipeDownCount = 0;

        while (!sectionFound && swipeDownCount < maxDownSwipes) {
            try {
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(movieHubSectionXpath)));
                sectionFound = true;
                System.out.println("Found MovieHub section.");
            } catch (Exception e) {
                System.out.println("MovieHub section not found. Scrolling down...");
                scrollUsingCoordinatesDown(driver);
                swipeDownCount++;
            }
        }

        if (!sectionFound) {
            System.err.println("MovieHub section not found after " + maxDownSwipes + " swipes.");
            return;
        }


        String targetItemXpath = "(//androidx.recyclerview.widget.RecyclerView[@resource-id='com.cgv.cinema.vn:id/rcv'])[3]/android.view.ViewGroup[2]";
        boolean itemFound = false;
        int maxLeftSwipes = 5;
        int swipeLeftCount = 0;


        try {
            AndroidElement targetItem = (AndroidElement) wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(targetItemXpath))
            );
            targetItem.click();
            System.out.println("Found and clicked target MovieHub item without swiping.");
            return;
        } catch (Exception e) {
            System.out.println("Target item not found initially. Starting to swipe left...");
        }

        while (!itemFound && swipeLeftCount < maxLeftSwipes) {
            try {
                AndroidElement targetItem = (AndroidElement) wait.until(
                        ExpectedConditions.elementToBeClickable(By.xpath(targetItemXpath))
                );
                targetItem.click();
                itemFound = true;
                System.out.println("Found and clicked target MovieHub item.");
            } catch (Exception e) {
                System.out.println("Target item not found. Swiping left...");
                scrollUsingCoordinatesLeftCustom(driver);
                swipeLeftCount++;
            }
        }

        if (!itemFound) {
            System.err.println("Target MovieHub item not found after " + maxLeftSwipes + " swipes.");
        }
    }

    private void testMovieHubDetails() {
        try {
            AndroidElement movieHubItem = (AndroidElement) wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//android.widget.FrameLayout[@resource-id='com.cgv.cinema.vn:id/design_bottom_sheet']/android.view.ViewGroup/android.webkit.WebView")
                    )
            );
            System.out.println("Found MovieHub details.");

            for (int i = 0; i < 3; i++) {
                scrollUsingCoordinatesDown(driver);
            }
            System.out.println("Scrolled down 3 times in MovieHub details.");

            AndroidElement closeBtn = (AndroidElement) wait.until(
                    ExpectedConditions.elementToBeClickable(By.id("com.cgv.cinema.vn:id/close"))
            );
            closeBtn.click();
            System.out.println("Clicked close button in MovieHub details.");

        } catch (Exception e) {
            System.err.println("Error during MovieHub details test: " + e.getMessage());
        }
    }
}