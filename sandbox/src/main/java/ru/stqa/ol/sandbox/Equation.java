package ru.stqa.ol.sandbox;

/**
 * Created by A546902 on 2016-11-04.
 */
public class Equation {

  private int a;
  private int b;
  private int c;

  private int n; // koli4estvo kornej uravnenija

  public Equation(int a, int b, int c) { //a *x *x + b * x + c
    this.a = a;
    this.b = b;
    this.c = c;

    double d = b * b - 4 * a * c; // diskriminant aka koli4estvo kornej

    if (a != 0) {
      if (d > 0) {
        n = 2;
      } else if (d == 0) {
        n = 1;
      } else {
        n = 0;
      }
    } else if (b != 0) {  // a = 0 System.out.println("Eto virozhdennoe uravnenie"+" a= "+a +" b= "+b +" c= "+c);
      n = 1;
    } else if (c != 0) {
      n = 0;
    } else {
      n = -1; //beskone4ne mnozhestvo reshenij
    }
  }

  public int rootNumber() {
    return n;
  }
}
