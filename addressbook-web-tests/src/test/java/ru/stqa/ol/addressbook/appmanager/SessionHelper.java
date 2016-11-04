package ru.stqa.ol.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by OL A546902 on 2016-11-03.
 */
public class SessionHelper extends HelperBase{


  public SessionHelper(WebDriver wd) {
    super(wd);
  }

  public void login(LoginData loginData) {
    type(By.name("user"), loginData.getUsername() );
    type(By.name("pass"), loginData.getPassword() );
    click(By.xpath("//form[@id='LoginForm']/input[3]"));
  }
}
