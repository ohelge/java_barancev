package ru.stqa.ol.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ol.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by OL A546902 on 2016-11-04.
 */
public class ContactModificationTests extends TestBase {

  @Test (enabled = true)
  public void testContactModification() {

    //int before = app.contact().getContactCount();
    app.goTo().contactPage();
    List<ContactData> before = app.contact().list();
    int before2;

    app.goTo().groupPage();
    if (app.group().isThereAGroup()) {
      app.goTo().contactPage();
      if (app.contact().isThereAContact()) { // est' i Group i Contact
        app.contact().edit();
        ContactData contact = new ContactData("First name3", "Last name3", "first-name1.last-name1@gmail.com", "Address3", "Group2");
        app.contact().fill(contact, false);
        app.contact().updateContact();
        before2 = before.size();
        // before.remove(0);

      } else { // est' Group, net Contact
        app.goTo().addNew();
        ContactData contact = new ContactData("First name1", "Last name1", "first-name1.last-name1@gmail.com", "Address1", "Group2");
        app.contact().fill(contact, true);
        app.contact().submit("//div[@id='content']/form/input[21]");
        before2 = before.size() +1 ;
        before.add(contact);
      }


    } else {
      app.goTo().contactPage();
      if (app.contact().isThereAContact()) { // net Group est' contact
        app.contact().edit();
        ContactData contact = new ContactData("First name2", "Last name2", "first-name2.last-name2@gmail.com", "Address2", null);
        app.contact().fill(contact, false);
        app.contact().updateContact();
        before2 = before.size();

      } else { //ni group ni contact netu
        app.goTo().addNew();
        app.contact().fill(new ContactData("First name4", "Last name4", "first-name1.last-name1@gmail.com", "Address1", "[none]"), true);
        app.contact().submit("//div[@id='content']/form/input[21]");
        before2 = before.size() +1 ;
        before.add(new ContactData("First name4", "Last name4", "first-name1.last-name1@gmail.com", "Address1", "[none]"));
      }
    }
    app.goTo().contactPage();
    try {
      Thread.sleep(2000);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    List<ContactData> after = app.contact().list();
    Assert.assertEquals( after.size(), before2);

    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId() );
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
    //Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
    // try { Thread.sleep(5000); } catch (Exception e) { throw new RuntimeException(e); }

  }
}


