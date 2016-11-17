package ru.stqa.ol.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by OL A546902 on 2016-11-17.
 */
public class PrimeTests {

  @Test (enabled = false)
  public void testPrimeIntFor(){
    Assert.assertTrue(Primes.isPrimeFor(Integer.MAX_VALUE));
  }
  @Test
  public void testPrimeIntForFast(){
    Assert.assertTrue(Primes.isPrimeForFast(Integer.MAX_VALUE));
  }

  @Test
  public void testPrimeLongForFast(){
    long n = Integer.MAX_VALUE; //zna4enie budet preobrazovanno v long
    Assert.assertTrue(Primes.isPrimeForFast(n));
  }

  @Test  (enabled = false)
  public void testPrimeLongFor(){
    long n = Integer.MAX_VALUE; //zna4enie budet preobrazovanno v long
    Assert.assertTrue(Primes.isPrimeFor(n));
  }


  @Test (enabled = false)
  public void testPrimeIntWhile (){
    Assert.assertTrue(Primes.isPrimeWhile(Integer.MAX_VALUE));
  }

  @Test (enabled = false)
  public void testPrimeLongWhile(){
    long n = Integer.MAX_VALUE; //zna4enie budet preobrazovanno v long
    Assert.assertTrue(Primes.isPrimeWhile(n));
  }

  @Test
  public void testNonPrimeFor (){
    Assert.assertFalse(Primes.isPrimeFor(Integer.MAX_VALUE - 2));
  }

  @Test
  public void testNonPrimeWhile (){
    Assert.assertFalse(Primes.isPrimeWhile(Integer.MAX_VALUE - 2 ));
  }

  @Test
  public void testNonPrimeLongFor(){
    long n = Integer.MAX_VALUE - 2; //zna4enie budet preobrazovanno v long
    Assert.assertFalse(Primes.isPrimeFor(n));
  }

}
