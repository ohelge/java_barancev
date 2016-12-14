package ru.stqa.ol.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.ol.addressbook.model.ContactData;
import ru.stqa.ol.addressbook.model.Contacts;
import ru.stqa.ol.addressbook.model.GroupData;
import ru.stqa.ol.addressbook.model.Groups;

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
      new Select(wd.findElement(By.name("new_group"))).getFirstSelectedOption();
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void edit(int id) {
    // click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    String php = "edit.php?id=" + Integer.toString(id);
    click(By.cssSelector("a[href='" + php + "']"));
  }

  public void update() {
    click(By.name("update"));
  }

  public void selectContactById(int id) {   //l5_m5: novii metod
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void deleteSelected() {
    click(By.cssSelector("input[value='Delete']"));
  }

  private Contacts contactCache = null; // l5_m7 Kewirovanie dobavka v metodi all, delete, create, modify

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }

    Contacts contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      String name = element.findElements(By.tagName("td")).get(2).getText(); // Vo 2m td nahoditsq "First name"
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));  //l4_m8: Preobrazovanie stroki v integer id
      contactCache.add(new ContactData().withId(id).withFirstname(name));
    }
    return new Contacts(contactCache);
  }

  public void delete(ContactData contact) {
    //contactPage();
    selectContactById(contact.getId());
    // try {Thread.sleep(1000);    } catch (Exception e) {     throw new RuntimeException(e);    }
    deleteSelected();
    wd.switchTo().alert().accept();
    contactCache = null;
    contactPage();
  }

  public void create(ContactData contact) {
    addNew();
    fill(contact, true);
    click(By.cssSelector("input[value='Enter']"));
    contactCache = null;
    contactPage();
  }

  public void modify(ContactData contact) {
    //try {Thread.sleep(3000);    } catch (Exception e) {     throw new RuntimeException(e);    }
    edit(contact.getId());
    fill(contact, false);
    update();
    contactCache = null;
    contactPage();
  }

  public void addNew() {
    if (isElementPresent(By.name("Submit"))) {
      return;
    }
    click(By.linkText("add new"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public void select(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
    // click(By.name("selected[]"));
  }

  public void delete_click() {
    click(By.xpath("//div[@id='content']/form[2]/input[2]"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

}
