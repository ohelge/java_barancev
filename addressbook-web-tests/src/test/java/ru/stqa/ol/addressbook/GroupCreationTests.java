package ru.stqa.ol.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() {
    app.gotoGroupPage();
    app.initGroupCreation();
    app.fillGroupForm(new GroupData("Group2", "Group2 header", "Group2 footer"));
    app.submitGroupCreation();
    app.returtToGroupPage();
  }

}
