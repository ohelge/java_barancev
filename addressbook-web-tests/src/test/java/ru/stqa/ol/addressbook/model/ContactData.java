package ru.stqa.ol.addressbook.model;

public class ContactData {
  private final String firstname;
  //private final String middlename;
  private final String lastname;
  //private final String nickname;
  private final String email;
  private final String address;
  private final String group;

  public ContactData(String firstname, String lastname, String email, String address, String group) { //String group added, video l3_m08
    this.firstname = firstname;
    // this.middlename = middlename;
    this.lastname = lastname;
    //this.nickname = nickname;
    this.email = email;
    this.address = address;
    this.group = group;
  }

  public String getFirstname() {
    return firstname;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
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
}
