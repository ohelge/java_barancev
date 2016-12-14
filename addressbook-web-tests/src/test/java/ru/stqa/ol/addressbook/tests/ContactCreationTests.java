package ru.stqa.ol.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.ol.addressbook.model.ContactData;
import ru.stqa.ol.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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

    assertThat(app.contact().count(), equalTo (before.size() + 1) );
    Contacts after = app.contact().all();

    contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt() );

    assertThat(after, equalTo(before.withAdded(contact)) );

  }

  @Test (enabled = true)
  public void contactBadCreation() {
    app.goTo().contactPage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstname("1111 ' ");
    app.contact().create(contact);

    assertThat(app.contact().count(), equalTo (before.size() ) );
    Contacts after = app.contact().all();

    contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt() );

    assertThat(after, equalTo(before) );

  }

}
