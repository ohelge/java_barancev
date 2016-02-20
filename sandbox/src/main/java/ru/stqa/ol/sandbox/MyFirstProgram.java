package ru.stqa.ol.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    hello("world");
    hello("Oleg");
    double a =5.0;
        System.out.println("Square of " + a + " = " + area(a));
    double b =6.0;
    System.out.println("Square of " + a + " and " + b + " = " + area(a, b));
  }

  public static void hello(String smb) {
    System.out.println("Hello, " + smb + "!");
  }

  public static double area (double len){
        return len * len;
  }

  public static double area (double a, double b){
    return a * b;
  }
}