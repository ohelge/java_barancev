package ru.stqa.ol.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Entity //l7_m2 dlq zapuska HbConnectionTest nado sopostavi' klass i imq tablici
@Table(name = "addressbook")
public class ContactData {
  @Id
  @Column(name = "id") // Mozhno ne ukazivat' @Column t.k. kolonka sovpadaet s peremennoi
  private int id = Integer.MAX_VALUE;

  @Expose
  @Column(name = "firstname") //l7_m3 Mozhno  ne ukazivat' t.k. kolonka sovpadaet s peremennoi
  private String firstname;

  @Expose
  @Column(name = "lastname") //l7_m3 Mozhno  ne ukazivat' t.k. kolonka sovpadaet s peremennoi
  private String lastname;

  @Expose
  @Transient
  private String email;
  @Transient
  private String email2;
  @Transient
  private String email3;

  @Expose
  @Type(type = "text")
  private String address;

  @Transient //l7_m3 Transient, 4tobi pole ne izvlekaetsq iz bd
  private String allPnones; //l5_m11: dobavili stroku so vsemi tel

  @Column(name = "home")
  @Type(type = "text")
  private String homePhone;

  @Column(name = "mobile")
  @Type(type = "text")
  private String mobilePhone;

  @Column (name = "work")
  @Type(type = "text")
  private String workPhone;

  transient private String allEmails; //l7_m3 Transient (drugoui variant napisaniq), 4tobi pole ne izvlekaetsq iz bd.

  @Expose
  @Column(name = "photo")
  @Type(type = "text")
  private String photoFileName;

  //@ManyToMany //l7_m6 Hibernate annotaciq Many2Many http://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/Hibernate_User_Guide.html#associations-many-to-many
  @ManyToMany (fetch = FetchType.EAGER) //l7_m6 delaem Zhadni (EAGER). Po umoll4aniu opciq = .LAZY (iz bd izvlekaetsq kak mozhno men'we iformacii)
  @JoinTable (name = "address_in_groups",
          joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id")) //l7_m6 Zaglqdivaem v bd, smotrim kak svqzivautsq adresa i gruppi: tablica address_in_groups.
  // Stolbec v joinColumns ukaz na stolbec tekuwego klassa t.e. na kontakti, t.e. stolbec "id"
  //inverseJoinColumn eto obratynii stolbec, tot kot ukazivaet na gruppu. Dalee sm GroupData
  private Set<GroupData> groups = new HashSet<GroupData>(); //nado inicializirovat': = new .. Delaem Generate-Getter.

  public Groups getGroups() { //l7_m6 menqem Set na Groups. Vozvrawaem Groups
    return new Groups (groups);
  }

  public String getPhotoFileName() {
    return photoFileName;
  }

  public ContactData withPhotoFileName(String photoFileName) {
    this.photoFileName = photoFileName;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withAllPhones(String allPnones) { //l5_m11:Setter
    this.allPnones = allPnones;
    return this;
  }

  public String getAllPnones() { //l5_m11:Getter
    return allPnones;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public int getId() {
    return id;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getEmail() {
    return email;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public String getAddress() {
    return address;
  }

  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public ContactData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public String getHomePhone() {

    return homePhone;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    return firstname != null ? firstname.equals(that.firstname) : that.firstname == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            '}';
  }

}
