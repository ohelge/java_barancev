package ru.stqa.ol.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ol.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.goTo().groupPage();
    List<GroupData> before = app.group().list();
    // int before = app.group().getGroupCount();
    GroupData group = new GroupData("Group2", "Group2 header", null);
    app.group().create(group);
    app.goTo().groupPage();
    List<GroupData> after = app.group().list();
    // int after = app.group().getGroupCount();

    Assert.assertEquals(after.size(), before.size() + 1);


   /* int max = 0;
    for (GroupData g : after) {
      if (g.getId() > max) {
        max = g.getId();
      }
    }
    Comparator<? super GroupData> byId2 = (Comparator<GroupData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId()); // max object v potoke, rabotaet s versii java 8
    int max1 = after.stream().max(byId2).get().getId();
    group.setId(max1);*/

    before.add(group);

    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId() );
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
    //Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
  }

}
