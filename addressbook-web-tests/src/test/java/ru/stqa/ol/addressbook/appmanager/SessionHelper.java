package ru.stqa.ol.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by OL A546902 on 2016-11-03.
 */
public class SessionHelper {


  private FirefoxDriver wd;

  public SessionHelper(FirefoxDriver wd) {

    this.wd = wd;
  }

  public void login(LoginData loginData) {
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys(loginData.getUsername());
    wd.findElement(By.name("pass")).click();
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys(loginData.getPassword());
    wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
  }
}
