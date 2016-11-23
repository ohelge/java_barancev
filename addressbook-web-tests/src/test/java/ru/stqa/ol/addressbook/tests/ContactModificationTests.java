package ru.stqa.ol.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ol.addressbook.model.ContactData;
import ru.stqa.ol.addressbook.model.GroupData;

import java.util.List;

/**
 * Created by OL A546902 on 2016-11-04.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {

    //int before = app.getContactHelper().getContactCount();
    List<ContactData> before = app.getContactHelper().getContactList();
    int before2;
    app.getNavigationHelper().gotoGroupPage();

    if (app.getGroupHelper().isThereAGroup()) {
      app.getNavigationHelper().returnToHomePage();
      if (app.getContactHelper().isThereAContact()) { // est' i Group i Contact
        app.getContactHelper().editContact();
        app.getContactHelper().fillContactForm(new ContactData("First name3", "Last name3", "first-name1.last-name1@gmail.com", "Address1", "Group2"), false);
        app.getContactHelper().updateContact();
        before2 = before.size();
      } else { // est' Group, net Contact
        app.getNavigationHelper().gotoContactPage();
        app.getContactHelper().fillContactForm(new ContactData("First name1", "Last name1", "first-name1.last-name1@gmail.com", "Address1", "Group2"), true);
        app.getContactHelper().submitContactCreation("//div[@id='content']/form/input[21]");
        before2 = before.size() +1 ;
      }


    } else {
      app.getNavigationHelper().returnToHomePage();
      if (app.getContactHelper().isThereAContact()) { // net Group est' contact
        app.getContactHelper().editContact();
        app.getContactHelper().fillContactForm(new ContactData("First name2", "Last name2", "first-name1.last-name1@gmail.com", "Address1", "[none]"), false);
        app.getContactHelper().updateContact();
        before2 = before.size();
      } else { //ni group ni contact netu
        app.getNavigationHelper().gotoContactPage();
        app.getContactHelper().fillContactForm(new ContactData("First name4", "Last name4", "first-name1.last-name1@gmail.com", "Address1", "[none]"), true);
        app.getContactHelper().submitContactCreation("//div[@id='content']/form/input[21]");
        before2 = before.size() +1 ;
      }
    }
    app.getNavigationHelper().returnToHomePage();
    // int after = app.getContactHelper().getContactCount();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before2);

    // try { Thread.sleep(5000); } catch (Exception e) { throw new RuntimeException(e); }

  }
}


