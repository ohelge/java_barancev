package ru.stqa.ol.sandbox;

/**
 * Created by OL on 21/02/2016.
 */
public class Rectangle {

  public double a;
  public double b;
  public Rectangle(double a, double b){
    this.a = a;
    this.b = b;
  }

  public double area (){
    return this.a * this.b;
  }

}
