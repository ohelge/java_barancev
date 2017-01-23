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
    if (app.db().groups().size() == 0) { //l7_m4 novaq proverka s ispol'z db v obhod web-interfejsa
    app.goTo().groupPage();
    // l7_m4 Ybiraem proverku: if (!app.group().isThereAGroup()) {  // ili if (app.group().list().size() == 0)
      app.group().create(new GroupData().withName("Group2"));

    }
  }

  @Test
  public void testGroupModification() {
    app.goTo().groupPage();
    //l7_m4 Menqem Groups before = app.group().all(); Spisok grupp iz bd:
    Groups before = app.db().groups();
            GroupData modifiedGroup = before.iterator().next(); //l5_m5: vozvrawaem pervii popavwiisq element mnojestva
    GroupData group = new GroupData()
            .withId(modifiedGroup.getId())  //Vstavili id v GroupData. OBS! Sohranqem starii id u izmenennoi gruppi ina4e test padaet
            .withName("Group2")
            .withHeader("Group2 header")
            .withFooter("Group2 footer");
    app.group().modify(group);//l7_m4 Eto osnovnoe dejstvie testa.Vse ostal'noe delaem max bistrim sposobom.Delaem novii klass DbHelper

    assertEquals(app.group().count(), before.size());//l7_m4 mojno ubrat' dlq bistroti, no ostavim dlq proverki iz web-interfejsa
    //l7_m4 Menqem Groups after = app.group().all(); na
    Groups after = app.db().groups(); //Test vipolnqetsq zna4itel'no bistree
    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));

    verifyGroupListInUI(); //l7_m5 novii metod dla proverki grupp iz web interfejsa. Alt+Enter, delaem metod public i perenosim v TestBase

  }


}
