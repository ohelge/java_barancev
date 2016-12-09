package ru.stqa.ol.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by A546902 on 2016-11-03.
 */
public class HelperBase {

  protected WebDriver wd;

  public HelperBase(WebDriver wd) {
    this.wd = wd;
  }
  protected void click(By locator) {
    wd.findElement(locator).click();
  }
  protected void type(By locator, String text) {
    click(locator);
    if (text != null) { // from video l3_m05-06
      String existingText = wd.findElement(locator).getAttribute("value");
      if (! text.equals(existingText)) {  //esli ne verno 4to text sovpadaet s uzhe suwestvuuwim. OBS proverka polezna dlq proizvoditel'nosti: Selenium zapolnqet pola posimvol'no
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
      }
    }

  }
  public boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }


  protected boolean isElementPresent(By locator) {
    try {
      wd.findElement(locator);
      return true;
    } catch(NoSuchElementException ex) {
      return false;
    }
  }

  public void groupPage() {
    if (isElementPresent(By.tagName("h1")) //Checking page Groups  with button "New group" then exit from method: return
            && wd.findElement(By.tagName("h1")).getText().equals("Groups")
            && isElementPresent(By.name("new"))) {
      return;
    }
    click(By.linkText("groups"));
  }
  public void contactPage() {
    if (isElementPresent(By.id("maintable"))) {
      return;
    }
    wd.findElement(By.linkText("home")).click();
  }
}
