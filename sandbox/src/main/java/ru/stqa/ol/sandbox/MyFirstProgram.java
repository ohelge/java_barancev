package ru.stqa.ol.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    hello("world");
    hello("Oleg");
    Square s = new Square(5);
    System.out.println("Square of " + s.l + " = " + s.area());
    Rectangle r = new Rectangle(4.0, 6.0);
    System.out.println("Square of " + r.a + " and " + r.b + " = " + r.area());

    Point p1 = new Point(-1, -1);
    Point p2 = new Point(0, 0);

    System.out.println("Distance between " + p1.x + " " + p1.y + " and " + p2.x + " " + p2.y + " = " + distance(p1, p2));
  }

  public static void hello(String smb) {
    System.out.println("Hello, " + smb + "!");
  }

  public static double distance(Point p1, Point p2) {
    return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
  }


}