package ru.stqa.ol.addressbook.tests;

import org.testng.annotations.Test;

import ru.stqa.ol.addressbook.model.ContactData;

public class ContactCreation extends TestBase{


  @Test
  public void contactCreation() {
    app.gotoContactPage();
    app.contactFill(new ContactData("First name1", "\\9", "Last name1", "\\9", "first-name1.last-name1@gmail", "Address1"));
    app.submitContactCreation();
    app.returnToHomePage();
  }


}
