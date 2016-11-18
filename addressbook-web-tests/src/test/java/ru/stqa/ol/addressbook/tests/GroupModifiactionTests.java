package ru.stqa.ol.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ol.addressbook.model.GroupData;

/**
 * Created by A546902 on 2016-11-04.
 */
public class GroupModifiactionTests extends TestBase {

  @Test
  public void testGroupModification(){
    app.getNavigationHelper().gotoGroupPage();

    int before = app.getGroupHelper().getGroupCount();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("Group2", "Group2 header", "Group2 footer"));
      app.getNavigationHelper().gotoGroupPage();
      try { Thread.sleep(2000); } catch (Exception e) { throw new RuntimeException(e); }
      app.getGroupHelper().selectGroup(before);
      before ++;
    } else {app.getGroupHelper().selectGroup(before - 1); }
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("Group0", null, null));
    try { Thread.sleep(2000); } catch (Exception e) { throw new RuntimeException(e); }
    //try { Thread.sleep(5000); } catch (Exception e) { throw new RuntimeException(e); }
    app.getGroupHelper().submitGroupModification();
    app.getNavigationHelper().gotoGroupPage();

    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before );


  }
}
