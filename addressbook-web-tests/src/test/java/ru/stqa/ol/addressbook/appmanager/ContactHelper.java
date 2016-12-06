package ru.stqa.ol.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.ol.addressbook.model.ContactData;
import ru.stqa.ol.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OL on 2016-11-03.
 */
public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation(String xpathExpression) {
    wd.findElement(By.xpath(xpathExpression)).click();
  }

  public void fillContactForm(ContactData contactData, boolean creation) { // added boolean creation dlq flaga sozdaniq kontakta, false- dlq modifikacii. Video l3_m08
    type(By.name("firstname"), contactData.getFirstname());
    //type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    //type(By.name("nickname"), contactData.getNickname());
    type(By.name("email"), contactData.getEmail());
    type(By.name("address2"), contactData.getAddress());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void editContact() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public void updateContact() {
    click(By.name("update"));
  }

  public void deleteContact() {
    click(By.xpath("//div[@id='content']/form[2]/input[2]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
    // click(By.name("selected[]"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    //List<WebElement> elements = wd.findElements( By.cssSelector("tr"));

    /*List<WebElement> elements = wd.findElements(By.cssSelector("tr.odd"));
    for (WebElement element : elements) {
      String name = element.getText();
      // String id = element.findElement(By.tagName("input")).getAttribute("value");
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));  //l4_m8: Preobrazovanie stroki v integer id
      ContactData contact = new ContactData(id, name, null, null, null, null);
      contacts.add(contact);
    }*/

    List<WebElement> elements2 = wd.findElements(By.cssSelector("tr.odd"));
    for (WebElement element : elements2) {
      String name = element.getText();
      // String id = element.findElement(By.tagName("input")).getAttribute("value");
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));  //l4_m8: Preobrazovanie stroki v integer id
      ContactData contact = new ContactData(id, name, null, null, null, null);
      contacts.add(contact);
    }

    return contacts;
  }
}
