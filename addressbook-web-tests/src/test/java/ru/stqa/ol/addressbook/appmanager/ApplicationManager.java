package ru.stqa.ol.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.ol.addressbook.model.ContactData;

import java.util.concurrent.TimeUnit;

/**
 * Created by OL on 2016-11-02.
 */
public class ApplicationManager {

  FirefoxDriver wd;

  private GroupHelper groupHelper;

  public static boolean isAlertPresent(FirefoxDriver wd) {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  public void init() {
    // Gecko driver (needed for working Selenium 3 + Fifefox.v > 48) http://barancev.github.io/geckodriver/
    //String marionetteDriverLocation = "C:\\Tools\\geckodriver.exe";
    //System.setProperty("webdriver.gecko.driver", marionetteDriverLocation);
    wd = new FirefoxDriver();
    groupHelper = new GroupHelper(wd);
    wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/group.php");
    login(new LoginData("admin", "secret"));
  }

  private void login(LoginData loginData) {
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys(loginData.getUsername());
    wd.findElement(By.name("pass")).click();
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys(loginData.getPassword());
    wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
  }

  public void gotoGroupPage() {
    wd.findElement(By.linkText("groups")).click();
  }

  public void stop() {
    wd.quit();
  }


  public void submitContactCreation() {
    wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
  }

  public void contactFill(ContactData contactData) {
    wd.findElement(By.name("firstname")).click();
    wd.findElement(By.name("firstname")).clear();
    wd.findElement(By.name("firstname")).sendKeys(contactData.getFirstname());
    wd.findElement(By.name("middlename")).click();
    wd.findElement(By.name("middlename")).sendKeys(contactData.getMiddlename());
    wd.findElement(By.name("lastname")).click();
    wd.findElement(By.name("lastname")).clear();
    wd.findElement(By.name("lastname")).sendKeys(contactData.getLastname());
    wd.findElement(By.name("nickname")).click();
    wd.findElement(By.name("nickname")).sendKeys(contactData.getNickname());
    wd.findElement(By.name("email")).click();
    wd.findElement(By.name("email")).clear();
    wd.findElement(By.name("email")).sendKeys(contactData.getEmail());
    wd.findElement(By.name("address2")).click();
    wd.findElement(By.name("address2")).clear();
    wd.findElement(By.name("address2")).sendKeys(contactData.getAddress());
  }

  public void returnToHomePage() {
    wd.findElement(By.linkText("home")).click();
  }
  public void gotoContactPage() {
    wd.findElement(By.linkText("add new")).click();
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }
}
