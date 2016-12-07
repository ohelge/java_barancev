package ru.stqa.ol.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ol.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {


  @Test (enabled = false)
  public void contactCreation() {
    app.goTo().contactPage();
    // int before = app.contact().getContactCount();
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData("First name1", "Last name1", "first-name1.last-name1@gmail.com", "Address1", "Group2");
    // ContactData contactNone = new ContactData("First name0", "Last name0", "first-name1.last-name1@gmail.com", "Address1", "[none]");
    app.goTo().groupPage(); // smotrim est' li gruppa
    if (app.group().isThereAGroup()) {
      app.goTo().addNew();
      app.contact().fill(contact, true);
      try {
        Thread.sleep(2000);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    } else { // esli net grupp, to zapolnqem v pole group "[none]"
      app.goTo().addNew();
      contact = new ContactData("First name0", "Last name0", "first-name1.last-name1@gmail.com", "Address1", "[none]");
      app.contact().fill(contact, true);
    }

    app.contact().submit("//div[@id='content']/form/input[21]");
    app.goTo().contactPage();

    // int after = app.contact().getContactCount();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

 /*   int max = 0;
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
    Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
*/
    before.add(contact);

    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId() );
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }


}
