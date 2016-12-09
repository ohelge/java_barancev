package ru.stqa.ol.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ol.addressbook.model.ContactData;
import java.util.Set;

public class ContactCreationTests extends TestBase {


  @Test (enabled = true)
  public void contactCreation() {
    app.goTo().contactPage();
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("First name1").withGroup("Group2");
    app.contact().create(contact);

    app.goTo().contactPage();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt() );
    before.add(contact);

    Assert.assertEquals(before, after);

    /*app.goTo().groupPage(); // smotrim est' li gruppa
    if (app.group().isThereAGroup()) {
      app.goTo().addNew();
      app.contact().fill(contact, true);
      app.contact().submit("//div[@id='content']/form/input[21]");
      before.add(contact);
      *//*try {
        Thread.sleep(2000);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }*//*
    } else { // esli net grupp, to zapolnqem v pole group "[none]"
      app.goTo().addNew();
      contact = new ContactData().withGroup("[none]");
      app.contact().fill(contact, true);
      app.contact().submit("//div[@id='content']/form/input[21]");
      before.add(contact);
    }*/




  }


}
