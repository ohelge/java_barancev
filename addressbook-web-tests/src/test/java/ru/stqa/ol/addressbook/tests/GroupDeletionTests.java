package ru.stqa.ol.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ol.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {


  @Test
  public void testGroupDeletion() {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    // int before = app.getGroupHelper().getGroupCount();

    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("Group2", "Group2 header", "Group2 footer"));
      app.getNavigationHelper().gotoGroupPage();
      before = app.getGroupHelper().getGroupList();
      try {
        Thread.sleep(3000);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
    app.getGroupHelper().selectGroup(before.size() - 1);
    // try { Thread.sleep(5000); } catch (Exception e) { throw new RuntimeException(e); }
    app.getGroupHelper().deleteSelectedGroups();
    app.getNavigationHelper().gotoGroupPage();

    // int after = app.getGroupHelper().getGroupCount();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() - 1);

    /*for (int i=0; i < after.size(); i++) {
     Assert.assertEquals(after.get(i), before.get(i));
    } */
    before.remove(before.size() - 1);
    Assert.assertEquals(after, before);
  }
}
