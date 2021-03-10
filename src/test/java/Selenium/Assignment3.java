package Selenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;

public class Assignment3 {
	WebDriver driver;

	@BeforeSuite
	public void beforeSuite() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\gunjankumar.singh\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Test(priority = 0)
	public void SwitchTab() throws InterruptedException {
		driver.get("http://openclinic.sourceforge.net/openclinic/home/index.php");
		driver.manage().window().maximize();
		String expectedTitle = "Welcome to OpenClinic : Clinica1";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);

		// Click on Medical Records
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"content\"]/h2[1]/a")).click();
		Thread.sleep(2000);

		// Click on Search Patient
		driver.findElement(By.xpath("//*[@id=\"content\"]/h2[1]/a")).click();
		Thread.sleep(1000);

		// Select firstName
		WebElement field = driver.findElement(By.id("search_type"));
		Thread.sleep(1000);

		Select select = new Select(field);
		select.selectByVisibleText("First Name");
		Thread.sleep(1000);

		// click on search

		driver.findElement(By.id("search_patient")).click();
		Thread.sleep(1000);
		String expectedMessage = "Search Results : Clinica1";
		String actualMessage = driver.getTitle();
		Assert.assertEquals(actualMessage, expectedMessage);

	}

	@Test(priority = 1)
	public void ScreenShot() throws IOException {
		driver.get("https://api.jquery.com/dblclick/");
		driver.manage().window().maximize();

		TakesScreenshot takeScreenShot = (TakesScreenshot) driver;
		File file = takeScreenShot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File("C:\\Users\\gunjankumar.singh\\Documents\\GK\\screenshot1.png"));
	}

	@Test(priority = 2)
	public void Excel() throws IOException {
		File file = new File("C:\\Users\\gunjankumar.singh\\Documents\\Final_Updated_Assignment.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);

		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		for (int i = 0; i < rowCount; i++) {

			Row row = sheet.getRow(i);

			for (int j = 0; j < row.getLastCellNum(); j++) {
				System.out.println(row.getCell(j).getStringCellValue() + "  ");
			}
			System.out.println();
		}

	}

	@Test(priority = 3)
	public void attribute() {

		driver.get("https://accounts.google.com/signup");
		WebElement firstName = driver.findElement(By.id("firstName"));
		firstName.sendKeys("Dummy");
		String attribute1 = firstName.getAttribute("name");
		System.out.println("First Name Attribute is :: " + attribute1);

		WebElement lastName = driver.findElement(By.id("lastName"));
		lastName.sendKeys("User");
		String attribute2 = lastName.getAttribute("name");
		System.out.println("Last Name Attribute is :: " + attribute2);

		WebElement Username = driver.findElement(By.id("username"));
		Username.sendKeys("dummyuser123");
		String attribute3 = Username.getAttribute("name");
		System.out.println("User Name Attribute is :: " + attribute3);

		WebElement Password = driver.findElement(By.xpath("//*[@id=\"passwd\"]/div[1]/div/div[1]/input"));
		Password.sendKeys("Dummy@123");
		String attribute4 = Password.getAttribute("name");
		System.out.println("Password Attribute is :: " + attribute4);

		WebElement confirmPassword = driver
				.findElement(By.xpath("//*[@id=\"confirm-passwd\"]/div[1]/div/div[1]/input"));
		confirmPassword.sendKeys("Dummy@123");
		String attribute5 = confirmPassword.getAttribute("name");

		WebElement nextButton = driver.findElement(By.xpath("//*[@id=\"accountDetailsNext\"]/div/button/div[2]"));
		nextButton.click();

	}

	@Test(priority = 4)
	public void verifyPinCode() {
		driver.get("http://chennaiiq.com/chennai/pincode-by-name.php");
		List<String> Pincode = new ArrayList<String>();

		WebElement table = driver.findElement(By.xpath("/html/body/table/tbody/tr[3]/td[2]/table[1]/tbody"));

		List<WebElement> row = table.findElements(By.tagName("tr"));
		for (WebElement r : row) {
			List<WebElement> col = r.findElements(By.tagName("td"));
			if (col.size() > 2 && col.get(2).getText().equals("Pincode") == false) {
				Pincode.add(col.get(2).getText());
			}
		}
		Set<String> temp = new HashSet<String>(Pincode);

		Assert.assertEquals(Pincode.size(), temp.size());
	}

	@AfterSuite
	public void afterSuite() {
		driver.close();
	}

}
