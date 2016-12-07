package ru.stqa.ol.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ol.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class GroupModifiactionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (!app.group().isThereAGroup()) {  // ili if (app.group().list().size() == 0)
      app.group().create(new GroupData().withGroupname("Group2") );
      try {
        Thread.sleep(2000);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }

  @Test
  public void testGroupModification() {
    app.goTo().groupPage();
    Set<GroupData> before = app.group().all();
    GroupData modifiedGroup = before.iterator().next(); //l5_m5: vozvrawaem pervii popavwiisq element mnojestva
    GroupData group = new GroupData()
            .withId(modifiedGroup.getId())  //Vstavili id v GroupData. OBS! Sohranqem starii id u izmenennoi gruppi ina4e test padaet
            .withGroupname("Group2")
            .withGroupheader("Group2 header")
            .withGroupfooter("Group2 footer");
    app.group().modify(group);

    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedGroup);
    before.add(group);

    Assert.assertEquals(before, after);

  }


}
