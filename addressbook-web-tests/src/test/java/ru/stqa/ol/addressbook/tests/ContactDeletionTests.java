package ru.stqa.ol.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ol.addressbook.model.ContactData;
import ru.stqa.ol.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by A546902 on 2016-11-04.
 */
public class ContactDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().contactPage();
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("First name1")
              .withLastname("Last name1")
              .withEmail("first-name1.last-name1@gmail.com")
              .withAddress("Address1")
              .withPhotoFileName("C:/Devel/java_barancev/addressbook-web-tests/src/test/resources/DoubleOL_foto2.jpg"));
    }
  }

  @Test(enabled = true)
  public void contactDeletion() {

    app.goTo().contactPage();
    //Contacts before = app.contact().all();
    Contacts before = app.db().contacts();//l7_m4
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);

    assertThat(app.contact().count(), equalTo(before.size() - 1) );

    Contacts after =  app.db().contacts() ;//l7_m4
    assertThat(after, equalTo(before.without(deletedContact)) );

  }
}
