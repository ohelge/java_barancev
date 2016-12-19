package ru.stqa.ol.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.ol.addressbook.model.GroupData;
import ru.stqa.ol.addressbook.model.Groups;
import java.util.List;

/**
 * Created by OL on 2016-11-03.
 */
public class GroupHelper extends HelperBase {

  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  public void submitGroupCreation() {
    click(By.name("submit"));
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getGroupname());
    type(By.name("group_header"), groupData.getGroupheader());
    type(By.name("group_footer"), groupData.getGroupfooter());
  }

  public void initGroupCreation() {
    click(By.name("new"));
  }

  public void deleteSelectedGroups() {
    click(By.name("delete"));
  }

  public void selectGroupById(int id) {   //l5_m5: novii metod
    // wd.findElement(By.cssSelector("input[value='" + id + "']")).click(); //l5_m9:Stroka skleivaetsq iz 3h kuskov. Ili mozhno String.format(), eto ponqtnee:
    wd.findElement(By.cssSelector(String.format("input[value='%s']", id  ))).click();

  }

  public void initGroupModification() {
    click(By.name("edit"));
  }

  public void submitGroupModification() {
    click(By.name("update"));
  }

  public void create(GroupData group) {
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    groupCache = null;
    groupPage();
  }
  public void modify(GroupData group) {  //l5_m5: menqetsq parametr
    selectGroupById(group.getId());
    initGroupModification();
    fillGroupForm(group);
    //try { Thread.sleep(2000); } catch (Exception e) { throw new RuntimeException(e); }
    submitGroupModification();
    groupCache = null;
    groupPage();
  }

  public void delete(GroupData group) { //l5_m5: novii metod delete
    selectGroupById(group.getId());
    deleteSelectedGroups();
    groupCache = null;
    groupPage();
  }
  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

private Groups groupCache = null; // l5_m7 Kewirovanie dobavka v metodi all, delete, create, modify

  public Groups all() { //l5_m6 Pomenali Set na Groups
    if (groupCache != null) {
      return new Groups(groupCache);
    }
    Groups groupCache = new Groups(); //l5_m6 Pomenali Set na Groups
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements) {
      String name = element.getText();
      // String id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value")); // l4_m7 novii parametr id is GroupData. Prisvaivaem zna4enie "value"
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));  //l4_m8: Preobrazovanie stroki v integer id
      groupCache.add(new GroupData().withId(id).withGroupname(name));
    }
    return new Groups(groupCache);
  }


}

