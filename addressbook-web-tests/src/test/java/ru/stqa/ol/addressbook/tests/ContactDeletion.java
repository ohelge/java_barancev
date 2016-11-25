package ru.stqa.ol.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ol.addressbook.model.ContactData;
import ru.stqa.ol.addressbook.model.GroupData;

import java.util.List;

/**
 * Created by A546902 on 2016-11-04.
 */
public class ContactDeletion extends TestBase {

  @Test
  public void contactDeletion() {
    app.getNavigationHelper().returnToHomePage();
    // int before = app.getContactHelper().getContactCount();
    List<ContactData> before = app.getContactHelper().getContactList();
    int before2;
    if (!app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().gotoGroupPage();
      if (app.getGroupHelper().isThereAGroup()) {
        app.getNavigationHelper().gotoContactPage();
        app.getContactHelper().fillContactForm(new ContactData("First name1", "Last name1", "first-name1.last-name1@gmail.com", "Address1", "Group2"), true);
        app.getContactHelper().submitContactCreation("//div[@id='content']/form/input[21]");
        app.getNavigationHelper().returnToHomePage();
        //try { Thread.sleep(2000); } catch (Exception e) { throw new RuntimeException(e); }
        before2 = before.size() +1 ;
      } else {
        app.getNavigationHelper().returnToHomePage();
        app.getNavigationHelper().gotoContactPage();
        app.getContactHelper().fillContactForm(new ContactData("First name4", "Last name4", "first-name1.last-name1@gmail.com", "Address1", "[none]"), true);
        app.getContactHelper().submitContactCreation("//div[@id='content']/form/input[21]");
        app.getNavigationHelper().returnToHomePage();
        before2 = before.size() +1 ;
        // before++;
      }
    } else {
      app.getContactHelper().selectContact(before.size() - 2);
      before2 = before.size() ;

    }

    //app.getNavigationHelper().returnToHomePage();
    try {
      Thread.sleep(2000);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    List<ContactData> after = app.getContactHelper().getContactList();
    app.getContactHelper().editContact();
    app.getContactHelper().deleteContact();
    app.getNavigationHelper().returnToHomePage();

    //int after = app.getContactHelper().getContactCount();

    Assert.assertEquals(after.size(), before2);

    before.remove(before2 - 1);
    Assert.assertEquals(after, before);
  }
}
