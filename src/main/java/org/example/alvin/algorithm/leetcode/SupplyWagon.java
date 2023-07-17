package org.example.alvin.algorithm.leetcode;

import java.util.Arrays;

/** LCP 72 */
public class SupplyWagon {
  public static void main(String[] args) {
    int[] supplies = new int[] {1, 2};
    System.out.println(Arrays.toString(supplyWagon(supplies)));
    supplies = new int[] {7, 3, 6, 1, 8};
    System.out.println(Arrays.toString(supplyWagon(supplies)));
    supplies = new int[] {1, 3, 1, 5};
    System.out.println(Arrays.toString(supplyWagon(supplies)));
  }

  private static int[] supplyWagon(int[] supplies) {
    int stopSignal = supplies.length / 2;
    return supplyWagon(supplies, stopSignal);
  }

  private static int[] supplyWagon(int[] supplies, int stopSignal) {
    int[] newSupplies = new int[supplies.length - 1];
    int[] calculations = new int[supplies.length - 1];
    if (supplies.length == 2) {
      newSupplies[0] = supplies[0] + supplies[1];
      return newSupplies;
    }
    int mergedIndex = 1;
    int minSum = Integer.MAX_VALUE;
    for (int i = 0; i < supplies.length - 1; i++) {
      calculations[i] = supplies[i] + supplies[i + 1];
      mergedIndex = calculations[i] < minSum ? i + 1 : mergedIndex;
      minSum = Math.min(calculations[i], minSum);
    }
    for (int i = 0; i < supplies.length; i++) {
      if (i < mergedIndex - 1) {
        newSupplies[i] = supplies[i];
      } else if (i == mergedIndex - 1) {
        newSupplies[i] = supplies[mergedIndex - 1] + supplies[mergedIndex];
      } else if (i + 1 <= supplies.length - 1) {
        newSupplies[i] = supplies[i + 1];
      }
    }
    return newSupplies.length == stopSignal ? newSupplies : supplyWagon(newSupplies, stopSignal);
  }
}
