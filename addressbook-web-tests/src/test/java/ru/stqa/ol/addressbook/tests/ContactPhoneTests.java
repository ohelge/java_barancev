package ru.stqa.ol.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.ol.addressbook.model.ContactData;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


/**
 * Created by A546902 on 2016-12-14.
 */
public class ContactPhoneTests extends TestBase {

  @Test(enabled= true)
  public void testContactPhones() {
    app.goTo().contactPage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getHomePhone(), equalTo(contactInfoFromEditForm.getHomePhone()) );
    assertThat(contact.getMobilePhone(), equalTo(contactInfoFromEditForm.getMobilePhone()) );
    assertThat(contact.getWorkPhone(), equalTo(contactInfoFromEditForm.getWorkPhone()) );
  }
}
