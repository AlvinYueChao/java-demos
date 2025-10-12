package org.example.alvin.algorithm;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class BenchmarkDemos {

  private int[][] arr1 = {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
  private int[][] arr2 = {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
  private boolean[] rows = {true, false, false};
  private boolean[] cols = {true, false, false, true};

  @Benchmark
  public void test1() {
    for (int i = 0; i < rows.length; i++) {
      for (int j = 0; j < cols.length; j++) {
        if (rows[i] || cols[j]) {
          arr1[i][j] = 0;
        }
      }
    }
  }

  @Benchmark
  public void test2() {
    for (int i = 0; i < rows.length; i++) {
      if (rows[i]) {
        Arrays.fill(arr2[i], 0);
        continue;
      }
      for (int j = 0; j < cols.length; j++) {
        if (cols[j]) {
          arr2[i][j] = 0;
        }
      }
    }
  }

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
      .include(BenchmarkDemos.class.getSimpleName())
      .build();
    new Runner(opt).run();
  }
}
