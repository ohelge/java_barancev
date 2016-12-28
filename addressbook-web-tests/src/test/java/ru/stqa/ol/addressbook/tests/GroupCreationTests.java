package ru.stqa.ol.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.ol.addressbook.model.GroupData;
import ru.stqa.ol.addressbook.model.Groups;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

public class GroupCreationTests extends TestBase {

  //l6_m6 Iterator massiva objektov
  @DataProvider // Ideq razdeleniq dannih ot testa
  public Iterator<Object[]> validGroupsFromXml() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")));
    String line = reader.readLine();
    String xml = ""; //l6_m6 peremennaq v kot 4itaem soderzhimoe faila
    while (line != null) {
      xml += line;  //l6_m6
      line = reader.readLine();
    }
    XStream xStream = new XStream(); //l6_m6
    xStream.processAnnotations(GroupData.class); //l6_m6
    List<GroupData> groups = (List<GroupData>) xStream.fromXML(xml); //l6_m6 List<GroupData>) -eto privedenie tipa. http://x-stream.github.io/tutorial.html Deserializing an object back from XML
    return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator(); //l6_m6 k kazhdomy obektu nuzhno primenit' funkciu kot etot obekt tipa GrpoupData zavernet v massiv kot sostoit iz odnogo etogo objekta.
    // Dalee collect() sozdaet iz potoka spisok. i u polu4ivwegosq spiska berem iterator. Ego i nuzhno vozvrawat'
  }

  @DataProvider // Ideq razdeleniq dannih ot testa
  public Iterator<Object[]> validGroupsFromJson() throws IOException { //l6_m7 novii DataProvider dlq Json
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")));
    String line = reader.readLine();
    String json = ""; //l6_m7 peremennaq v kot 4itaem soderzhimoe faila
    while (line != null) {
      json += line;
      line = reader.readLine();
    }
    Gson gson = new Gson(); //l6_m7
    List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>() {}.getType()); //l6_m7 Deserialisation v gson: 4itaem vse soderzhimoe faila v peremennyu tipa String(json) i potom ee obrabativaem. Vtoroi parametr-tip dannih kot serializyut'sq
    // v Java nel'zq napisat' vtorim parametrom (json, List<GroupData>.class) t.k. so spiskami deserializaciq ne prohodit. Esli bi bil odin objekt to mozhno: (json, GroupData.class).
    // No u nas spisok objektov, poetomu delaem slozhnie deistviq: new TypeToken<List<GroupData>>() {}.getType() kot po suti zna4it List<GroupData>.class

    return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator(); //l6_m6 k kazhdomy obektu nuzhno primenit' funkciu kot etot obekt tipa GrpoupData zavernet v massiv kot sostoit iz odnogo etogo objekta.
    // Dalee collect() sozdaet iz potoka spisok. i u polu4ivwegosq spiska berem iterator. Ego i nuzhno vozvrawat'
  }

  @Test(enabled = true, dataProvider = "validGroupsFromJson")
  //l6_m7 Parametr dataProvider ukazat' po imeni. Menqem Xml na Json
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


  @Test (enabled = false)
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
