package ru.stqa.ol.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.ol.addressbook.appmanager.ApplicationManager;

/**
 * Created by OL on 2016-11-02.
 */
public class TestBase {

  protected static ApplicationManager app; // dobavlqem static 4tobi app bila obbwei dlq vseh testov: l5_m1
  //copy corresponding driver to some directory declared in PATH, c:/Tools

  @BeforeSuite
  public void setUp() throws Exception {
    //l6_m10 Piwem v Configuration testa e.g.GroupCreation v pole "VM options": -Dbrowser=firefox. BrowserType.CHROME-dlq default. Inicializiruem vnuri metoda, a deklariruem viwe na urovne klassa
    app.init();

  }

  @AfterSuite
  public void tearDown() {
    app.stop();
  }

}
