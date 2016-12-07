package ru.stqa.ol.addressbook.model;

public class ContactData {
  public void setId(int id) {
    this.id = id;
  }

  private int id;
  private final String firstname;
  //private final String middlename;
  private final String lastname;
  //private final String nickname;
  private final String email;
  private final String address;
  private final String group;

  public ContactData(int id, String firstname, String lastname, String email, String address, String group) { //String group added, video l3_m08. id added video l4_m7
    this.id = id;
    this.firstname = firstname;
    // this.middlename = middlename;
    this.lastname = lastname;
    //this.nickname = nickname;
    this.email = email;
    this.address = address;
    this.group = group;
  }

  public ContactData(String firstname, String lastname, String email, String address, String group) {
    this.id = Integer.MAX_VALUE;
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.address = address;
    this.group = group;
  }

  public int getId() {
    return id;
  }

  public String getFirstname() {
    return firstname;
  }

  /*public String getMiddlename() {
    return middlename;
  }*/

  public String getLastname() {
    return lastname;
  }

  /*public String getNickname() {
    return nickname;
  }*/

  public String getEmail() {
    return email;
  }

  public String getAddress() {
    return address;
  }

  public String getGroup() {
    return group;
  }
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    return firstname != null ? firstname.equals(that.firstname) : that.firstname == null;

  }

  @Override
  public String toString() {
    return "ContactData{" +
            "firstname='" + firstname + '\'' +
            '}';
  }

  @Override
  public int hashCode() {
    return firstname != null ? firstname.hashCode() : 0;
  }
}
