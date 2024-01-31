package AlmosaferProject;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestCases extends Parameters{

	@BeforeTest
	public void mySetup() {

		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		WebElement popUPScreen = driver.findElement(By.className("sc-iBmynh"));

		if (popUPScreen.isDisplayed()) {
			WebElement SARBUTTOn = driver.findElement(By.className("cta__saudi"));
			SARBUTTOn.click();

		}

	}

	@Test(priority = 1)
	public void CheckTheDeafultLanguageIsEnglish() {
		String ExpectedLanaguage = "EN";
		String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang").toUpperCase();
		assertEquals(ActualLanguage, ExpectedLanaguage);

	}

	@Test(priority = 2)
	public void CheckTheDefaultCurrencyIsSAR() throws InterruptedException {
		String ExpectedCurrency = "SAR";
		String ActualCurrency = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']"))
				.getText();
		assertEquals(ActualCurrency, ExpectedCurrency);
	}

	@Test(priority = 3)
	public void CheckContactNumber() throws InterruptedException {
		String ExpectedContactNumber = "+966554400000";
		String ActualContactNumber = driver.findElement(By.tagName("strong")).getText();

		assertEquals(ActualContactNumber, ExpectedContactNumber);
	}

	@Test(priority = 4)
	public void CheckQitafLogo() {
		WebElement theFooter = driver.findElement(By.tagName("footer"));

		System.out.println();

		assertEquals(theFooter.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ekulBa.eYboXF")).isDisplayed(), true);
	}

	@Test(priority = 5)
	public void CheckHotelTabIsNotSelectedByDefault() {

		;
		assertEquals(driver.findElement(By.id("uncontrolled-tab-example-tab-hotels")).getAttribute("aria-selected"),
				"false");

	}

	@Test(priority = 7)
	public void RandomMethodToChangeTheLanguage() {
		Random rand = new Random();

		int randomIndexForTheWebSite = rand.nextInt(Websites.length);

		driver.get(Websites[randomIndexForTheWebSite]);

		if (driver.getCurrentUrl().contains("ar")) {
			String ExpectedLang = "ar";

			String ActualLang = driver.findElement(By.tagName("html")).getAttribute("lang");

			assertEquals(ActualLang, ExpectedLang);
		} else {
			String ExpectedLang = "en";

			String ActualLang = driver.findElement(By.tagName("html")).getAttribute("lang");

			assertEquals(ActualLang, ExpectedLang);

		}

	}

	@AfterTest
	public void myPostTest() {
	}

}