package ru.stqa.ol.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ol.addressbook.model.ContactData;
import ru.stqa.ol.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;

/**
 * Created by A546902 on 2016-11-04.
 */
public class ContactDeletion extends TestBase {

  @Test(enabled = true)
  public void contactDeletion() {
    app.goTo().contactPage();
    if (!app.contact().isThereAContact()) {
      app.goTo().groupPage();
      if (app.group().isThereAGroup()) {
        app.contact().create(new ContactData()
                .withFirstname("First name1")
                .withLastname("Last name1")
                .withEmail("first-name1.last-name1@gmail.com")
                .withAddress("Address1").withGroup("Group2") );
         } else {
        app.contact().create(new ContactData()
                .withFirstname("First name1")
                .withLastname("Last name1")
                .withEmail("first-name1.last-name1@gmail.com")
                .withAddress("Address1").withGroup("[none]") );
      }
    }

    app.goTo().contactPage();
    Set<ContactData> before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);

    app.goTo().contactPage();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedContact);
    Assert.assertEquals(after, before);


  }
}
