package ru.stqa.ol.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ol.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModifiactionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (!app.group().isThereAGroup()) {  // ili if (app.group().list().size() == 0)
      app.group().create(new GroupData("Group2", "Group2 header", "Group2 footer"));
      try {
        Thread.sleep(2000);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }

  @Test
  public void testGroupModification() {


    List<GroupData> before = app.group().list();
    int index = before.size() - 1;

    GroupData group = new GroupData(before.get(index).getId(), "Group2", "Group2 header", "Group2 footer"); //Vstavili id v GroupData. OBS! Sohranqem starii id u izmenennoi gruppi ina4e test padaet
    app.group().modify(index, group);

    List<GroupData> after = app.group().list();
    // int after = app.group().getGroupCount();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

    // Assert.assertEquals(new HashSet<>(before), new HashSet<>(after)); // preobrazovanie spiska after/before v mnozhestvo (4tobi sravnivat' neupoqdo4ennie mnozhestva). OBS. spiski eto uporqdo4ennoe mnozhestvo.
    // OBS. esli budut 2 gruppi s odinakovimi imenami to mnozhestvo pomestit ih v odin element. Group imeet identifikator value (inspect element)

  }


}
