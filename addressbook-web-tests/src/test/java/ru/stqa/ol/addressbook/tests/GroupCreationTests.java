package ru.stqa.ol.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.ol.addressbook.model.GroupData;
import ru.stqa.ol.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

public class GroupCreationTests extends TestBase {

  //l6_m6 Iterator massiva objektov
  @DataProvider
  public Iterator<Object[]> validGroups() {
    List<Object[]> list = new ArrayList<Object[]>(); //delaem spisok massivov objektov. Togda pri vipolnenii vidim 3 zapuska s 3mq testovimi dannimi.
    list.add(new Object[]{new GroupData().withName("group1").withHeader("header1").withFooter("footer1")}); // Menqem v @DataProvider ne massiv strok a massiv objektov tipa GroupData, massiv budet iz odnogo elementa
    list.add(new Object[]{new GroupData().withName("group2").withHeader("header2").withFooter("footer2")});
    list.add(new Object[]{new GroupData().withName("group3").withHeader("header3").withFooter("footer3")});
    return list.iterator();
  }

  @Test(enabled = true, dataProvider = "validGroups") //l6_m4 Parametr dataProvider ukazat' po imeni
  public void testGroupCreation(GroupData group) { //l6_m4 Udobnee peredavat' dannie kak 1 objekt: menqem String name, String header, String footer na GroupData group.
    // Menqem v @DataProvider ne massiv strok a massiv objektov tipa GroupData, massiv budet iz odnogo elementa
    app.goTo().groupPage();
    Groups before = app.group().all(); //l5_m6
    app.group().create(group);

    assertThat(app.group().count(), equalTo(before.size() + 1)); //l5_m8 : zamenqem after.size() na bolee bistruu proverku app.group().count() i stavim pered zagruzkoi spiska grupp. Eto i est' hashirovanie
    Groups after = app.group().all();

    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());   //l5_m5: prewvawaem potok objektov GrouData v potok Identifikatorov. Anonimnaq funkciq mapToInt g: parametr gruppa, a resultat - identifikator. Berem max i preobrazuem v int
    assertThat(after, equalTo(before.withAdded(group))); // MatcherAssert.assertThat(..) Alt+Enter -> "Add static import"

  }


  @Test
  public void testBadGroupCreation() {
    app.goTo().groupPage();
    Groups before = app.group().all(); //l5_m6
    GroupData group = new GroupData().withName("1111' "); // Imq s apostrofom NE sozdaetsq dazhe esli poprobovat' sozdat' v prilozhenii
    app.group().create(group);

    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.group().all();


    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());   //l5_m5: prewvawaem potok objektov GrouData v potok Identifikatorov. Anonimnaq funkciq mapToInt g: parametr gruppa, a resultat - identifikator. Berem max i preobrazuem v int
    assertThat(after, equalTo(before)); // MatcherAssert.assertThat(..) Alt+Enter -> "Add static import"
  }


}
