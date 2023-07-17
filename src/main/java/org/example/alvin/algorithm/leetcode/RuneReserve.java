package org.example.alvin.algorithm.leetcode;

/** LCP 77 */
public class RuneReserve {
  public static void main(String[] args) {
    int[] runes = new int[] {1, 3, 5, 4, 1, 7};
    System.out.println(runeReserve(runes));
    runes = new int[] {1, 1, 3, 3, 2, 4};
    System.out.println(runeReserve(runes));
    runes = new int[] {1, 3, 4, 7};
    System.out.println(runeReserve(runes));
    runes = new int[] {};
    System.out.println(runeReserve(runes));
    runes = new int[] {1};
    System.out.println(runeReserve(runes));
  }

  private static int runeReserve(int[] runes) {
    // timeout
    //    List<Integer> sortedRunes =
    // Arrays.stream(runes).sorted().boxed().collect(Collectors.toList());
    quickSort(runes, 0, runes.length - 1);
    int result = runes.length == 0 || runes.length == 1 ? runes.length : 0;
    for (int i = 0; i < runes.length - 1; i++) {
      int start = i;
      int end = start + 1;
      int count = 1;
      while (end <= runes.length - 1 && runes[end] - runes[start] <= 1) {
        start = end;
        end = end + 1;
      }
      count = start - i + 1;
      if (count > result) {
        result = count;
      }
    }
    return result;
  }

  private static void quickSort(int[] arr, int low, int high) {
    if (low > high) {
      return;
    }
    int i = low;
    int j = high;
    int pivot = arr[low];

    while (i < j) {
      // 从哪个方向选基准点，则遍历时先便利反方向
      while (pivot <= arr[j] && i < j) {
        j--;
      }
      while (pivot >= arr[i] && i < j) {
        i++;
      }
      if (i < j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
      }
    }
    arr[low] = arr[i];
    arr[i] = pivot;

    // 递归调用左半数组
    quickSort(arr, low, j - 1);
    // 递归调用右半数组
    quickSort(arr, j + 1, high);
  }
}
