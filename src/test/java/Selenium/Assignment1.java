package Selenium;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterSuite;

public class Assignment1 {
	WebDriver driver;

	@BeforeSuite
	public void beforeSuite() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\gunjankumar.singh\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Test(priority = 0)
	public void TestDropDown() {
		driver.get("https://www.jquery-az.com/boots/demo.php?ex=63.0_2");

		driver.findElement(By.xpath("//button[contains(@class,'multiselect')]")).click();

		List<WebElement> list = driver
				.findElements(By.xpath("//ul[contains(@class,'multiselect-container')]//li//a//label"));

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getText().contains("Python")) {
				list.get(i).click();
			}
		}

	}

	@Test(priority = 1)
	public void TestDragandDrop() throws InterruptedException {

		driver.get("https://jqueryui.com/droppable/");

		driver.switchTo().frame(0);
		Thread.sleep(1000);
		WebElement source = driver.findElement(By.id("draggable"));
		WebElement target = driver.findElement(By.id("droppable"));

		Actions action = new Actions(driver);
		action.clickAndHold(source).moveToElement(target).release().build().perform();

	}

	@Test(priority = 2)
	public void Scroll() {
		driver.get("http://openclinic.sourceforge.net/openclinic/home/index.php");

		JavascriptExecutor js = (JavascriptExecutor) driver;

		WebElement element = driver.findElement(By.xpath("//*[@id=\"app_info\"]/p[3]/a"));
		js.executeScript("arguments[0].scrollIntoView();", element);
	}

	@Test(priority = 3)
	public void SetSizeOfBrowser() throws InterruptedException {
		driver.get("https://www.facebook.com/");
		driver.manage().window().maximize();

		Dimension dimension = new Dimension(600, 720);
		Thread.sleep(1000);
		driver.manage().window().setSize(dimension);
	}

	@Test(priority = 4)
	public void TextWithoutSendKeys() {
		driver.get("https://www.facebook.com/");

		WebElement email = driver.findElement(By.id("email"));
		WebElement password = driver.findElement(By.id("pass"));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value='dummy@gmail.com'", email);
	}

	@Test(priority = 5)
	public void BrokenLinks() throws MalformedURLException, IOException {
		driver.get("https://www.zlti.com/");
		driver.manage().window().maximize();

		List<WebElement> linksList = driver.findElements(By.tagName("a"));

		List<WebElement> activeLinks = new ArrayList<WebElement>();

		// excluding link that doesn't have href attribute
		for (int i = 0; i < linksList.size(); i++) {
			if (linksList.get(i).getAttribute("href") != null
					&& (!linksList.get(i).getAttribute("href").contains("javascript"))) {
				activeLinks.add(linksList.get(i));
			}
		}

		for (int j = 0; j < activeLinks.size(); j++) {

			HttpURLConnection connection = (HttpURLConnection) new URL(activeLinks.get(j).getAttribute("href"))
					.openConnection();
			connection.connect();
			String response = connection.getResponseMessage();
			connection.disconnect();
			System.out.println(activeLinks.get(j).getAttribute("href") + "--->" + response);
		}

	}

	@Test(priority = 6)
	public void popUp() throws InterruptedException {
		driver.get("https://demoqa.com/alerts");
		driver.manage().window().maximize();
		driver.findElement(By.id("alertButton")).click();
		Thread.sleep(1000);
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	@Test(priority = 7)
	public void googleSearch() throws InterruptedException {

		driver.get("https://www.google.com/");
		driver.findElement(By.name("q")).sendKeys("java");
		Thread.sleep(1000);

		List<WebElement> list = driver
				.findElements(By.xpath("//ul[@role='listbox']//li/descendant::div[@class='sbl1']"));

		System.out.println("Total number of suggestions : " + list.size());

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getText());
			if (list.get(i).getText().contains("javascript tutorial")) {
				list.get(i).click();
				break;
			}
		}
	}

	@AfterSuite
	public void afterSuite() {
		driver.close();
	}

}
