package ru.stqa.ol.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ol.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Created by A546902 on 2016-11-04.
 */
public class GroupModifiactionTests extends TestBase {

  @Test
  public void testGroupModification() {
    app.getNavigationHelper().gotoGroupPage();

    //int before = app.getGroupHelper().getGroupCount();

    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("Group2", "Group2 header", "Group2 footer"));
       try {
        Thread.sleep(2000);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }

    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().initGroupModification();
    GroupData group = new GroupData(before.get(before.size() - 1).getId(), "Group2", "Group2 header", "Group2 footer"); //Vstavili id v GroupData. OBS! Sohranqem starii id u izmenennoi gruppi ina4e test padaet
    app.getGroupHelper().fillGroupForm(group);

    //try { Thread.sleep(2000); } catch (Exception e) { throw new RuntimeException(e); }
    app.getGroupHelper().submitGroupModification();
    app.getNavigationHelper().gotoGroupPage();

    List<GroupData> after = app.getGroupHelper().getGroupList();
    // int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId() );
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

    // Assert.assertEquals(new HashSet<>(before), new HashSet<>(after)); // preobrazovanie spiska after/before v mnozhestvo (4tobi sravnivat' neupoqdo4ennie mnozhestva). OBS. spiski eto uporqdo4ennoe mnozhestvo.
     // OBS. esli budut 2 gruppi s odinakovimi imenami to mnozhestvo pomestit ih v odin element. Group imeet identifikator value (inspect element)

  }
}
