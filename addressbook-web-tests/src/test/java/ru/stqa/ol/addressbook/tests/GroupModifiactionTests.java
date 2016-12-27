package ru.stqa.ol.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ol.addressbook.model.GroupData;
import ru.stqa.ol.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

public class GroupModifiactionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (!app.group().isThereAGroup()) {  // ili if (app.group().list().size() == 0)
      app.group().create(new GroupData().withName("Group2") );
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
    Groups before = app.group().all();
    GroupData modifiedGroup = before.iterator().next(); //l5_m5: vozvrawaem pervii popavwiisq element mnojestva
    GroupData group = new GroupData()
            .withId(modifiedGroup.getId())  //Vstavili id v GroupData. OBS! Sohranqem starii id u izmenennoi gruppi ina4e test padaet
            .withName("Group2")
            .withHeader("Group2 header")
            .withFooter("Group2 footer");
    app.group().modify(group);

    assertEquals(app.group().count(), before.size());
    Groups after = app.group().all();


    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));

  }


}
