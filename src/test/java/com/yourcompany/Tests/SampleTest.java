package com.yourcompany.Tests;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.UnexpectedException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import com.yourcompany.Pages.GuineaPigPage2;
import com.yourcompany.Pages.LoginPage;
import com.yourcompany.Utilities.Keywords;

public class SampleTest extends TestBase {

	// @Test(dataProvider = "hardCodedBrowsers")
	public void Sample(String browser, String version, String os, Method method)
			throws UnexpectedException, MalformedURLException, InterruptedException {

		// create webdriver session
		this.createDriver(browser, version, os, method.getName());
		WebDriver driver = this.getWebDriver();

		driver.get("https://app-grasshopper.stage.cloudwords.com");

		this.annotate("Visiting Login page...");

		LoginPage login = new LoginPage(driver);

		login.enetrUserName("custom14_pm@gmail.com");
		Thread.sleep(1000);
		login.enterPassWord("password1");
		login.clickLoginBtn();
		Thread.sleep(15000);

	}

	@Test(dataProvider = "hardCodedBrowsers")
	public void verifyCommentInputTest(String browser, String version, String os, Method method)
			throws MalformedURLException, InvalidElementStateException, UnexpectedException, InterruptedException {

		this.createDriver(browser, version, os, method.getName());
		RemoteWebDriver driver = this.getWebDriver();

		this.annotate("Visiting GuineaPig page...");
		GuineaPigPage2 page = GuineaPigPage2.visitPage(driver);

		Thread.sleep(10000);

		this.annotate("Visiting Login page...");

		LoginPage login = new LoginPage(driver);

		login.enetrUserName("custom14_pm@gmail.com");
		Thread.sleep(3000);
		login.enterPassWord("password1");
		Thread.sleep(3000);
		login.clickLoginBtn();
		//Thread.sleep(50000);
		
		WebElement startNewButton = null;
		
		
		int k = 0;

		while (k <= 6) {
			Thread.sleep(30*1000);

			k++;
			
			try {
				startNewButton = driver.findElement(By.cssSelector("button#create-new-button"));

				if (startNewButton != null) {
					break;
				}

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		System.out.println("HOLA  ::  "+startNewButton);	
		System.out.println("PT :: " + driver.getTitle());

		// click on start new

		driver.findElement(By.cssSelector("button#create-new-button")).click();

		Thread.sleep(10000);
		// click on Start new Project

		driver.findElement(By.xpath("//a[text()='Start New Project']")).click();
		Thread.sleep(10000);

		// Enter Proj Name
		driver.findElement(By.cssSelector("#projectName")).sendKeys("KESHAV1234567");
		Thread.sleep(10000);

		// Select Intended Use click
		driver.findElement(By.cssSelector("#intendedUse > option:nth-child(3)")).click();
		Thread.sleep(10000);

		// click on Next
		driver.findElement(By.cssSelector("#nextStepBtnId")).click();
		Thread.sleep(20000);

		// click on Local
		driver.findElement(By.cssSelector("#project-select-STANDARD")).click();
		Thread.sleep(10000);

		// click on upload link
		driver.findElement(By.cssSelector("#selectSourceFileLink")).click();
		Thread.sleep(10000);

		// Upload pdf file
		List<WebElement> forms = driver.findElements((By.className("fileUploadForm")));

		System.out.println("Total Forms" + forms);

		System.out.println("Last Form" + forms.get(forms.size() - 1));

		WebElement uploadContainer = forms.get(forms.size() - 1);

		String formID, id;

		if (uploadContainer != null) {
			id = uploadContainer.getAttribute("id");

			System.out.println("ID is ==" + id);

			formID = id.substring(id.lastIndexOf('_') + 1);
			System.out.println("Form ID :: " + formID);

			driver.setFileDetector(new LocalFileDetector());
			Thread.sleep(5000);

			String FP = new File("Resources/tkt.pdf").getAbsolutePath();

			System.out.println(FP);

			driver.findElement(By.xpath("//*[@id='_fileUploadForm_f" + formID + "_0']/a/input")).sendKeys(FP);
			Thread.sleep(30000);

			WebElement uploadButton = driver.findElement(By.cssSelector("div#" + id + " a.doFileUpload"));
			System.out.println("uploadButton :: " + uploadButton);
			uploadButton.click();
			Thread.sleep(30000);

			WebElement uploadedFileStatus = null;
			int i = 0;

			while (i <= 6) {
				Thread.sleep(5 * 1000);

				i++;
				
				try {
					uploadedFileStatus = driver.findElement(
							By.cssSelector("#_fileUploadLightboxContainer_" + formID + " div.file-item span.uploaded"));

					if (uploadedFileStatus != null) {
						break;
					}

				} catch (Exception e) {
					// TODO: handle exception
				}
			}

			// Click on close

			if (uploadedFileStatus != null) {

				WebElement closeButton = driver.findElement(By.cssSelector("div#" + id + " a.closeFileUpload"));

				closeButton.click();
				
				
				// Select Source Language click
				driver.findElement(By.cssSelector("#sourceLanguage > option:nth-child(9)")).click();
				Thread.sleep(5000);

				// Select Destination Language click
				driver.findElement(By.cssSelector("#languageSelectViewSelector > option:nth-child(17)")).click();
				Thread.sleep(5000);

				//click on next
				driver.findElement(By.cssSelector("#nextStepBtnId")).click();
				Thread.sleep(10000);

				//Select Vendor
				driver.findElement(By.cssSelector("#vendor-type-select > li:nth-child(1) > div > div.vendor-type-logo.external")).click();
				Thread.sleep(10000);
				
				//click on next
				driver.findElement(By.cssSelector("#nextStepBtnId")).click();
				Thread.sleep(5000);
				
				//Select Vendor type 
				driver.findElement(By.cssSelector("#selected-vendors-display > li")).click();
				Thread.sleep(5000);
				System.out.println("Selected Vendor Type");
		
				
				
				////////////////////////////////////
				
				
				
				//Select Submit Day
				
				WebElement bidDueDateInput = driver.findElement(By.cssSelector("#ProjectDatesViewbidDueDate"));
				
				WebElement bidDeliveryDateInput = driver.findElement(By.cssSelector("#ProjectDatesViewdeliveryDueDate"));
				
				WebElement bidDueDateTime = driver.findElement(By.cssSelector("#bidDueDateHour > option:nth-child(7)"));
				
				WebElement bidDeliveryDateTime = driver.findElement(By.cssSelector("#deliveryDueDateHour > option:nth-child(7)"));
		
				bidDueDateInput.click();
				Thread.sleep(5000);
				
				WebElement startBidDate =  driver.findElement(By.cssSelector("#ui-datepicker-div > div.ui-datepicker-group.ui-datepicker-group-last > table > tbody > tr:nth-child(1) > td:nth-child(7)"));
		
				startBidDate.click();
				Thread.sleep(5000);
				bidDueDateTime.click();
				Thread.sleep(5000);
				
				/////////////////// First Date Input completed
				System.out.println("First Date Input completed");
				
				
				
				
				bidDeliveryDateInput.click();
				Thread.sleep(5000);
				
				WebElement endBidDate =  driver.findElement(By.cssSelector("#ui-datepicker-div > div.ui-datepicker-group.ui-datepicker-group-last > table > tbody > tr:nth-child(4) > td:nth-child(1)"));
				
				endBidDate.click();
				Thread.sleep(5000);
				bidDeliveryDateTime.click();
				Thread.sleep(5000);
				
				/////////////////// Second Date Input completed
				System.out.println("Second Date Input completed");
				
				
				driver.findElement(By.cssSelector("#projectNotes")).sendKeys("KESKSKSKKSKSKSKS");
				Thread.sleep(5000);
				
				
				
				
				//click on next
				WebElement nextStepButton = null;
				
				
				int j = 0;

				while (j <= 6) {
					Thread.sleep(1000);

					j++;
					
					try {
						nextStepButton = driver.findElement(By.cssSelector("a#nextStepBtnId"));

						if (nextStepButton != null) {
							break;
						}

					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				
				System.out.println("HOLA  ::  "+nextStepButton);
				
			
				if (nextStepButton != null) {
					nextStepButton.click();
					Thread.sleep(10000);
					System.out.println("Clicked on Next Button");
					
				}
				
				//driver.findElement(By.cssSelector("#nextStepBtnId")).click();
				
				//Click on Finish
				driver.findElement(By.cssSelector("#finish-link")).click();
				Thread.sleep(10000);
				System.out.println("Clicked on Finish Button");

				
				//Click on Accept
				driver.findElement(By.cssSelector("#popup_ok")).click();
				Thread.sleep(10000);
				System.out.println("Clicked on Accept Button");


			}

		} else {
			System.out.println("Element not found ");
		}

		/*
		 * //Logout
		 * driver.findElement(By.cssSelector("#nav-button-container > div")).
		 * click(); Thread.sleep(5000);
		 * 
		 * driver.findElement(By.cssSelector("#logout")).click();
		 * Thread.sleep(5000);
		 * 
		 * 
		 */
	}

}