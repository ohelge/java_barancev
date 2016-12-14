package ru.stqa.ol.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by A546902 on 2016-12-12.
 */
public class Contacts extends ForwardingSet<ContactData> { //google Guava project https://github.com/google/guava/wiki
  private Set<ContactData> delegate;

  public Contacts(Contacts contacts) {
    this.delegate = new HashSet<ContactData>(contacts.delegate);
  }

  public Contacts() {
    this.delegate = new HashSet<ContactData>();
  }

  @Override
  protected Set<ContactData> delegate() {
    return delegate;
  }

  public Contacts withAdded(ContactData contact) {
    Contacts contacts = new Contacts(this); // l5_m6 Konstruktor budet stroit kopiu suwestvuuwego objecta
    contacts.add(contact);
    return contacts;
  }
  public Contacts without(ContactData contact) {
    Contacts contacts = new Contacts(this); // l5_m6 Konstruktor budet stroit kopiu suwestvuuwego objecta
    contacts.remove(contact);
    return contacts;
  }

}
