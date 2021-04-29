/*
 * DoubleComplex.java
 *
 * Copyright (c) 2021 KITAMURA Yuichiro
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

package jp.northvillage.math.complex;

import java.util.Objects;

/**
 * A complex number with primitive double-valued parts.
 *
 * @author KITAMURA Yuichiro
 * @since 1.0
 */
public abstract class DoubleComplex implements Complex<DoubleComplex, Double> {

  private DoubleComplex() {}

  @Override
  public Double real() {
    return this.doubleReal();
  }

  /**
   * Returns the real part of this complex number.
   *
   * @return the real part of this complex number
   * @see #real()
   * @since 1.0
   */
  public abstract double doubleReal();

  @Override
  public Double imaginary() {
    return this.doubleImaginary();
  }

  /**
   * Returns the imaginary part of this complex number.
   *
   * @return the imaginary part of this complex number
   * @see #imaginary()
   * @since 1.0
   */
  public abstract double doubleImaginary();

  @Override
  public DoubleComplex addedTo(DoubleComplex other) {
    Objects.requireNonNull(other);
    return valuesOf(
        this.doubleReal() + other.doubleReal(),
        this.doubleImaginary() + other.doubleImaginary());
  }

  @Override
  public DoubleComplex subtractedFrom(DoubleComplex other) {
    Objects.requireNonNull(other);
    return valuesOf(
        this.doubleReal() - other.doubleReal(),
        this.doubleImaginary() - other.doubleImaginary());
  }

  @Override
  public DoubleComplex multipliedBy(DoubleComplex other) {
    Objects.requireNonNull(other);
    return valuesOf(
        this.doubleReal() * other.doubleReal() - this.doubleImaginary() * other.doubleImaginary(),
        this.doubleReal() * other.doubleImaginary() + this.doubleImaginary() * other.doubleReal());
  }

  @Override
  public DoubleComplex dividedBy(DoubleComplex other) {
    Objects.requireNonNull(other);
    final double scale
        = other.doubleReal() * other.doubleReal()
        + other.doubleImaginary() * other.doubleImaginary();
    if (Double.compare(scale, 0.) == 0)
      throw new ArithmeticException("OTHER is ZERO.");

    return valuesOf(
        (this.doubleReal() * other.doubleReal() + this.doubleImaginary() * other.doubleImaginary())
            / scale,
        (this.doubleReal() * other.doubleImaginary() - this.doubleImaginary() * other.doubleReal())
            / scale);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    DoubleComplex that = (DoubleComplex) o;

    return Double.compare(
        that.doubleReal(), this.doubleReal()) == 0
        && Double.compare(that.doubleImaginary(), this.doubleImaginary()) == 0;
  }

  @Override
  public int hashCode() {
    int result = DoubleComplex.class.hashCode();
    result = 31 * result + Double.hashCode(this.doubleReal());
    result = 31 * result + Double.hashCode(this.doubleImaginary());

    return result;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder(Double.toString(this.doubleReal()));

    if (Double.compare(this.doubleImaginary(), 0) >= 0) {
      builder.append("+");
    }
    builder.append(this.doubleImaginary());
    builder.append("i");

    return builder.toString();
  }

  /**
   * Returns a complex number with the specified real number and the specified imaginary number.
   *
   * @param real      the real number to be specified
   * @param imaginary the imaginary number to be specified
   * @return a complex number with the specified real number and the specified imaginary number
   * @throws NullPointerException the real number to be specified is {@code null},
   *     the imaginary number to be specified is {@code null}
   */
  public static DoubleComplex valuesOf(Double real, Double imaginary) {
    return valuesOf(
        Objects.requireNonNull(real).doubleValue(),
        Objects.requireNonNull(imaginary).doubleValue());
  }

  /**
   * Returns a complex number with the specified real number and the specified imaginary number.
   *
   * @param doubleReal      the real number to be specified
   * @param doubleImaginary the imaginary number to be specified
   * @return a complex number with the specified real number and the specified imaginary number
   */
  public static DoubleComplex valuesOf(double doubleReal, double doubleImaginary) {
    if (Double.compare(doubleReal, 0.) == 0) {
      if (Double.compare(doubleImaginary, 0.) == 0) {
        return ConcreteDoubleComplex.ZERO;
      } else if (Double.compare(doubleImaginary, 1.) == 0) {
        return ConcreteDoubleComplex.ONE_I;
      }
    } else if (Double.compare(doubleReal, 1.) == 0 && Double.compare(doubleImaginary, 0.) == 0) {
      return ConcreteDoubleComplex.ONE;
    }

    return new ConcreteDoubleComplex(doubleReal, doubleImaginary);
  }

  /**
   * A concrete implementation of the {@link DoubleComplex} class.
   */
  private static final class ConcreteDoubleComplex extends DoubleComplex {

    /**
     * The real part of this complex number.
     */
    private final double doubleReal;

    /**
     * The imaginary part of this complex number.
     */
    private final double doubleImaginary;

    /**
     * Constructs a complex number
     * with the specified real number and the specified imaginary number.
     *
     * @param doubleReal      the real number to be specified
     * @param doubleImaginary the imaginary number to be specified
     */
    private ConcreteDoubleComplex(double doubleReal, double doubleImaginary) {
      this.doubleReal = doubleReal;
      this.doubleImaginary = doubleImaginary;
    }

    @Override
    public double doubleReal() {
      return this.doubleReal;
    }

    @Override
    public double doubleImaginary() {
      return this.doubleImaginary;
    }

    /**
     * The complex number \( 0 \).
     */
    public static final DoubleComplex ZERO = new ConcreteDoubleComplex(0., 0.);

    /**
     * The complex number \( 1 \).
     */
    public static final DoubleComplex ONE = new ConcreteDoubleComplex(1., 0.);

    /**
     * The complex number \( -i \).
     */
    public static final DoubleComplex ONE_I = new ConcreteDoubleComplex(0., 1.);

  }

}
