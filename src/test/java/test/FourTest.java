package test;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import resources.Base;

public class FourTest extends Base {
	public WebDriver driver;

	@Test
	public void TestFour() throws IOException, InterruptedException {
		System.out.println("Test Four");

		driver = intializeDriver();
		driver.get("http://tutorialsninja.com/demo/");
		Thread.sleep(2000);

		Assert.assertTrue(false); // intentionaly fails

		
	}

	public void closureBrowser() {
		driver.close();
	}

}
