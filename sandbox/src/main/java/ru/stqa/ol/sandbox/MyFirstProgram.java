package ru.stqa.ol.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    hello("world");
    hello("Oleg");
    Square s = new Square (5);

        System.out.println("Square of " + s.l + " = " + s.area());
    Rectangle r = new Rectangle(4.0, 6.0);
     System.out.println("Square of " + r.a + " and " + r.b + " = " + r.area());
  }

  public static void hello(String smb) {
    System.out.println("Hello, " + smb + "!");
  }



}