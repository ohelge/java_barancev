package ru.stqa.ol.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.ol.addressbook.model.ContactData;
import ru.stqa.ol.addressbook.model.Contacts;
import ru.stqa.ol.addressbook.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider // Ideq razdeleniq dannih ot testa
  public Iterator<Object[]> validContactsFromJson() throws IOException { //l6_m7 novii DataProvider dlq Json
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
    String line = reader.readLine();
    String json = ""; //l6_m7 peremennaq v kot 4itaem soderzhimoe faila
    while (line != null) {
      json += line;
      line = reader.readLine();
    }
    Gson gson = new Gson(); //l6_m7
    List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {}.getType()); //l6_m7 Deserialisation v gson: 4itaem vse soderzhimoe faila v peremennyu tipa String(json) i potom ee obrabativaem. Vtoroi parametr-tip dannih kot serializyut'sq
    // v Java nel'zq napisat' vtorim parametrom (json, List<GroupData>.class) t.k. so spiskami deserializaciq ne prohodit. Esli bi bil odin objekt to mozhno: (json, GroupData.class).
    // No u nas spisok objektov, poetomu delaem slozhnie deistviq: new TypeToken<List<GroupData>>() {}.getType() kot po suti zna4it List<GroupData>.class

    return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator(); //l6_m6 k kazhdomy obektu nuzhno primenit' funkciu kot etot obekt tipa GrpoupData zavernet v massiv kot sostoit iz odnogo etogo objekta.
    // Dalee collect() sozdaet iz potoka spisok. i u polu4ivwegosq spiska berem iterator. Ego i nuzhno vozvrawat'
  }

  @Test (enabled = true, dataProvider = "validContactsFromJson")
  public void contactCreation(ContactData contact) {
    app.goTo().contactPage();
    Contacts before = app.contact().all();
    app.contact().create(contact);

    assertThat(app.contact().count(), equalTo (before.size() + 1) );
    Contacts after = app.contact().all();

    contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt() );

    assertThat(after, equalTo(before.withAdded(contact)) );

  }
  @Test (enabled = false)
  public void testCurrentDir() {
    //l6_m1: esli File currentDir = new File(".") to sout ukazhet kornevyu/tekywyu direktoriu C:\Devel\java_barancev\addressbook-web-tests\.
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath() );
    File photo = new File("src\\test\\resources\\DoubleOL_foto2.jpg"); //C:\Devel\java_barancev\addressbook-web-tests\src\test\resources\DoubleOL_foto2.jpg
    System.out.println(photo.getAbsolutePath() );
    System.out.println(photo.exists());

  }

  @Test (enabled = false)
  public void contactBadCreation() {
    app.goTo().contactPage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstname("1111 ' ");
    app.contact().create(contact);

    assertThat(app.contact().count(), equalTo (before.size() ) );
    Contacts after = app.contact().all();

    contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt() );

    assertThat(after, equalTo(before) );

  }

}
