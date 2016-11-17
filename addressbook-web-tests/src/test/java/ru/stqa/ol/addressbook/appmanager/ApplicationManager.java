package ru.stqa.ol.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;


/**
 * Created by OL on 2016-11-02.
 */
public class ApplicationManager {

  private WebDriver wd;

  private NavigationHelper navigationHelper;
  private ContactHelper contactHelper;
  private GroupHelper groupHelper;
  private SessionHelper sessionHelper;
  private String browser;

  public ApplicationManager(String browser) {
    this.browser = browser;
  }

  public void init() {
    // Gecko driver (needed for working Selenium 3 + Fifefox.v > 48) http://barancev.github.io/geckodriver/
    //String marionetteDriverLocation = "C:\\Tools\\geckodriver.exe"; Or just copy driver to some directory declared in PATH
    //System.setProperty("webdriver.gecko.driver", marionetteDriverLocation);
    if (browser.equals (BrowserType.FIREFOX)) {
      wd = new FirefoxDriver();
    } else if (browser.equals (BrowserType.CHROME)) {
      wd = new ChromeDriver();
    } else if (browser.equals (BrowserType.IE)) {
      wd = new InternetExplorerDriver();
    }

    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook");
    contactHelper = new ContactHelper(wd);
    groupHelper = new GroupHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    sessionHelper.login(new LoginData("admin", "secret"));
  }

  public void stop() {
    wd.quit();
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public ContactHelper getContactHelper() {
    return contactHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }
}
