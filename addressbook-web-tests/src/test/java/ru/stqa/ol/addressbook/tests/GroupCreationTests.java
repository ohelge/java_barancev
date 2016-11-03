package ru.stqa.ol.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.ol.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupForm(new GroupData("Group2", "Group2 header", "Group2 footer"));
    app.getGroupHelper().submitGroupCreation();
    app.getGroupHelper().returnToGroupPage();
  }

}
