package com.yourcompany.Pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;




public class LoginPage{


  public WebDriver driver;
    

  @FindBy(how = How.ID, using = "j_username")
  private WebElement eleUsrName;

  @FindBy(how = How.ID, using = "j_password")
  private WebElement elePassword;

  @FindBy(how = How.ID, using = "login-button")
  private WebElement loginButton;

  @FindBy(how = How.ID, using = "j_remember_email")
  private WebElement eleRememberMyEmail;

  @FindBy(how = How.ID, using = "forgot-password-link")
  private WebElement eleForgotPwdLink;

  @FindBy(how = How.XPATH, using = "//*[@id='loginContainer']/p[2]/a")
  private WebElement eleSignLinkedAcc;

  @FindBy(how = How.XPATH, using = "//*[@id='loginContainer']/div[1]")
  private WebElement errorMsg;

  /**
   * Page Objects related methods
   */

  /*@Override
  protected void isLoaded() throws Error {

    PageLoadHelper.isLoaded().isElementIsVisible(driver, eleUsrName)
        .isElementIsVisible(driver, elePassword).isElementIsClickable(driver, loginButton);
  }*/
  
  public LoginPage(WebDriver driver) {
	  
	  System.out.println("driver reached to login page is   "+driver);
    this.driver = driver;
    PageFactory.initElements(driver, this);

  }

  public boolean isPasswordHidden() {
    if (elePassword.getAttribute("type").equals("password"))
      return true;
    else
      return false;
  }

  public String alertText() {
    return errorMsg.getText();

  }

  public boolean isAlertDisp() {
    return errorMsg.isDisplayed();
  }

  public LoginPage enterPassWord(String passWordValue) {
    //elePassword.clear();
    elePassword.sendKeys(passWordValue);
    return this;
  }

  public void clickLoginBtn() throws InterruptedException {
	Thread.sleep(5000);
    loginButton.click();
  }

  public LoginPage enetrUserName(String userNameValue) {
    //eleUsrName.clear();
    eleUsrName.sendKeys(userNameValue);
    return this;
  }

/*  @Override
  protected void load() {

  }*/

}
