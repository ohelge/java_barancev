package ru.stqa.ol.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by OL on 12/03/2016.
 */
public class SquareTests {

  @Test
  public void testArea() {
    Square s= new Square(5);
    Assert.assertEquals( s.area() , 25.);
    /* Zadanie 3*/
    Point p1 = new Point(-2, 0);
    Point p2 = new Point(0, 0);
    Assert.assertEquals( MyFirstProgram.distance(p1,p2) ,2.);
    Point p3 = new Point(1, 1);
    Point p4 = new Point(0, 0);
    Assert.assertEquals( MyFirstProgram.distance(p3,p4) , Math.sqrt(2));

  }
}
