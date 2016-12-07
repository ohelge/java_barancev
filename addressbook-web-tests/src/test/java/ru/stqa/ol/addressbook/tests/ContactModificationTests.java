package ru.stqa.ol.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ol.addressbook.model.ContactData;
import ru.stqa.ol.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Created by OL A546902 on 2016-11-04.
 */
public class ContactModificationTests extends TestBase {

  @Test (enabled = false)
  public void testContactModification() {

    //int before = app.getContactHelper().getContactCount();
    app.getNavigationHelper().returnToHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    int before2;
    app.getNavigationHelper().gotoGroupPage();

    if (app.getGroupHelper().isThereAGroup()) {
      app.getNavigationHelper().returnToHomePage();
      if (app.getContactHelper().isThereAContact()) { // est' i Group i Contact
        app.getContactHelper().editContact();
        ContactData contact = new ContactData("First name3", "Last name3", "first-name1.last-name1@gmail.com", "Address3", "Group2");
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().updateContact();
        before2 = before.size();
        // before.remove(0);

      } else { // est' Group, net Contact
        app.getNavigationHelper().gotoContactPage();
        ContactData contact = new ContactData("First name1", "Last name1", "first-name1.last-name1@gmail.com", "Address1", "Group2");
        app.getContactHelper().fillContactForm(contact, true);
        app.getContactHelper().submitContactCreation("//div[@id='content']/form/input[21]");
        before2 = before.size()  ;
        before.add(contact);
      }


    } else {
      app.getNavigationHelper().returnToHomePage();
      if (app.getContactHelper().isThereAContact()) { // net Group est' contact
        app.getContactHelper().editContact();
        ContactData contact = new ContactData("First name2", "Last name2", "first-name2.last-name2@gmail.com", "Address2", null);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().updateContact();
        before2 = before.size();
        //before.remove(before.size()-1);

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

    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId() );
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
    //Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
    // try { Thread.sleep(5000); } catch (Exception e) { throw new RuntimeException(e); }

  }
}


