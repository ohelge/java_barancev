package ru.stqa.ol.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.ol.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{


  @Test
  public void contactCreation() {
    app.getNavigationHelper().gotoContactPage();
    app.getNavigationHelper().returnToHomePage();
  }


}
