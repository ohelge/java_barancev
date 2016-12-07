package ru.stqa.ol.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.ol.addressbook.appmanager.ApplicationManager;

/**
 * Created by OL on 2016-11-02.
 */
public class TestBase {

  protected static final ApplicationManager app = new ApplicationManager(BrowserType.CHROME); //copy corresponding driver to some directory declared in PATH, c:/Tools
  // dobavlqem static 4tobi app bila obbwei dlq vseh testov: l5_m1

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite
  public void tearDown() {
    app.stop();
  }

}
