package org.example.alvin.study.jvm;

public class FinalizeGCT {
  public static FinalizeGCT instance = null;

  public void isAlive() {
    System.out.println(Thread.currentThread().getName() + ": I'm still alive");
  }

  @Override
  protected void finalize() throws Throwable {
    super.finalize();
    System.out.println(Thread.currentThread().getName() + ": finalize method executed");
    FinalizeGCT.instance = this;
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
   */
}
