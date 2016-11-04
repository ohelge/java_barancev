package ru.stqa.ol.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Ol A546902 on 2016-11-04.
 */
public class EquationTests {
  @Test
  public void test0() {
    Equation e = new Equation(1, 1, 1); //x*x + x + 1 = 0
    Assert.assertEquals(e.rootNumber(), 0); //net reshenij
  }

  @Test
  public void test1() {
    Equation e = new Equation(1, 2, 1); //x*x + 2x + 1 = 0
    Assert.assertEquals(e.rootNumber(), 1); // odno reshenie
  }

  @Test
  public void test3() {
    Equation e = new Equation(1, 5, 6); //x*x + 5x + 6 = 0
    Assert.assertEquals(e.rootNumber(), 2); // dva reshwnija
  }
  @Test
  public void testLinear() {
    Equation e = new Equation(0, 1, 1); //      x + 1 = 0
    Assert.assertEquals(e.rootNumber(), 1); // odno reshenije
  }

  @Test
  public void testConstant() {
    Equation e = new Equation(0, 0, 1); //      1 = 0
    Assert.assertEquals(e.rootNumber(), 0); // net reshenij
  }
  @Test
  public void testInfinity() {
    Equation e = new Equation(0, 0, 0); //      0 = 0
    Assert.assertEquals(e.rootNumber(), -1); // beskone4noe mnozhestvo reshenij
  }
}
