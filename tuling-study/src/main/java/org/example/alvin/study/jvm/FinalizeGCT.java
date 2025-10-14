package org.example.alvin.study.jvm;

import java.lang.ref.Cleaner;
import java.util.concurrent.atomic.AtomicReference;

public class FinalizeGCT {
  public static FinalizeGCT instance = null;
  
  // 创建一个静态的Cleaner实例
  private static final Cleaner cleaner = Cleaner.create();
  
  // 保存Cleaner.Cleanable引用，防止被过早清理
  private final Cleaner.Cleanable cleanable;
  
  // 内部状态类，用于清理操作
  private static class State implements Runnable {
    private String name;
    
    State(String name) {
      this.name = name;
    }
    
    @Override
    public void run() {
      System.out.println(name + ": finalize method executed");
      // 注意：在实际Cleaner中无法安全地设置静态变量
      // 因为这会在不同的线程中执行
    }
  }

  public FinalizeGCT() {
    // 注册清理操作
    this.cleanable = cleaner.register(this, new State(Thread.currentThread().getName()));
  }

  public void isAlive() {
    System.out.println(Thread.currentThread().getName() + ": I'm still alive");
  }

  public static void main(String[] args) {
    instance = new FinalizeGCT();
    instance = null;
    System.gc();

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    if (instance == null) {
      System.out.println(Thread.currentThread().getName() + ": I'm dead");
    } else {
      instance.isAlive();
    }

    instance = new FinalizeGCT();
    instance = null;
    System.gc();

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    if (instance == null) {
      System.out.println(Thread.currentThread().getName() + ": I'm dead");
    } else {
      instance.isAlive();
    }
  }

  /**
   * 执行结果： run结果： main: I'm dead Finalizer: finalize method executed
   *
   * <p>line20 线程断点debug结果： Finalizer: finalize method executed main: I'm still alive
   *
   * <p>结果分析： finalize() 方法由Finalizer线程异步执行，其优先级低于其他代码 finalize() 方法只能被执行一次
   * 
   * <p>更新说明： 使用Java 9引入的Cleaner API替代已弃用的finalize()方法
   */
}