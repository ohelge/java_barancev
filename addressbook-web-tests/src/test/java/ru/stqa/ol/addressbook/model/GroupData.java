package ru.stqa.ol.addressbook.model;

public class GroupData {
  private final String id;
  private final String groupname;
  private final String groupheader;
  private final String groupfooter;

  public GroupData(String id, String groupname, String groupheader, String groupfooter) {
    this.id = id;
    this.groupname = groupname;
    this.groupheader = groupheader;
    this.groupfooter = groupfooter;
  }
  public GroupData(String groupname, String groupheader, String groupfooter) {
    this.id = null;
    this.groupname = groupname;
    this.groupheader = groupheader;
    this.groupfooter = groupfooter;
  }

  public String getId() {
    return id;
  }

  public String getGroupname() {
    return groupname;
  }

  @Override //Code->Generate->equals() and hashCode()
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    GroupData groupData = (GroupData) o;

    if (id != null ? !id.equals(groupData.id) : groupData.id != null) return false;
    return groupname != null ? groupname.equals(groupData.groupname) : groupData.groupname == null;

  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (groupname != null ? groupname.hashCode() : 0);
    return result;
  }

  @Override // Code->Generate->toString()
  public String toString() {
    return "GroupData{" +
            "id='" + id + '\'' +
            ", groupname='" + groupname + '\'' +
            '}';
  }

  public String getGroupheader() {
    return groupheader;
  }

  public String getGroupfooter() {
    return groupfooter;
  }
}
