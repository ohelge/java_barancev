package ru.stqa.ol.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.ol.addressbook.model.GroupData;
import ru.stqa.ol.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

public class GroupCreationTests extends TestBase {

  @Test (enabled = true)
  public void testGroupCreation() {
    app.goTo().groupPage();
    Groups before =  app.group().all(); //l5_m6
    GroupData group = new GroupData().withGroupname("Group2");
    app.group().create(group);

    assertThat(app.group().count(), equalTo(before.size() +1 )); //l5_m8 : zamenqem after.size() na bolee bistruu proverku app.group().count() i stavim pered zagruzkoi spiska grupp. Eto i est' hashirovanie
    Groups after = app.group().all();

    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());   //l5_m5: prewvawaem potok objektov GrouData v potok Identifikatorov. Anonimnaq funkciq mapToInt g: parametr gruppa, a resultat - identifikator. Berem max i preobrazuem v int
    assertThat(after, equalTo(before.withAdded(group))); // MatcherAssert.assertThat(..) Alt+Enter -> "Add static import"
  }

  @Test
  public void testBadGroupCreation() {
    app.goTo().groupPage();
    Groups before =  app.group().all(); //l5_m6
    GroupData group = new GroupData().withGroupname("1111' "); // Imq s apostrofom NE sozdaetsq dazhe esli poprobovat' sozdat' v prilozhenii
    app.group().create(group);

    assertThat(app.group().count(), equalTo(before.size() ));
    Groups after = app.group().all();


    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());   //l5_m5: prewvawaem potok objektov GrouData v potok Identifikatorov. Anonimnaq funkciq mapToInt g: parametr gruppa, a resultat - identifikator. Berem max i preobrazuem v int
    assertThat(after, equalTo(before)); // MatcherAssert.assertThat(..) Alt+Enter -> "Add static import"
  }


}
