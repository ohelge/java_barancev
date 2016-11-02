package ru.stqa.ol.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() {
    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("Group2", "Group2 header", "Group2 footer"));
    submitGroupCreation();
    returtToGroupPage();
  }

}
