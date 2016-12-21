package ru.stqa.ol.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.ol.addressbook.model.ContactData;
import ru.stqa.ol.addressbook.model.Contacts;

import java.util.List;

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
      List<WebElement> cells = element.findElements(By.tagName("td"));

      String firstName = cells.get(2).getText(); // Vo 2m td nahoditsq "First name"
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));  //l4_m8: Preobrazovanie stroki v integer id
      String[] phones = cells.get(5).getText().split("\n"); //l5_m9-10: OBS! rezhem stroku s pomow'u regex. Tri telefona v odnoi q4eike
      contactCache.add(new ContactData().withId(id).withFirstname(firstName)
              .withHomePhone(phones[0]).withMobilePhone(phones[1]).withWorkPhone(phones[2]));
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

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModification(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back();
    return new ContactData()
            .withId(contact.getId())
            .withFirstname(firstname)
            .withHomePhone(home)
            .withMobilePhone(mobile)
            .withWorkPhone(work);
  }

  private void initContactModification(int id) {
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../..")); // l5_m9: Pervii perehod daet q4eiku tablici, vtoroi- stroku
    //Nahodim checkbox kontakta i podnimaemsq na 2 urovnq vverh kak i cd.. tut tozhe .. A na4inaem s . eto zna4it 4to poisk na4inaetsq s tekuwego elementa.
    List<WebElement> cells = row.findElements(By.tagName("td")); //Iwem vse elementi s tegom td
    cells.get(7).findElement(By.tagName("a")).click();//Berem nuzhnuu q4eiku 7(eto 8i stolbec) i klikaem. Tag "a" zna4it ancor
    try {Thread.sleep(1000);    } catch (Exception e) {     throw new RuntimeException(e);    }

    // drugie 3 varianta nahozhdeniq ssilki:
    //String.format() - podstanovka zna4enii vnutr' stroki
    //wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click(); //l5_m9:OBS! v Xpath numeraciq na4inaetsq s 1, a ne s 0.
    //input[@value='%s'] - nahodim checkbox, /../../ - podnimaemsq na 2 urovnq vverh, td[8]/a - v stroke iwem 8u q4eiku i vnutri etoi q4eiki iwem ssilku (tag a)

    //wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click(); //l5_m9: v [] piwem ograni4eniq
    // Ho4u naiti stroku vnutri kotoroi est' est' checkbox s zadannim identifikatorom. tr- iskat' stroku i v [] piwi lubie ograni4eniq. input[@value='%s']] - vnutri stroki est' input s zadannim elementom value

    //wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }
}
