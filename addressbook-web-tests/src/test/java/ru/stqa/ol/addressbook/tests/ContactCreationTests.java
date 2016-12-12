package ru.stqa.ol.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ol.addressbook.model.ContactData;
import ru.stqa.ol.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {


  @Test (enabled = true)
  public void contactCreation() {
    app.goTo().contactPage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstname("First name1")
            .withLastname("Last name1")
            .withEmail("first-name1.last-name1@gmail.com");
    app.contact().create(contact);

    app.goTo().contactPage();
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt() );

    assertThat(after, equalTo(before.withAdded(contact)) );

  }


}
