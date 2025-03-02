package mobile_testing_app;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import mobile_testing_app.tests.BookingTest;
import mobile_testing_app.tests.LoginTest;
import mobile_testing_app.tests.LogoutTest;
import mobile_testing_app.tests.MovieHubTest;
import mobile_testing_app.tests.MovieListTest;
import mobile_testing_app.tests.ProfileTest;
import mobile_testing_app.tests.TicketTest;

public class Assignment {
	private final AndroidDriver<AndroidElement> driver;
	private final WebDriverWait wait;

	public Assignment(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 10); // Timeout 10s
	}

	public void runTest() {
		try {

			// Run test Login
//			LoginTest loginTest = new LoginTest(driver);
//			loginTest.runLoginTests();
//			System.out.println("Login tests completed.");

			// Run test Booking
//			BookingTest bookingTest = new BookingTest(driver);
//			bookingTest.runBookingTests();
//			System.out.println("Booking tests completed.");

			//Run test MovieList
			MovieListTest movieListTest = new MovieListTest(driver);
			movieListTest.runMovieListTests();
			System.out.println("MovieList tests completed.");

			// Run test Profile
			ProfileTest profileTest = new ProfileTest(driver);
			profileTest.runProfileTests();
			System.out.println("Profile tests completed.");

			// Run test Ticket
			TicketTest ticketTest = new TicketTest(driver);
			ticketTest.runTicketTests();
			System.out.println("Ticket tests completed.");

			// Run test MovieHub
			MovieHubTest movieHubTest = new MovieHubTest(driver);
			movieHubTest.runMovieHubTests();
			System.out.println("MovieHub tests completed.");

			// Run test logout
			LogoutTest logoutTest = new LogoutTest(driver);
			logoutTest.runLogoutTests();
			System.out.println("Logout tests completed.");
		} catch (Exception e) {
			System.err.println("Error during test execution: " + e.getMessage());
		}
	}

}






