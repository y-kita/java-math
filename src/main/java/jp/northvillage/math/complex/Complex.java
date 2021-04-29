/*
 * Complex.java
 *
 * Copyright (c) 2021 KITAMURA Yuichiro
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

package jp.northvillage.math.complex;

/**
 * A complex number.
 *
 * @param <Z> the type of the complex number
 * @param <R> the type of the real part
 * @author KITAMURA Yuichiro
 * @since 1.0
 */
public interface Complex<Z extends Complex<Z, R>, R> {

  /**
   * Returns the real part of this complex number.
   *
   * @return the real part of this complex number
   * @since 1.0
   */
  R real();

  /**
   * Returns the imaginary part of this complex number as a real number.
   *
   * @return the imaginary part of this complex number as a real number
   * @since 1.0
   */
  R imaginary();

  /**
   * Return the summation of this complex number and the other complex number.
   *
   * @param other the complex number as a addend
   * @return the summation of this complex number and the other complex number
   * @throws NullPointerException the complex number as a addend is {@code null}
   * @since 1.0
   */
  Z addedTo(Z other);

  /**
   * Returns the difference between this complex number and the other complex number.
   *
   * @param other the complex number as a subtrahend
   * @return the difference between this complex number and the other complex number
   * @throws NullPointerException the complex number as a subtrahend is {@code null}
   * @since 1.0
   */
  Z subtractedFrom(Z other);

  /**
   * Returns the product of this complex number and the other complex number.
   *
   * @param other the complex number as a multiplicand
   * @return the product of this complex number and the other complex number
   * @throws NullPointerException the complex number as a multiplicand is {@code null}
   * @since 1.0
   */
  Z multipliedBy(Z other);

  /**
   * Returns the quotient of this complex number and the other complex number.
   *
   * @param other the complex number as a divisor
   * @return the quotient of this complex number and the other complex number
   * @throws ArithmeticException  the complex number as a divisor is 0
   * @throws NullPointerException the complex number as a divisor is {@code null}
   * @since 1.0
   */
  Z dividedBy(Z other);

}
