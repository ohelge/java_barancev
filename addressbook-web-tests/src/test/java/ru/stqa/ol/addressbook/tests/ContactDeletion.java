package ru.stqa.ol.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by A546902 on 2016-11-04.
 */
public class ContactDeletion extends TestBase {

  @Test
  public void contactDeletion(){
    app.getNavigationHelper().returnToHomePage();
    app.getContactHelper().editContact();
    app.getContactHelper().deleteContact();
    app.getNavigationHelper().returnToHomePage();
  }
}
