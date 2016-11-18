package ru.stqa.ol.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ol.addressbook.model.GroupData;

import java.util.List;

/**
 * Created by A546902 on 2016-11-04.
 */
public class GroupModifiactionTests extends TestBase {

  @Test
  public void testGroupModification(){
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    //int before = app.getGroupHelper().getGroupCount();

    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("Group2", "Group2 header", "Group2 footer"));
      app.getNavigationHelper().gotoGroupPage();
      before = app.getGroupHelper().getGroupList();
      try { Thread.sleep(2000); } catch (Exception e) { throw new RuntimeException(e); }
      //before ++;
    }
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("Group0", null, null));
    try { Thread.sleep(2000); } catch (Exception e) { throw new RuntimeException(e); }
    //try { Thread.sleep(5000); } catch (Exception e) { throw new RuntimeException(e); }
    app.getGroupHelper().submitGroupModification();
    app.getNavigationHelper().gotoGroupPage();

    List<GroupData> after = app.getGroupHelper().getGroupList();
    // int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after.size(), before.size() );


  }
}
