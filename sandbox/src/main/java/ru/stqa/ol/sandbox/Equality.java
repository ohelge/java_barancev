package ru.stqa.ol.sandbox;

/**
 * Created by A546902 on 2016-11-07.
 */
public class Equality {
  public static void main(String args[]) {
    String s1= "fifefox";
    //String s2 = s1;
    // String s2 = new String (s1);
    String s2 = "fire" + "fox";

    System.out.println(s1 == s2); // OBS false when String s2 = new String (s1); watch video l3_m04
    System.out.println(s1.equals(s2));
  }
}
