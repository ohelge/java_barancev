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

  private FirefoxDriver wd;

  private NavigationHelper navigationHelper;
  private ContactHelper contactHelper;
  private GroupHelper groupHelper;
  private SessionHelper sessionHelper;

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
    wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/group.php");
    contactHelper = new ContactHelper(wd);
    groupHelper = new GroupHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    sessionHelper.login(new LoginData("admin", "secret"));
  }

  public void stop() {
    wd.quit();
  }


  public void returnToHomePage() {
    wd.findElement(By.linkText("home")).click();
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public ContactHelper getContactHelper() {
    return contactHelper;
  }

  public void submitContactCreation() {
    contactHelper.submitContactCreation();
  }

  public void contactFill(ContactData contactData) {
    contactHelper.contactFill(contactData);
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }
}
