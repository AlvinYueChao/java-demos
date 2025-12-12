package org.example.alvin.study.jvm;

/**
 * @author AlvinPower
 * @date 2021/1/2
 */
public class DispatchTest {
  public static void main(String[] args) {
    Human human = new Human();
    Human man = new Man();
    Human woman = new Woman();

    human.sayHello(human);
    man.sayHello(man);
    woman.sayHello(woman);
  }

  private static class Human {
    public void sayHello(Human guy) {
      System.out.println("Hello, guy");
    }
  }

  private static class Man extends Human {
    @Override
    public void sayHello(Human guy) {
      System.out.println("Hello, gentleman");
    }
  }

  private static class Woman extends Human {
    @Override
    public void sayHello(Human guy) {
      System.out.println("Hello, lady");
    }
  }
}
