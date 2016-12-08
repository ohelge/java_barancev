package ru.stqa.ol.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ol.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by A546902 on 2016-11-04.
 */
public class ContactDeletion extends TestBase {

  @Test(enabled = true)
  public void contactDeletion() {
    app.goTo().contactPage();
    // int before = app.contact().getContactCount();
    List<ContactData> before = app.contact().list();
    int before2 = before.size();
    if (!app.contact().isThereAContact()) {
      app.goTo().groupPage();
      if (app.group().isThereAGroup()) {
        app.goTo().addNew();
        app.contact().fill(new ContactData("First name1", "Last name1", "first-name1.last-name1@gmail.com", "Address1", "Group2"), true);
        app.contact().submit("//div[@id='content']/form/input[21]");
      } else {
        app.goTo().addNew();
        app.contact().fill(new ContactData("First name4", "Last name4", "first-name1.last-name1@gmail.com", "Address1", "[none]"), true);
        app.contact().submit("//div[@id='content']/form/input[21]");

      }
    } else {

      before.remove(0);
    }
    app.goTo().contactPage();

    app.contact().edit();
    app.contact().delete();
    app.goTo().contactPage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());
    Assert.assertEquals(after, before);
  }
}
