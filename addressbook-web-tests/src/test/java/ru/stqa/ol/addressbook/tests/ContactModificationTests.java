package ru.stqa.ol.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.ol.addressbook.model.ContactData;

/**
 * Created by OL A546902 on 2016-11-04.
 */
public class ContactModificationTests extends TestBase{

  @Test
  public void testContactModification() {
    app.getNavigationHelper().returnToHomePage();
    app.getContactHelper().editContact();
    app.getContactHelper().fillContactForm(new ContactData("First name0", "Last name0", "first-name1.last-name1@gmail.com", "Address0", null), false);
    app.getContactHelper().updateContact();
    app.getNavigationHelper().returnToHomePage();

    // try { Thread.sleep(5000l); } catch (Exception e) { throw new RuntimeException(e); }

  }

}
