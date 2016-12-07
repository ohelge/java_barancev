package ru.stqa.ol.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ol.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.goTo().groupPage();
    Set<GroupData> before = app.group().all();
    // int before = app.group().getGroupCount();
    GroupData group = new GroupData().withGroupname("Group2");
    app.group().create(group);
    app.goTo().groupPage();
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt() );   //l5_m5: prewvawaem potok objektov GrouData v potok Identifikatorov. Anonimnaq funkciq mapToInt g: parametr gruppa, a resultat - identifikator. Berem max i preobrazuem v int
    before.add(group);

    Assert.assertEquals(before, after);
    //Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
  }

}
