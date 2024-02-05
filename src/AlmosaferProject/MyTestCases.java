package AlmosaferProject;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestCases extends Parameters {

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

	@Test(priority = 1, enabled = false)
	public void CheckTheDeafultLanguageIsEnglish() {
		String ExpectedLanaguage = "EN";
		String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang").toUpperCase();
		assertEquals(ActualLanguage, ExpectedLanaguage);

	}

	@Test(priority = 2, enabled = false)
	public void CheckTheDefaultCurrencyIsSAR() throws InterruptedException {
		String ExpectedCurrency = "SAR";
		String ActualCurrency = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']"))
				.getText();
		assertEquals(ActualCurrency, ExpectedCurrency);
	}

	@Test(priority = 3, enabled = false)
	public void CheckContactNumber() throws InterruptedException {
		String ExpectedContactNumber = "+966554400000";
		String ActualContactNumber = driver.findElement(By.tagName("strong")).getText();

		assertEquals(ActualContactNumber, ExpectedContactNumber);
	}

	@Test(priority = 4, enabled = false)
	public void CheckQitafLogo() {
		WebElement theFooter = driver.findElement(By.tagName("footer"));

		System.out.println();

		assertEquals(theFooter.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ekulBa.eYboXF")).isDisplayed(), true);
	}

	@Test(priority = 5, enabled = false)
	public void CheckHotelTabIsNotSelectedByDefault() {

		;
		assertEquals(driver.findElement(By.id("uncontrolled-tab-example-tab-hotels")).getAttribute("aria-selected"),
				"false");

	}

	@Test(priority = 6, enabled = false)
	public void ChickDepartureDateAndRetunDate() {
		LocalDate Today = LocalDate.now();
		int ExpectedDepartureDate = Today.plusDays(1).getDayOfMonth();
		int ExpectedReturnDate = Today.plusDays(2).getDayOfMonth();

		int ActualDepartureDate = Integer.parseInt(driver
				.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-kqlzXE blwiEW'] span[class='sc-cPuPxo LiroG']"))
				.getText());

		int ActualReturnDate = Integer.parseInt(driver
				.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-OxbzP edzUwL'] span[class='sc-cPuPxo LiroG']"))
				.getText());

		assertEquals(ActualDepartureDate, ExpectedDepartureDate);
		assertEquals(ActualReturnDate, ExpectedReturnDate);
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

	@Test(priority = 8)
	public void SwitchToHotelTab() {
		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		HotelTab.click();

		if (driver.getCurrentUrl().contains("ar")) {
			WebElement SearchInputField = driver
					.findElement(By.cssSelector("input[placeholder='البحث عن فنادق أو وجهات']"));
			SearchInputField.sendKeys(CitiesInArabic[RandomCitiesInArabic]);

		} else {
			WebElement SearchInputField = driver
					.findElement(By.cssSelector("input[placeholder='Search for hotels or places']"));
			SearchInputField.sendKeys(CitiesInEnglish[RandomCitiesInEnglish]);
		}
		WebElement List = driver.findElement(By.className("phbroq-4"));
		List.findElements(By.tagName("li")).get(1).click();
	}

	@Test(priority = 9)
	public void SelectRoomRandomly() {
		WebElement Vistor = driver.findElement(By.tagName("select"));
		Select Selctor = new Select(Vistor);
		if (driver.getCurrentUrl().contains("ar")) {
			Selctor.selectByVisibleText("1 غرفة، 1 بالغ، 0 أطفال");
		} else {
			Selctor.selectByVisibleText("1 Room, 1 Adult, 0 Children");
		}

		driver.findElement(By.className("btwWVk")).click();
	}

	@Test(priority = 10)
	public void PageLoaded() throws InterruptedException {
		Thread.sleep(20000);
		String SearchResult = driver.findElement(By.className("sc-kAKrxA")).getText();
		if (driver.getCurrentUrl().contains("ar")) {
			boolean ActualResult = SearchResult.contains("وجدنا");
			assertEquals(ActualResult, true);
		} else {
			boolean ActualResult = SearchResult.contains("found");
			assertEquals(ActualResult, true);
		}

	}

	@Test(priority = 11)
	public void PriceSort() {
		WebElement LowestPriceButton = driver.findElement(By.className("sc-csuNZv"));
		LowestPriceButton.click();
		WebElement HotelContainer = driver.findElement(By.cssSelector(".sc-htpNat.KtFsv.col-9"));
		List<WebElement> PricesList = HotelContainer.findElements(By.className("Price__Value"));
		System.out.println("this is the total price" + PricesList.size());
		String LowestPrice = PricesList.get(0).getText();
		int LowestPriceAsNum = Integer.parseInt(LowestPrice);

		String HighstPrice = PricesList.get(PricesList.size() - 1).getText();
		int HighstPriceAsNum = Integer.parseInt(HighstPrice);

		System.out.println("this is minimum price" + LowestPrice);
		System.out.println("this is maximum price" + HighstPrice);

		assertEquals(HighstPriceAsNum > LowestPriceAsNum, true);

	}

	@AfterTest
	public void myPostTest() {
	}

}
