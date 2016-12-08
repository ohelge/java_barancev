package ru.stqa.ol.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ol.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {


  @Test (enabled = true)
  public void contactCreation() {
    app.goTo().contactPage();
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData("Firstname1", "Last name1", "first-name1.last-name1@gmail.com", "Address1", "Group2");
    // ContactData contactNone = new ContactData("First name0", "Last name0", "first-name1.last-name1@gmail.com", "Address1", "[none]");
    app.goTo().groupPage(); // smotrim est' li gruppa
    if (app.group().isThereAGroup()) {
      app.goTo().addNew();
      app.contact().fill(contact, true);
      app.contact().submit("//div[@id='content']/form/input[21]");
      before.add(contact);
      /*try {
        Thread.sleep(2000);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }*/
    } else { // esli net grupp, to zapolnqem v pole group "[none]"
      app.goTo().addNew();
      contact = new ContactData("First name0", "Last name0", "first-name1.last-name1@gmail.com", "Address1", "[none]");
      app.contact().fill(contact, true);
      app.contact().submit("//div[@id='content']/form/input[21]");
      before.add(contact);
    }
    app.goTo().contactPage();

    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() );

    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId() );
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }


}
