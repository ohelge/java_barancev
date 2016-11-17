package ru.stqa.ol.sandbox;

import java.util.Arrays;
import java.util.List;

/**
 * Created by OL A546902 on 2016-11-17.
 */
public class Collections {
  public static void main(String[] args) {

    String[] langs = {"Java", "C#", "Python", "PHP"};  // ili tak: langs[0] = "Java"; langs[1] = "C#";
    for (String l : langs) {    //ili for (int i = 0; i < langs.length; i++ )
      System.out.println("i want to learn " + l);
    }
    System.out.println(" ");

    List<String> languages = Arrays.asList("Java", "C#", "Python", "PHP");  // esli proizvol'nii tip: List languages = Arrays.asList("Java", "C#", "Python", "PHP"); togda: for (Object l : languages)

    /* ili List<String> languages = new ArrayList<String>();
    languages.add("Java");
    languages.add("C#"); etc*/

    for (String l : languages) {
      System.out.println("i want to learn " + l);
    }
  }
}
