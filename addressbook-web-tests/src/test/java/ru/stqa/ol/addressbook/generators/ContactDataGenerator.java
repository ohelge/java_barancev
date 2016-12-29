package ru.stqa.ol.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.ol.addressbook.model.ContactData;
import ru.stqa.ol.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by A546902 on 2016-12-29.
 */
public class ContactDataGenerator {
  @Parameter(names = "-c", description = "Contact count")
  //l6_m3 Jcommander biblioteka iz Maven. Dobavlqem -c, -f v Configuration klassa
  public int count;
  @Parameter(names = "-f", description = "Target file")
  public String file;//jcommander ne podderzhivaet raboyu s failami poetomu String file. Preobrazuem v fail potom

  @Parameter(names = "-d", description = "Data format")
  public String format;// l6_m6 v Configuration 3i parametr -d xml

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run(); //l6_m3: sozdaem metod run i perenosim v nego List i saveAsCSV
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count); // Part1:generaciq dannih
    saveAsJson(contacts, new File(file));
  }

  private List<ContactData> generateContacts(int count) {// ubiraem static posle sozdaniq metoda run
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData().withFirstname(String.format("FIRST name %s", i)).withLastname(String.format("LAST name %s", i))
              .withAddress(String.format("ADDRESS\n %s", i)) //v gfaile json viglqdit: "groupheader": "header\n 0"
              .withEmail(String.format("first.last@foo.ru %s", i)));
    }
    return contacts;
  }

  private void saveAsJson(List<ContactData> contacts, File file) throws IOException { //l6_m7 gson Object example: https://github.com/google/gson/blob/master/UserGuide.md#TOC-Object-Examples
    //Gson gson = new Gson();
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create(); //l6_m7 stroim objekt 4tobi fail json viglqdel krasivo a ne v odnu stroku: setPrettyPrinting().
    // Zatem propuskaem vse polq kot NE pome4eni @Expose (sm. ContactData) : excludeFieldsWithoutExposeAnnotation() https://github.com/google/gson/blob/master/UserGuide.md#TOC-Gson-s-Expose
    String json = gson.toJson(contacts);
    Writer writer = new FileWriter(file); //kak ran'we delaem writer
    writer.write(json); // Ne zabit' pomenqt' imq faila v Configuration
    writer.close();
  }

}

