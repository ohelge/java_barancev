package ru.stqa.ol.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.stqa.ol.addressbook.appmanager.ApplicationManager;

/**
 * Created by OL on 2016-11-02.
 */
public class TestBase {


  @BeforeMethod
  public void setUp() throws Exception {
    app.init();
  }

  @AfterMethod
  public void tearDown() {
    app.stop();
  }

}
