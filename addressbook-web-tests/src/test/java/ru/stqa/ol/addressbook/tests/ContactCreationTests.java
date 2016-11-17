package ru.stqa.ol.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ol.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{


  @Test
  public void contactCreation() {
    app.getNavigationHelper().returnToHomePage();
    int before = app.getContactHelper().getContactCount();

    app.getNavigationHelper().gotoContactPage();
    app.getContactHelper().fillContactForm(new ContactData("First name1", "Last name1", "first-name1.last-name1@gmail.com", "Address1", "Group0") , true);
    try { Thread.sleep(5000l); } catch (Exception e) { throw new RuntimeException(e); }
    app.getContactHelper().submitContactCreation("//div[@id='content']/form/input[21]");
    app.getNavigationHelper().returnToHomePage();

    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before + 1);
  }


}
