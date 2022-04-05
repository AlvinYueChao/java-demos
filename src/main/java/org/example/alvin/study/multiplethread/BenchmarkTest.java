package org.example.alvin.study.multiplethread;

import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * @author AlvinPower
 * @date 2021/3/14
 */
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3)
@Measurement(iterations = 5)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class BenchmarkTest {
  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder().include(BenchmarkTest.class.getSimpleName()).build();
    new Runner(opt).run();
  }

  @Benchmark
  public void a() {
    int i = 0;
    i++;
  }

  @Benchmark
  public void b() {
    int i = 0;
    Object o = new Object();
    synchronized (o) {
      i++;
    }
  }
}
