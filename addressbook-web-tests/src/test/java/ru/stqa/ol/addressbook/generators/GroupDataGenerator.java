package ru.stqa.ol.addressbook.generators;

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
  public static void main (String [] args) throws IOException {
    //l6_m2 : v ka4estve parametrov peredaem koli4estvo grupp i put' k failu
    int count = Integer.parseInt(args[0]);
    File file = new File(args[1]);
    List<GroupData> groups = generateGroups(count); // Part1:generaciq dannih
    save(groups, file); // Part2: sohranenie dannih
  }

  private static List<GroupData> generateGroups(int count) {
    List <GroupData> groups = new ArrayList<GroupData>();
    for (int i = 0; i < count; i++) {
      groups.add(new GroupData().withName(String.format("Group %s", i))
      .withHeader(String.format("header %s", i))
      .withFooter(String.format("footer %s", i)) );
    }
    return groups;
  }
  private static void save(List<GroupData> groups, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());//l6_m2 Rabo4aq dir ne takaq kak v testah, a tekuwaq dir projekta C:\Devel\java_barancev\. Menqem Working dir v nastroikah class GroupDataGenerator
    Writer writer = new FileWriter(file);
    for (GroupData group : groups) {
      writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(), group.getFooter()));
    }
    writer.close();
  }
}
