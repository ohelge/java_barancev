package ru.stqa.ol.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ol.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().list().size() == 0 ) {
      app.group().create(new GroupData("Group2", "Group2 header", "Group2 footer"));
      try {
        Thread.sleep(2000);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }

  @Test
  public void testGroupDeletion() {
    app.goTo().groupPage();
    List<GroupData> before = app.group().list();
    // int before = app.group().getGroupCount();

    int index = before.size() - 1;
    app.group().delete(index);

    // int after = app.group().getGroupCount();
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), before.size() - 1);

    /*for (int i=0; i < after.size(); i++) {
     Assert.assertEquals(after.get(i), before.get(i));
    } */
    before.remove(index);
    Assert.assertEquals(after, before);
  }


}
