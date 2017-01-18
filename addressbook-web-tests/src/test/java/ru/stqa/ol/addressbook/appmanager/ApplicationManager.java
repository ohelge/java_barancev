package ru.stqa.ol.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


/**
 * Created by OL on 2016-11-02.
 */
public class ApplicationManager {

  private final Properties properties; //l6_m10
  private WebDriver wd;
  private ContactHelper contactHelper;
  private GroupHelper groupHelper;
  private SessionHelper sessionHelper;
  private String browser;
  private DbHelper dbHelper; //l7_m4 sozdaem pole

  public ApplicationManager(String browser) throws IOException {
    this.browser = browser;
    properties = new Properties();  //l6_m10 sozddaem objekt tipa Properties v konstruktore, a ostal'noe sm. nizhe

  }

  public void init() throws IOException {
    // Gecko driver (needed for working Selenium 3 + Fifefox.v > 48) http://barancev.github.io/geckodriver/
    //String marionetteDriverLocation = "C:\\Tools\\geckodriver.exe"; Or just copy driver to some directory declared in PATH
    //System.setProperty("webdriver.gecko.driver", marionetteDriverLocation);

    String target = System.getProperty("target", "local");//l6_m10 "local" -defoltnoe zna4enie dlq sistemnogo svoistva target (sm. build.gragle)
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target)))); //l6_m10 zagruzhaem properties s reader. target podstavlqetsq vmesto %s

    dbHelper = new DbHelper();//l7_m4 inizializiruem objekt iz novogo klassa. Sm DbHelper

    if (browser.equals (BrowserType.FIREFOX)) {
      wd = new FirefoxDriver();
    } else if (browser.equals (BrowserType.CHROME)) {
      wd = new ChromeDriver();
    } else if (browser.equals (BrowserType.IE)) {
      wd = new InternetExplorerDriver();
    }

    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    //wd.get("http://localhost/addressbook");
    wd.get(properties.getProperty("web_baseUrl")); //l6_m10 Parametrizuem url. sm. file local.properties
    contactHelper = new ContactHelper(wd);
    groupHelper = new GroupHelper(wd);
    sessionHelper = new SessionHelper(wd);
    //sessionHelper.login(new LoginData("admin", "secret"));
    sessionHelper.login(new LoginData(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"))); //l6_m10 Parametrizuem login/pass sm. file local.properties

      }

  public void stop() {
    wd.quit();
  }

  public GroupHelper group() {
    return groupHelper;
  }

  public ContactHelper contact() {
    return contactHelper;
  }

  public SessionHelper goTo() {
    return sessionHelper;
  }

  public DbHelper db() { //l7_m4 sozdaem metod kot vozvrawaet etogo pomownika
    return dbHelper;
  }
}
