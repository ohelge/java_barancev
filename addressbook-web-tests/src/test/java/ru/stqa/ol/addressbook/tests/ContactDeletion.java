package ru.stqa.ol.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ol.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by A546902 on 2016-11-04.
 */
public class ContactDeletion extends TestBase {

  @Test (enabled = false)
  public void contactDeletion() {
    app.goTo().contactPage();
    // int before = app.contact().getContactCount();
    List<ContactData> before = app.contact().list();
    int before2;
    if (!app.contact().isThereAContact()) {
      app.goTo().groupPage();
      if (app.group().isThereAGroup()) {
        app.goTo().addNew();
        app.contact().fill(new ContactData("First name1", "Last name1", "first-name1.last-name1@gmail.com", "Address1", "Group2"), true);
        app.contact().submit("//div[@id='content']/form/input[21]");
        app.goTo().contactPage();
        //try { Thread.sleep(2000); } catch (Exception e) { throw new RuntimeException(e); }
        before2 = before.size() +1 ;
      } else {
        app.goTo().contactPage();
        app.goTo().addNew();
        app.contact().fill(new ContactData("First name4", "Last name4", "first-name1.last-name1@gmail.com", "Address1", "[none]"), true);
        app.contact().submit("//div[@id='content']/form/input[21]");
        app.goTo().contactPage();
        before2 = before.size() +1 ;
        // before++;
      }
    } else {
      before2 = before.size() ;

    }

    try {
      Thread.sleep(2000);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    List<ContactData> after = app.contact().list();
    app.contact().edit();
    try { Thread.sleep(2000); } catch (Exception e) { throw new RuntimeException(e); }
    app.contact().delete();
    app.goTo().contactPage();

    //int after = app.contact().getContactCount();

    Assert.assertEquals(after.size(), before2 -1);

    before.remove(before2 - 1);
    Assert.assertEquals(after, before);
  }
}
