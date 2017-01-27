package ru.stqa.ol.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@XStreamAlias("group") //l6_m6 Annotaciq dlq xml tag group
@Entity //l7_m2 dlq zapuska HbConnectionTest nado sopostavi' klass i imq tablici
@Table (name = "group_list") //l7_m2 nuzna annotacia t.k. objekt naz "groupData", a tablica "group_list"
public class GroupData {

  @XStreamOmitField //l6_m6 Ne zapisivaem nenuzhnii tag <id> v group.xml
  @Id
  @Column (name = "group_id") //l7_m2 nuzna annotacia t.k. atribut naz "id", a stolbec "group_id"
  private int id = Integer.MAX_VALUE; // l4_m8 : ubrali "final" ina4e nel'zq sdelat' Code->Generate->Setter

  @Expose //l6_m7 gson Pome4aem polq kot DOLZHNI bit' selializovani, a ne te kot dolzhni bit' propuweni kak v XStreamOmitField. https://github.com/google/gson/blob/master/UserGuide.md#TOC-Gson-s-Expose
  @Column(name = "group_name") //l7_m2 imq privqzivaetsq k stolbcu "group_name"
  private String groupname;

  @Expose
  @Column (name = "group_header") //l7_m2 analogi4no s header,footer
  @Type(type = "text") //bez etoj annotacii test HbConnectionTest padaet iz-za nesovpadeniq tipov
  private String groupheader;

  @Expose
  @Column (name = "group_footer") //l7_m2 analogi4no s header,footer
  @Type(type = "text") //bez etoj annotacii test HbConnectionTest padaet iz-za nesovpadeniq tipov
  private String groupfooter;

  @ManyToMany(mappedBy = "groups") //l7_m6 Sna4ala delaem v ContactData potom zdes'. Zdes' ManyToMany opisivat' ne nado, a mozhno ukazat' mappedBy-
  //Eto ozna4aet, 4to v parnom klasse nado najti svojstvo/atribut "groups" (private Set<GroupData> groups = new HashSet<GroupData>();) i ottuda vzqt' vse opisanie @JoinTable
  private Set<ContactData> contacts = new HashSet<ContactData>(); // Nuzhno sozdat' Getter i izmenqem ego kak v ContactData:

  public Contacts getContacts() {
    return new Contacts(contacts);
  }

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
    if (groupname != null ? !groupname.equals(groupData.groupname) : groupData.groupname != null) return false;
    if (groupheader != null ? !groupheader.equals(groupData.groupheader) : groupData.groupheader != null) return false;
    return groupfooter != null ? groupfooter.equals(groupData.groupfooter) : groupData.groupfooter == null;

  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (groupname != null ? groupname.hashCode() : 0);
    result = 31 * result + (groupheader != null ? groupheader.hashCode() : 0);
    result = 31 * result + (groupfooter != null ? groupfooter.hashCode() : 0);
    return result;
  }
}
