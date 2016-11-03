package ru.stqa.ol.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.ol.addressbook.model.ContactData;

public class ContactCreation extends TestBase{


  @Test
  public void contactCreation() {
    app.getNavigationHelper().gotoContactPage();
    app.getContactHelper().contactFill(new ContactData("First name1", "\\9", "Last name1", "\\9", "first-name1.last-name1@gmail", "Address1"));
    app.getContactHelper().submitContactCreation();
    app.returnToHomePage();
  }


}
