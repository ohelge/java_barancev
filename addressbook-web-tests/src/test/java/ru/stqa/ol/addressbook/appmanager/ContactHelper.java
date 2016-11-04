package ru.stqa.ol.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.ol.addressbook.model.ContactData;

/**
 * Created by OL on 2016-11-03.
 */
public class ContactHelper extends HelperBase {

  public ContactHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void submitContactCreation(String xpathExpression) {
    wd.findElement(By.xpath(xpathExpression)).click();
  }

  public void contactFill(ContactData contactData) {
    fillContactForm(contactData);
  }

  private void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("email"), contactData.getEmail());
    type(By.name("address2"), contactData.getAddress());

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
}
