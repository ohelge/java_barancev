package ru.stqa.ol.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by OL A546902 on 2016-11-03.
 */
public class NavigationHelper {
  private FirefoxDriver wd;

  public NavigationHelper(FirefoxDriver wd) {
    this.wd = wd;
  }

  public void gotoGroupPage() {
    wd.findElement(By.linkText("groups")).click();
  }

  public void gotoContactPage() {
    wd.findElement(By.linkText("add new")).click();
  }
}
