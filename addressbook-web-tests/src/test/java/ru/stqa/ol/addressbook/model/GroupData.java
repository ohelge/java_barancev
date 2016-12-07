package ru.stqa.ol.addressbook.model;

public class GroupData {

  private int id = Integer.MAX_VALUE; // l4_m8 : ubrali "final" ina4e nel'zq sdelat' Code->Generate->Setter
  private String groupname;
  private String groupheader;
  private String groupfooter;

 /* //  2 konstruktora
  public GroupData(int id, String groupname, String groupheader, String groupfooter) {
    this.id = id;
    this.groupname = groupname;
    this.groupheader = groupheader;
    this.groupfooter = groupfooter;
  }
  public GroupData(String groupname, String groupheader, String groupfooter) {
    this.id = Integer.MAX_VALUE;
    this.groupname = groupname;
    this.groupheader = groupheader;
    this.groupfooter = groupfooter;
  }*/

  public int getId() {
    return id;
  }

  // Generim setteri i menqem setteri setId na withId etc..Zatem dobavlqem return this i menqem void na GroupData. a zatem udalqem/ kommentim 2 konstruktora
  public GroupData withId(int id) {
    this.id = id;
    return this;
  }

  public GroupData withGroupname(String groupname) {
    this.groupname = groupname;
    return this;
  }

  public GroupData withGroupheader(String groupheader) {
    this.groupheader = groupheader;
    return this;
  }

  public GroupData withGroupfooter(String groupfooter) {
    this.groupfooter = groupfooter;
    return this;
  }

  public String getGroupname() {
    return groupname;
  }

  public String getGroupheader() {
    return groupheader;
  }

  public String getGroupfooter() {
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

    return groupname != null ? groupname.equals(groupData.groupname) : groupData.groupname == null;

  }

  @Override
  public int hashCode() {
    return groupname != null ? groupname.hashCode() : 0;
  }
}
