package ru.stqa.ol.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.ol.addressbook.appmanager.ApplicationManager;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by OL on 2016-11-02.
 */
public class TestBase {

  protected static ApplicationManager app; // dobavlqem static 4tobi app bila obbwei dlq vseh testov: l5_m1
  //copy corresponding driver to some directory declared in PATH, c:/Tools
  Logger logger = LoggerFactory.getLogger(TestBase.class); //l6_m11 perenesli Logger iz klassa GroupCreationTests, menqem parametr na TestBase

  @BeforeSuite
  public void setUp() throws Exception {
    app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME)); //OBS
    //l6_m10 Piwem v Configuration testa e.g.GroupCreation v pole "VM options": -Dbrowser=firefox. BrowserType.CHROME-dlq default. Inicializiruem vnuri metoda, a deklariruem viwe na urovne klassa
    app.init();

  }

  @AfterSuite(alwaysRun = true) //l6_m11 4tobi browser ostanavlivalsq vsegda
  public void tearDown() {
    app.stop();
  }


  @BeforeMethod
  public void logTestStart(Method m, Object[] p) { //l6_m11 peredaem parametr tipa metod iz paketa java.lang.reflect. 4tobi pisalas' info o parametrah metoda: vvodim parametr Object
    logger.info("Start test " + m.getName() + " with parameters " + Arrays.asList(p)); //vivodim parametri kak spisok, 4tobi krasivo: Arrays.asList(p)
  }

  @AfterMethod(alwaysRun = true) //l6_m11 alwaysRun: esli owibka to @AfterMethod vipolnit'sq
  public void logTestStop(Method m) {
    logger.info("Stop test " + m.getName());
  }

}
