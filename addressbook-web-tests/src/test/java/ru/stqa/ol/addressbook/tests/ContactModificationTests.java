package ru.stqa.ol.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ol.addressbook.model.ContactData;
import ru.stqa.ol.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by OL A546902 on 2016-11-04.
 */
public class ContactModificationTests extends TestBase {
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
  public void testContactModification() {

    app.goTo().contactPage();
    Contacts before = app.db().contacts();

    ContactData modifiedContact = before.iterator().next(); //l5_m5: vozvrawaem pervii popavwiisq element mnojestva
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId())
            .withFirstname("First name1")
            .withLastname("Last name1")
            .withEmail("first-name1.last-name1@gmail.com")
            .withPhotoFileName(new File("src\\test\\resources\\DoubleOL_foto2.jpg").getAbsolutePath());
    app.contact().modify(contact);

    assertEquals(app.contact().count(), before.size());
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();//l7_m4

    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    // try { Thread.sleep(5000); } catch (Exception e) { throw new RuntimeException(e); }

  }
}


