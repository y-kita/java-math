/*
 * DoubleComplexTest.java
 *
 * Copyright (c) 2021 yuichiro
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

package jp.northvillage.math.complex;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

/**
 * The unit test for the {@link DoubleComplex} class.
 *
 * @author KITAMURA Yuichiro
 * @since 1.0
 */
class DoubleComplexTest {

  /**
   * The unit test for the {@link DoubleComplex#doubleReal()} and {@link DoubleComplex#doubleImaginary()} methods.
   *
   * @since 1.0
   */
  @Test
  void testDoubleRealAndDoubleImaginary() {
    final double doubleReal = 123.;
    final double doubleImaginary = 456.;

    final DoubleComplex complex = DoubleComplex.valuesOf(doubleReal, doubleImaginary);

    assertEquals(doubleReal, complex.doubleReal());
    assertEquals(doubleImaginary, complex.doubleImaginary());
  }

  /**
   * The unit tests for the {@link DoubleComplex#real()} and {@link DoubleComplex#imaginary()} methods.
   *
   * @since 1.0
   */
  @Test
  void testRealAndImaginary() {
    final double doubleReal = 123.;
    final double doubleImaginary = 456.;

    final DoubleComplex complex = DoubleComplex.valuesOf(doubleReal, doubleImaginary);

    assertEquals(doubleReal, complex.real().doubleValue());
    assertEquals(doubleImaginary, complex.imaginary().doubleValue());
  }

  /**
   * The unit test for the {@link DoubleComplex#addedTo(DoubleComplex)} method.
   */
  @Test
  void testAddedToDoubleComplex() {
    final double a = 123.;
    final double b = 234.;
    final double c = 345.;
    final double d = 456.;

    final DoubleComplex z1 = DoubleComplex.valuesOf(a, b);
    final DoubleComplex z2 = DoubleComplex.valuesOf(c, d);
    final DoubleComplex z3 = z1.addedTo(z2);

    assertEquals(a + c, z3.doubleReal());
    assertEquals(b + d, z3.doubleImaginary());

    assertThrows(NullPointerException.class, () -> z1.addedTo(null));
  }

  /**
   * The unit test for the {@link DoubleComplex#subtractedFrom(DoubleComplex)} method.
   */
  @Test
  void testSubtractedFromDoubleComplex() {
    final double a = 123.;
    final double b = 234.;
    final double c = 345.;
    final double d = 456.;

    final DoubleComplex z1 = DoubleComplex.valuesOf(a, b);
    final DoubleComplex z2 = DoubleComplex.valuesOf(c, d);
    final DoubleComplex z3 = z1.subtractedFrom(z2);

    assertEquals(a - c, z3.doubleReal());
    assertEquals(b - d, z3.doubleImaginary());

    assertThrows(NullPointerException.class, () -> z1.subtractedFrom(null));
  }

  /**
   * The unit test for the {@link DoubleComplex#multipliedBy(DoubleComplex)} method.
   */
  @Test
  void testMultipliedByDoubleComplex() {
    final double a = 123.;
    final double b = 234.;
    final double c = 345.;
    final double d = 456.;

    final DoubleComplex z1 = DoubleComplex.valuesOf(a, b);
    final DoubleComplex z2 = DoubleComplex.valuesOf(c, d);
    final DoubleComplex z3 = z1.multipliedBy(z2);

    assertEquals(a * c - b * d, z3.doubleReal());
    assertEquals(a * d + b * c, z3.doubleImaginary());

    assertThrows(NullPointerException.class, () -> z1.multipliedBy(null));
  }

  /**
   * The unit test for the {@link DoubleComplex#dividedBy(DoubleComplex)} method.
   */
  @Test
  void testDividedByDoubleComplex() {
    final double a = 123.;
    final double b = 234.;
    final double c = 345.;
    final double d = 456.;

    final DoubleComplex z1 = DoubleComplex.valuesOf(a, b);
    final DoubleComplex z2 = DoubleComplex.valuesOf(c, d);
    final DoubleComplex z3 = z1.dividedBy(z2);

    double s = c * c + d * d;

    assertEquals((a * c + b * d) / s, z3.doubleReal());
    assertEquals((a * d - b * c) / s, z3.doubleImaginary());

    assertThrows(ArithmeticException.class, () -> z1.dividedBy(DoubleComplex.valuesOf(0., 0.)));
    assertThrows(NullPointerException.class, () -> z1.dividedBy(null));
  }

  /**
   * The unit test for the {@link DoubleComplex#valuesOf(Double, Double)} method.
   */
  @Test
  void testValuesOfDoubleDouble() {
    final Double real = 123.;
    final Double imaginary = 456.;

    assertThrows(NullPointerException.class, () -> DoubleComplex.valuesOf(null, imaginary));
    assertThrows(NullPointerException.class, () -> DoubleComplex.valuesOf(real, null));

    final DoubleComplex complex = DoubleComplex.valuesOf(real, imaginary);
    assertEquals(real, complex.real());
    assertEquals(imaginary, complex.imaginary());
  }

  /**
   * The unit test for the instances.
   */
  @Test
  void testInstances() {
    assertEquals(DoubleComplex.valuesOf(0., 0.), DoubleComplex.valuesOf(0., 0.));
    assertEquals(DoubleComplex.valuesOf(1., 0.), DoubleComplex.valuesOf(1., 0.));
    assertEquals(DoubleComplex.valuesOf(0., 1.), DoubleComplex.valuesOf(0., 1.));
  }

  /**
   * The unit test for the {@link DoubleComplex#equals(Object)} method.
   */
  @SuppressWarnings("SimplifiableAssertion")
  @Test
  void testEqualsObject() {
    DoubleComplex x = DoubleComplex.valuesOf(123., 456.);
    DoubleComplex y = DoubleComplex.valuesOf(123., 456.);
    DoubleComplex z = DoubleComplex.valuesOf(123., 456.);
    DoubleComplex a = DoubleComplex.valuesOf(234., 456.);
    DoubleComplex b = DoubleComplex.valuesOf(123., 567.);
    DoubleComplex c = DoubleComplex.valuesOf(234., 567.);

    //noinspection EqualsWithItself
    assertTrue(x.equals(x)); // reflexive

    assertTrue(x.equals(y)); // symmetric
    assertTrue(y.equals(x));

    assertTrue(x.equals(z)); // transitive
    assertTrue(y.equals(z));
    assertTrue(x.equals(z));

    assertTrue(x.equals(y)); // consistent
    Stream.of(a, b, c).forEach(o -> {
      assertFalse(x.equals(o));
      assertFalse(x.equals(o));
    });

    //noinspection ConstantConditions
    assertFalse(x.equals(null));
  }

  /**
   * The unit test for the {@link DoubleComplex#hashCode()} method.
   */
  @Test
  void testHashCode() {
    DoubleComplex x = DoubleComplex.valuesOf(123., 456.);
    DoubleComplex y = DoubleComplex.valuesOf(123., 456.);
    DoubleComplex a = DoubleComplex.valuesOf(234., 456.);
    DoubleComplex b = DoubleComplex.valuesOf(123., 567.);
    DoubleComplex c = DoubleComplex.valuesOf(234., 567.);

    assertEquals(x.hashCode(), x.hashCode());
    assertEquals(x.hashCode(), y.hashCode());

    Stream.of(a, b, c).forEach(o -> assertNotEquals(x.hashCode(), o.hashCode()));
  }

  @Test
  void testToString() {
    assertEquals("123.0+456.0i", DoubleComplex.valuesOf(123., 456.).toString());
    assertEquals("123.0-456.0i", DoubleComplex.valuesOf(123., -456.).toString());
    assertEquals("-123.0+456.0i", DoubleComplex.valuesOf(-123., 456.).toString());
    assertEquals("123.0+0.0i", DoubleComplex.valuesOf(123., 0.).toString());
    assertEquals("0.0+456.0i", DoubleComplex.valuesOf(0., 456.).toString());
  }

}