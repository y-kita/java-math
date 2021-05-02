/*
 * Vector.java
 *
 * Copyright (c) 2021 KITAMURA Yuichiro
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

package jp.northvillage.math.vector;

/**
 * A vector.
 *
 * @param <V> the type of the vector
 * @param <E> the type of the elements
 * @author KITAMURA Yuichiro
 * @since 1.0
 */
public interface Vector<V extends Vector<V, E>, E> {

  /**
   * Returns the number of elements in this vector.
   *
   * @return the number of elements in this vector
   * @since 1.0
   */
  int size();

  /**
   * Returns the element at the specific position in this vector.
   *
   * @param index index of the element to return
   * @return the element at the specific position in this vector
   * @throws IndexOutOfBoundsException
   *     if the index is out of range {@code (index < 0 || index >= size())}
   * @since 1.0
   */
  E get(int index);

  /**
   * Replaces the element at the specified position in this vector with the specified element
   * (optional operation).
   *
   * @param index index of the element to replace
   * @param newElement element to be stored at the specified position
   * @return element previously at the specified position
   * @throws UnsupportedOperationException if the set operation is not supported by this vector
   * @throws ClassCastException
   *     if the class of the specified element prevents it from being added to this vector
   * @throws NullPointerException
   *     if the specified element is null and this list does not permit null elements
   * @throws IllegalArgumentException
   *     if some property of the specified element prevents it from being added to this vector
   * @throws IndexOutOfBoundsException
   *     if the index is out of range {@code (index < 0 || index >= size())}
   */
  default E set(int index, E newElement) {
    throw new UnsupportedOperationException();
  }

}
