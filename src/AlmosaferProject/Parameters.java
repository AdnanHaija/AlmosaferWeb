package AlmosaferProject;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Parameters {

	WebDriver driver = new ChromeDriver();
	String URL = "https://global.almosafer.com/en";
	String[] Websites = { "https://global.almosafer.com/en", "https://global.almosafer.com/ar" };

	Random rand = new Random();

	String[] CitiesInEnglish = { "dubai", "riyadh", "jeddah" };
	int RandomCitiesInEnglish = rand.nextInt(CitiesInEnglish.length);

	String[] CitiesInArabic = { "دبي", "جدة" };
	int RandomCitiesInArabic = rand.nextInt(CitiesInArabic.length);

}
