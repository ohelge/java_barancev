package ru.stqa.ol.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("group") //l6_m6 Annotaciq dlq tag group

public class GroupData {

  @XStreamOmitField //l6_m6 Ne zapisivaem nenuzhnii tag <id> v group.xml
  private int id = Integer.MAX_VALUE; // l4_m8 : ubrali "final" ina4e nel'zq sdelat' Code->Generate->Setter
  @Expose //l6_m7 gson Pome4aem polq kot DOLZHNI bit' selializovani, a ne te kot dolzhni bit' propuweni kak v XStreamOmitField. https://github.com/google/gson/blob/master/UserGuide.md#TOC-Gson-s-Expose
  private String groupname;
  @Expose
  private String groupheader;
  @Expose
  private String groupfooter;

  public int getId() {
    return id;
  }

  // Generim setteri i menqem setteri setId na withId etc..Zatem dobavlqem return this i menqem void na GroupData. a zatem udalqem/ kommentim 2 konstruktora
  public GroupData withId(int id) {
    this.id = id;
    return this;
  }

  public GroupData withName(String groupname) {
    this.groupname = groupname;
    return this;
  }

  public GroupData withHeader(String groupheader) {
    this.groupheader = groupheader;
    return this;
  }

  public GroupData withFooter(String groupfooter) {
    this.groupfooter = groupfooter;
    return this;
  }

  public String getName() {
    return groupname;
  }

  public String getHeader() {
    return groupheader;
  }

  public String getFooter() {
    return groupfooter;
  }

  @Override // Code->Generate->toString()
  public String toString() {
    return "GroupData{" +
            "id='" + id + '\'' +
            ", groupname='" + groupname + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    GroupData groupData = (GroupData) o;

    if (id != groupData.id) return false;
    return groupname != null ? groupname.equals(groupData.groupname) : groupData.groupname == null;

  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (groupname != null ? groupname.hashCode() : 0);
    return result;
  }
}
