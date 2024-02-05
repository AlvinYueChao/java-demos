package org.example.alvin.algorithm.datastructure.tree;

public class TreeBin<T> {
  T value;
  TreeBin<T> left;
  TreeBin<T> right;

  TreeBin(T value) {
    this.value = value;
    left = null;
    right = null;
  }
}
