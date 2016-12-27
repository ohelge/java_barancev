package ru.stqa.ol.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.ol.addressbook.model.ContactData;
import ru.stqa.ol.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test (enabled = true)
  public void contactCreation() {
    app.goTo().contactPage();
    Contacts before = app.contact().all();
    File photo = new File("src\\test\\resources\\DoubleOL_foto2.jpg"); //l6_m1 ukazivaem put' otnositel'no tekuwei dir
    ContactData contact = new ContactData()
            .withFirstname("First name1")
            .withLastname("Last name1")
            .withEmail("first-name1.last-name1@gmail.com")
            .withPhoto(photo);
    app.contact().create(contact);

    assertThat(app.contact().count(), equalTo (before.size() + 1) );
    Contacts after = app.contact().all();

    contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt() );

    assertThat(after, equalTo(before.withAdded(contact)) );

  }
  @Test (enabled = false)
  public void testCurrentDir() {
    //l6_m1: esli File currentDir = new File(".") to sout ukazhet kornevyu/tekywyu direktoriu C:\Devel\java_barancev\addressbook-web-tests\.
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath() );
    File photo = new File("src\\test\\resources\\DoubleOL_foto2.jpg"); //C:\Devel\java_barancev\addressbook-web-tests\src\test\resources\DoubleOL_foto2.jpg
    System.out.println(photo.getAbsolutePath() );
    System.out.println(photo.exists());

  }

  @Test (enabled = false)
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
