package com.yourcompany.Utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class Keywords{
	
	
	// Return Current Date
		public static String DateReturn() {
			String pattern = "yyyy-MM-dd";

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("da", "DK"));
			String date = simpleDateFormat.format(new Date());
			return date;
		}
	
		
		public static void selectDate(String date, WebDriver driver) throws ParseException, InterruptedException {
		    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		    Date dateToBeSelected = df.parse(date);
		    
		    System.out.println("date to be selected  "+dateToBeSelected);
		    Date currentDate = new Date();
		    String monthYearDisplayed = driver
		        .findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/div/div")).getText();
		    System.out.println(monthYearDisplayed);
		    
		    
		    String month = new SimpleDateFormat("MMMM").format(dateToBeSelected);
		    String year = new SimpleDateFormat("yyyy").format(dateToBeSelected);
		    String day = new SimpleDateFormat("d").format(dateToBeSelected);
		    String monthYearToBeSelected = month + " " + year;
		    System.out.println(monthYearToBeSelected);
		    System.out.println("day to be selected "+day);
		    while (true) {
		      if (monthYearToBeSelected.equals(monthYearDisplayed)) {
		        driver.findElement(By.xpath("//a[text()='" + day + "']")).click();
		        System.out.println("Found and selected");
		        break;

		      } else {
		        if (dateToBeSelected.after(currentDate)) {
		          driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[2]/div/a/span")).click();
		          //CommonUtil.Wait(5, driver);
		          Thread.sleep(5000);
		        } else {
		          driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/div/a/span")).click();
		          //CommonUtil.Wait(5, driver);
		          Thread.sleep(5000);
		        }

		      }
		      monthYearDisplayed = driver
		          .findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/div/div")).getText();
		    }

		  }

}