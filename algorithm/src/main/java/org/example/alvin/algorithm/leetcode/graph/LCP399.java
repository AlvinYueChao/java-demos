package org.example.alvin.algorithm.leetcode.graph;

import java.util.*;

public class LCP399 {
  public static void main(String[] args) {
    List<List<String>> equations = new ArrayList<>();
    equations.add(List.of("a", "b"));
    equations.add(List.of("b", "c"));
    double[] values = new double[]{2.0d, 3.0d};
    List<List<String>> queries = new ArrayList<>();
    queries.add(List.of("a", "c"));
    queries.add(List.of("b", "a"));
    queries.add(List.of("a", "e"));
    queries.add(List.of("a", "a"));
    queries.add(List.of("x", "x"));
    LCP399 lcp399 = new LCP399();
    System.out.println(Arrays.toString(lcp399.calcEquation(equations, values, queries)));
  }

  public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
    int n = equations.size();
    UnionFind uf = new UnionFind(n * 2);
    Map<String, Integer> map = new HashMap<>();
    int index = 0;
    for (int i = 0; i < n; i++) {
      List<String> equation = equations.get(i);
      String a = equation.get(0);
      String b = equation.get(1);
      if (!map.containsKey(a)) {
        map.put(a, index);
        index++;
      }
      if (!map.containsKey(b)) {
        map.put(b, index);
        index++;
      }
      uf.union(map.get(a), map.get(b), values[i]);
    }

    int m = queries.size();
    double[] res = new double[m];
    for (int i = 0; i < m; i++) {
      List<String> query = queries.get(i);
      String a = query.get(0);
      String b = query.get(1);

      Integer id1 = map.get(a);
      Integer id2 = map.get(b);
      if (id1 == null || id2 == null) {
        res[i] = -1.0d;
      } else {
        res[i] = uf.isConnected(id1, id2);
      }
    }

    return res;
  }

  private static class UnionFind {
    private final int[] parent;
    private final double[] weight;

    public UnionFind(int n) {
      parent = new int[n];
      weight = new double[n];
      for (int i = 0; i < n; i++) {
        parent[i] = i;
        weight[i] = 1.0;
      }
    }

    public void union(int x, int y, double value) {
      int rootX = find(x);
      int rootY = find(y);
      if (rootX == rootY) {
        return;
      }
      parent[rootX] = rootY;
      weight[rootX] = weight[y] * value / weight[x];
    }

    public double isConnected(int x, int y) {
      int rootX = find(x);
      int rootY = find(y);
      return rootX == rootY ? weight[x] / weight[y] : -1.0;
    }

    private int find(int x) {
      if (x != parent[x]) {
        int origin = parent[x];
        parent[x] = find(parent[x]);
        weight[x] *= weight[origin];
      }
      return parent[x];
    }
  }
}
