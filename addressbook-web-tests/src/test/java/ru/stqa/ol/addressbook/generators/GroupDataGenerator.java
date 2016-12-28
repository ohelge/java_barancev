package ru.stqa.ol.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import ru.stqa.ol.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by A546902 on 2016-12-27.
 */
public class GroupDataGenerator {
  @Parameter(names = "-c", description = "Group count")  //l6_m3 Jcommander biblioteka iz Maven. Dobavlqem -c, -f v Configuration klassa
  public int count;
  @Parameter(names = "-f", description = "Target file")
  public String file;//jcommander ne podderzhivaet raboyu s failami poetomu String file. Preobrazuem v fail potom

  @Parameter(names = "-d", description = "Data format")
  public String format;// l6_m6 v Configuration 3i parametr -d xml

  public static void main(String[] args) throws IOException {
    GroupDataGenerator generator = new GroupDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run(); //l6_m3: sozdaem metod run i perenosim v nego List i saveAsCSV
    //l6_m2 : v ka4estve parametrov peredaem koli4estvo grupp i put' k failu
    //int count = Integer.parseInt(args[0]);
    //File file = new File(args[1]);

  }

  private void run() throws IOException {
    List<GroupData> groups = generateGroups(count); // Part1:generaciq dannih
    if (format.equals("scv")) { //l6_m6 proverka na format
      saveAsCSV(groups, new File(file)); // Part2: sohranenie dannih s preobrazovvanim String file v File
    } else if (format.equals("xml")){
      saveAsXml(groups, new File(file)) ;
    } else {
      System.out.println("Unrecognised format: not csv or xml but " + "'" + format + "'");
    }
  }

  private void saveAsXml(List<GroupData> groups, File file) throws IOException { //l6_m6 pervii parammetr eto spisok grupp, 2i eto fail v kot nado sohranqt'
    XStream xstream = new XStream(); //Sozdaem ojekt tipa xstream. http://x-stream.github.io/tutorial.html
    // xstream.alias("group", GroupData.class); // nastroika xstream 4tobi izmenit' tag <ru.stqa.ol.addressbook.model.GroupData>: dlq dannih tipa GroupData ispol'zovat' tag "group" http://x-stream.github.io/tutorial.html
    xstream.processAnnotations(GroupData.class); // vmesto viweukazannogo mozhno ukazat' annotacii v GroupDatam, smotri GroupData. Dlq klassa GroupData nado 4itat' podskazki/annotacii
    String xml = xstream.toXML(groups); // http://x-stream.github.io/tutorial.html
    Writer writer = new FileWriter(file); //kak ran'we delaem writer
    writer.write(xml); // Ne zabit' pomenqt' imq faila v Configuration
    writer.close();
  }

  private List<GroupData> generateGroups(int count) {// ubiraem static posle sozdaniq metoda run
    List<GroupData> groups = new ArrayList<GroupData>();
    for (int i = 0; i < count; i++) {
      groups.add(new GroupData().withName(String.format("Group %s", i))
              .withHeader(String.format("header %s", i))
              .withFooter(String.format("footer %s", i)));
    }
    return groups;
  }

  private void saveAsCSV(List<GroupData> groups, File file) throws IOException { // ubiraem static posle sozdaniq metoda run
    System.out.println(new File(".").getAbsolutePath());//l6_m2 Rabo4aq dir ne takaq kak v testah, a tekuwaq dir projekta C:\Devel\java_barancev\. Menqem Working dir v nastroikah class GroupDataGenerator
    Writer writer = new FileWriter(file);
    for (GroupData group : groups) {
      writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(), group.getFooter()));
    }
    writer.close();
  }
}
