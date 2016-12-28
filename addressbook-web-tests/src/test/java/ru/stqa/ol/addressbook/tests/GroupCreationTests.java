package ru.stqa.ol.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.ol.addressbook.model.GroupData;
import ru.stqa.ol.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

public class GroupCreationTests extends TestBase {

  //l6_m6 Iterator massiva objektov
  @DataProvider // Ideq razdeleniq dannih ot testa
  public Iterator<Object[]> validGroups() throws IOException {
    /*List<Object[]> list = new ArrayList<Object[]>(); //l6_m5 delaem spisok massivov objektov. Togda pri vipolnenii vidim 3 zapuska s 3mq testovimi dannimi.
    list.add(new Object[]{new GroupData().withName("group1").withHeader("header1").withFooter("footer1")}); // Menqem v @DataProvider ne massiv strok na massiv objektov tipa GroupData, massiv budet iz odnogo elementa
    list.add(new Object[]{new GroupData().withName("group2").withHeader("header2").withFooter("footer2")});
    list.add(new Object[]{new GroupData().withName("group3").withHeader("header3").withFooter("footer3")}); l6_m5:Pri 4tenii dannih iz faila udalqem list.add*
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.csv"))); //l6_m5 Object BufferedReader(togda est' metod readline, 4ego net v Reader)! Zavora4ivaem Reader v BufferedReader */
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")));
    String line = reader.readLine();
    String xml = ""; //l6_m6
    while (line != null) {
      /*String[] split = line.split(";");//l6_m5 V ka4estve razdelitelq -;
      list.add(new Object[] {new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2]) } ); //l6_m5 stroim iz polu4ennih kuso4kov objekt. Dobavlqem massiv kot sostoit iz odnogo objekta*/
      xml += line;  //l6_m6
      line = reader.readLine();
    }
    XStream xStream = new XStream(); //l6_m6
    xStream.processAnnotations(GroupData.class); //l6_m6
    List<GroupData> groups = (List<GroupData>) xStream.fromXML(xml); //l6_m6 List<GroupData>) -eto privedenie tipa. http://x-stream.github.io/tutorial.html Deserializing an object back from XML
    return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator(); //l6_m6 k kazhdomy obektu nuzhno primenit' funkciu kot etot obekt tipa GrpoupData zavernet v massiv kot sostoit iz odnogo etogo objekta.
// Dalee collect() sozdaet iz potoka spisok. i u polu4ivwegosq spiska berem iterator. Ego i nuzhno vozvrawat'
    // return list.iterator(); //l6_m5
  }

  @Test(enabled = true, dataProvider = "validGroups") //l6_m4 Parametr dataProvider ukazat' po imeni
  public void testGroupCreation(GroupData group) { //l6_m4 Udobnee peredavat' dannie kak 1 objekt: menqem String name, String header, String footer na GroupData group.
    // Menqem v @DataProvider ne massiv strok na massiv objektov tipa GroupData, massiv budet iz odnogo elementa
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
