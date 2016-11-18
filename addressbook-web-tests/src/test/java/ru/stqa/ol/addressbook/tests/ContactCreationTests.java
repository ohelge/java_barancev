package ru.stqa.ol.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ol.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {


  @Test
  public void contactCreation() {
    app.getNavigationHelper().returnToHomePage();
    int before = app.getContactHelper().getContactCount();

    app.getNavigationHelper().gotoGroupPage(); // smotrim est' li gruppa
    if (app.getGroupHelper().isThereAGroup()) {
      app.getNavigationHelper().gotoContactPage();
      app.getContactHelper().fillContactForm(new ContactData("First name1", "Last name1", "first-name1.last-name1@gmail.com", "Address1", "Group2"), true);
      try {
        Thread.sleep(3000);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    } else { // esli net grupp, to zapolnqem v pole group "[none]"
      app.getNavigationHelper().gotoContactPage();
      app.getContactHelper().fillContactForm(new ContactData("First name0", "Last name0", "first-name1.last-name1@gmail.com", "Address1", "[none]"), true);
    }

    app.getContactHelper().submitContactCreation("//div[@id='content']/form/input[21]");
    app.getNavigationHelper().returnToHomePage();

    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before + 1);
  }


}
