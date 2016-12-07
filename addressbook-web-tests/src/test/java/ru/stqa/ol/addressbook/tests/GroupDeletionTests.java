package ru.stqa.ol.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ol.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;

public class GroupDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0 ) {
      app.group().create(new GroupData().withGroupname("Group2").withGroupheader("Group2 header").withGroupfooter("Group2 footer") );
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
    Set<GroupData> before = app.group().all();
    GroupData deletedGroup = before.iterator().next(); //l5_m5: vozvrawaem pervii popavwiisq element mnojestva
    app.group().delete(deletedGroup);
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedGroup);
    Assert.assertEquals(after, before);
  }


}
