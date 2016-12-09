package ru.stqa.ol.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ol.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * Created by OL A546902 on 2016-11-04.
 */
public class ContactModificationTests extends TestBase {

  @Test (enabled = true)
  public void testContactModification() {

    //int before = app.contact().getContactCount();
    app.goTo().contactPage();
    Set<ContactData> before = app.contact().all();

    app.goTo().groupPage();
    if (app.group().isThereAGroup()) {
      app.goTo().contactPage();
      if (app.contact().isThereAContact()) { // est' i Group i Contact


      } else { // est' Group, net Contact
        app.contact().create(new ContactData()
                .withFirstname("First name1")
                .withLastname("Last name1")
                .withEmail("first-name1.last-name1@gmail.com")
                .withAddress("Address1").withGroup("Group2") );


      }


    } else {
      app.goTo().contactPage();
      if (app.contact().isThereAContact()) { // net Group est' contact


      } else { //ni group ni contact netu
        app.contact().create(new ContactData()
                .withFirstname("First name1")
                .withLastname("Last name1")
                .withEmail("first-name1.last-name1@gmail.com")
                .withAddress("Address1").withGroup("[none]") );
      }
    }


    app.goTo().contactPage();
    try {
      Thread.sleep(2000);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals( after.size(), before.size());

    Assert.assertEquals(before, after);
    // try { Thread.sleep(5000); } catch (Exception e) { throw new RuntimeException(e); }

  }
}


