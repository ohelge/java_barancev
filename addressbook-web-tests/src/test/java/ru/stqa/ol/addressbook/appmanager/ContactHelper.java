package ru.stqa.ol.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.ol.addressbook.model.ContactData;
import ru.stqa.ol.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by OL on 2016-11-03.
 */
public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submit(String xpathExpression) {
    wd.findElement(By.xpath(xpathExpression)).click();
  }

  public void fill(ContactData contactData, boolean creation) { // added boolean creation dlq flaga sozdaniq kontakta, false- dlq modifikacii. Video l3_m08
    type(By.name("firstname"), contactData.getFirstname());
    //type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    //type(By.name("nickname"), contactData.getNickname());
    type(By.name("email"), contactData.getEmail());
    type(By.name("address2"), contactData.getAddress());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).getFirstSelectedOption() ;
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void edit() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public void updateContact() {
    click(By.name("update"));
  }

  public void delete2() {
    click(By.name("delete"));
  }

  public void delete_click() {
    click(By.xpath("//div[@id='content']/form[2]/input[2]"));
  }

  public void delete(ContactData contact) {
    contactPage();
    edit();
    delete_click();
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public void select(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
    // click(By.name("selected[]"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public Set<ContactData> all() {
    Set<ContactData> contacts = new HashSet<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("entry") );
    for (WebElement element : elements) {
      String name = element.findElements( By.tagName("td") ).get(2).getText()  ;
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));  //l4_m8: Preobrazovanie stroki v integer id
      ContactData contact = new ContactData().withId(id).withFirstname(name) ;
      contacts.add(contact);
    }
    return contacts;
  }
  public void addNew() {
    if ( isElementPresent( By.name("Submit")) ) {
      return;
    }
    click(By.linkText("add new"));
  }

  public void create(ContactData contact) {
    addNew();
    fill(contact, true);
    submit("//div[@id='content']/form/input[21]");
  }
}
