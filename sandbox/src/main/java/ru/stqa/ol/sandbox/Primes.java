package ru.stqa.ol.sandbox;

/**
 * Created by OL A546902 on 2016-11-17.
 */
public class Primes {
  public static boolean isPrimeFor(int n) {
    for (int i = 2; i < n; i++) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }

  // same with while:
  public static boolean isPrimeWhile(int n) {
    int i = 2;
    while (i < n && n % i != 0) {
      i++;
    }
    return i == n;
  }

  public static boolean isPrimeForFast(int n) {
    int m = (int) Math.sqrt(n);
    for (int i = 2; i < m; i++) { // delim na deliteli do kornq iz n
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }

  public static boolean isPrimeForFast(long n) {
    long m = (long) Math.sqrt(n);
    for (long i = 2; i < m ; i++) { //delim na deliteli do kornq iz n
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }


  public static boolean isPrimeFor(long n) {  //OBS! imq funkcii odinakovoe, vibor avtomati4 v zavisimosti ot tipa peremennoi "n"
    for (long i = 2; i < n; i++) {
      if (n % i == 0) {
        return false;
      }

    }
    return true;
  }

  public static boolean isPrimeWhile(long n) {
    long i = 2;
    while (i < n && n % i != 0) {
      i++;
    }
    return i == n;
  }
}
