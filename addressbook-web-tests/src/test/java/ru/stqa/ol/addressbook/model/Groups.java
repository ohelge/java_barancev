package ru.stqa.ol.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by OL A546902 on 2016-12-12.
 */
public class Groups extends ForwardingSet<GroupData> { //google Guava project https://github.com/google/guava/wiki
  private Set<GroupData> delegate;

  public Groups(Groups groups) {
    this.delegate = new HashSet<GroupData>(groups.delegate);
  }

  public Groups() {
    this.delegate = new HashSet<GroupData>();
  }

  @Override
  protected Set<GroupData> delegate() {
    return delegate;
  }

  public Groups withAdded(GroupData group) {
    Groups groups = new Groups(this); // l5_m6 Konstruktor budet stroit kopiu suwestvuuwego objecta
    groups.add(group);
    return groups;
  }
  public Groups without(GroupData group) {
    Groups groups = new Groups(this); // l5_m6 Konstruktor budet stroit kopiu suwestvuuwego objecta
    groups.remove(group);
    return groups;
  }
}
