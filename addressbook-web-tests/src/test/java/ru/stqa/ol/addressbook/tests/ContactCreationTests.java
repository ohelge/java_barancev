package ru.stqa.ol.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ol.addressbook.model.ContactData;
import ru.stqa.ol.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {


  @Test
  public void contactCreation() {
    app.getNavigationHelper().returnToHomePage();
    // int before = app.getContactHelper().getContactCount();
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData("First name1", "Last name1", "first-name1.last-name1@gmail.com", "Address1", "Group2");
    // ContactData contactNone = new ContactData("First name0", "Last name0", "first-name1.last-name1@gmail.com", "Address1", "[none]");
    app.getNavigationHelper().gotoGroupPage(); // smotrim est' li gruppa
    if (app.getGroupHelper().isThereAGroup()) {
      app.getNavigationHelper().gotoContactPage();
      app.getContactHelper().fillContactForm(contact, true);
      try {
        Thread.sleep(2000);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    } else { // esli net grupp, to zapolnqem v pole group "[none]"
      app.getNavigationHelper().gotoContactPage();
      contact = new ContactData("First name0", "Last name0", "first-name1.last-name1@gmail.com", "Address1", "[none]");
      app.getContactHelper().fillContactForm(contact, true);
    }

    app.getContactHelper().submitContactCreation("//div[@id='content']/form/input[21]");
    app.getNavigationHelper().returnToHomePage();

    // int after = app.getContactHelper().getContactCount();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    int max = 0;
    for (ContactData c : after) {
      if (c.getId() > max) {
        max = c.getId();
      }
    }
    Comparator<? super ContactData> byId2 = new Comparator<ContactData>() {
      @Override
      public int compare(ContactData o1, ContactData o2) {
        return Integer.compare(o1.getId(), o2.getId());
      }
    };
    int max1 = after.stream().max(byId2).get().getId();
    contact.setId(max1);
    // Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));

    before.add(contact);

    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId() );
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }


}
