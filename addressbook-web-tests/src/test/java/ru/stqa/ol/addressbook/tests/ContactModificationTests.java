package ru.stqa.ol.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ol.addressbook.model.ContactData;
import ru.stqa.ol.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * Created by OL A546902 on 2016-11-04.
 */
public class ContactModificationTests extends TestBase {

  @Test(enabled = true)
  public void testContactModification() {

    app.goTo().contactPage();
    if (!app.contact().isThereAContact()) {
      app.contact().create(new ContactData()
              .withFirstname("First name1")
              .withLastname("Last name1")
              .withEmail("first-name1.last-name1@gmail.com"));
    }
    app.goTo().contactPage();
    Set<ContactData> before = app.contact().all();

    ContactData modifiedContact = before.iterator().next(); //l5_m5: vozvrawaem pervii popavwiisq element mnojestva
    ContactData contact = new ContactData()
            .withFirstname("First name1")
            .withLastname("Last name1")
            .withEmail("first-name1.last-name1@gmail.com");
    app.contact().modify(contact);

    app.goTo().contactPage();
    try {
      Thread.sleep(1000);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());

    Assert.assertEquals(before, after);
    // try { Thread.sleep(5000); } catch (Exception e) { throw new RuntimeException(e); }

  }
}


