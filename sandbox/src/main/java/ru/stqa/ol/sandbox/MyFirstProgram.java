package ru.stqa.ol.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    hello("world");
    hello("Oleg");
    Square s = new Square (5);

        System.out.println("Square of " + s.l + " = " + area(s));
    Rectangle r = new Rectangle(4.0, 6.0);
     System.out.println("Square of " + r.a + " and " + r.b + " = " + area(r));
  }

  public static void hello(String smb) {
    System.out.println("Hello, " + smb + "!");
  }

  public static double area (Square s){
        return s.l * s.l;
  }

  public static double area (Rectangle r){
    return r.a * r.b;
  }
}