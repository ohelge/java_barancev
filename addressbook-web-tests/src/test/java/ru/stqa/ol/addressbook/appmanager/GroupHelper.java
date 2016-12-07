package ru.stqa.ol.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.ol.addressbook.model.GroupData;

import java.util.ArrayList;
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

  public void selectGroup(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
    // click(By.name("selected[]"));
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
  }
  public void modify(int index, GroupData group) {
    selectGroup(index);
    initGroupModification();
    fillGroupForm(group);
    //try { Thread.sleep(2000); } catch (Exception e) { throw new RuntimeException(e); }
    submitGroupModification();
    groupPage();
  }
  public void delete(int index) {
    selectGroup(index);
    // try { Thread.sleep(5000); } catch (Exception e) { throw new RuntimeException(e); }
    deleteSelectedGroups();
    groupPage();
  }
  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getGroupCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<GroupData> list() {
    List<GroupData> groups = new ArrayList<GroupData>();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements) {
      String name = element.getText();
      // String id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value")); // l4_m7 novii parametr id is GroupData. Prisvaivaem zna4enie "value"
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));  //l4_m8: Preobrazovanie stroki v integer id
      GroupData group = new GroupData(id, name, null,null);
      groups.add(group);
    }
    return groups;
  }

}

