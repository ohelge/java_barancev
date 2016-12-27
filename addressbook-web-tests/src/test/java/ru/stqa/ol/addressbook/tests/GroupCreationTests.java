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
    list.add(new Object[]{"group1'", "header1", "footer1"}); //Esli postavim ' v imq to tol'ko odin test iz 3h upadet
    list.add(new Object[]{"group2", "header2", "footer2"});
    list.add(new Object[]{"group3", "header3", "footer3"});
    return list.iterator();
  }

  @Test(enabled = true, dataProvider = "validGroups") //l6_m4 Pervoe:Parametr dataProvider ukazat' po imeni
  public void testGroupCreation(String name, String header, String footer) { //l6_m4 Vtoroe: U testovogo metoda nado sdelat' stol'ko paramentrov skol'ko est' v kazhdom massive objektov.
    /*String[] names = new String[]{"group1", "group2", "group3"}; //l6_m4 Cikl for mozhno ubrat'. Ego delaet TestNG
    for (String name : names) { */
      GroupData group = new GroupData().withName(name).withHeader(header).withFooter(footer);
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
