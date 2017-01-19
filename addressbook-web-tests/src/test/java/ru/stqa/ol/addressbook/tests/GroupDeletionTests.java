package ru.stqa.ol.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ol.addressbook.model.GroupData;
import ru.stqa.ol.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.db().groups().size() == 0) {
      app.group().create(new GroupData().withName("Group2").withHeader("Group2 header").withFooter("Group2 footer"));

    }
  }

  @Test
  public void testGroupDeletion() {
    app.goTo().groupPage();
    //Groups before = app.group().all();
    Groups before = app.db().groups(); //l7_m4
    GroupData deletedGroup = before.iterator().next(); //l5_m5: vozvrawaem pervii popavwiisq element mnojestva
    app.group().delete(deletedGroup);
    //Groups after = app.group().all();
    Groups after = app.db().groups();
    assertEquals(after.size(), before.size() - 1);

    assertThat(after, equalTo(before.without(deletedGroup)));
  }


}
