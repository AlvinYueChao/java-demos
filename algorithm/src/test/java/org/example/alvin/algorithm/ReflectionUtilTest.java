package org.example.alvin.algorithm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ReflectionUtilTest {

  record TargetClass(String name) {
    // 1. 无参构造
    public TargetClass() {
      this("Default");
      System.out.println(">> 无参构造被调用");
    }

    // 2. 有参构造
    public TargetClass(String name) {
      this.name = name;
      System.out.println(">> 有参构造被调用: " + name);
    }

    // 3. 无参有返回值
    public String getName() {
      System.out.println(">> getName 被调用");
      return this.name;
    }

    // 4. 有参无返回值 (int 参数)
    public void printAge(int age) {
      System.out.println(">> printAge 被调用, Age: " + age);
    }

    // 5. 多参方法
    public void complex(String action, int count) {
      System.out.println(">> complex 被调用: " + action + " * " + count);
    }
  }

  @Test
  void testConstructorWithParam() {
    Object instance = ReflectionUtil.execute(TargetClass.class, null, "Gemini User");
    Assertions.assertTrue(instance instanceof TargetClass);
  }

  @Test
  void testConstructorWithoutParam() {
    Object instance = ReflectionUtil.execute(TargetClass.class, null);
    Assertions.assertTrue(instance instanceof TargetClass);
  }

  @Test
  void testMethodWithParamButWithoutReturn() {
    Assertions.assertDoesNotThrow(() -> ReflectionUtil.execute(new TargetClass(), "printAge", 18));
  }

  @Test
  void testMethodWithReturnButWithoutParam() {
    Object result = ReflectionUtil.execute(new TargetClass(), "getName");
    Assertions.assertEquals("Default", result);
  }

  @Test
  void testMethodWithMultipleParamsButWithoutReturn() {
    Assertions.assertDoesNotThrow(() -> ReflectionUtil.execute(new TargetClass(), "complex", "print", 5));
  }
}
