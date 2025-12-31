package org.example.alvin.algorithm;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ReflectionUtil {

  /**
   * 基础类型映射表（用于解决 Integer.class 无法匹配 int.class 的问题）
   */
  private static final Map<Class<?>, Class<?>> PRIMITIVE_MAP = new HashMap<>();

  static {
    PRIMITIVE_MAP.put(Integer.class, int.class);
    PRIMITIVE_MAP.put(Double.class, double.class);
    PRIMITIVE_MAP.put(Float.class, float.class);
    PRIMITIVE_MAP.put(Long.class, long.class);
    PRIMITIVE_MAP.put(Boolean.class, boolean.class);
    // ... 其他类型可按需添加
  }

  /**
   * 通用方法调用器
   *
   * @param target     目标对象 (如果是静态方法传 null，如果是构造函数传 Class对象)
   * @param methodName 方法名 (如果是调用构造函数，传 null 或空字符串)
   * @param args       可变参数列表
   * @return 方法返回值 (void 返回 null, 构造函数返回新实例)
   */
  public static Object execute(Object target, String methodName, Object... args) {
    try {
      // 1. 获取参数类型列表
      Class<?>[] paramTypes = new Class[args.length];
      for (int i = 0; i < args.length; i++) {
        paramTypes[i] = args[i].getClass();
      }

      // 2. 判断是调用构造函数还是普通方法
      if (target instanceof Class && (methodName == null || methodName.isEmpty())) {
        // --- 场景 A: 调用构造函数 ---
        Class<?> clazz = (Class<?>) target;
        Constructor<?> ctor = findConstructor(clazz, paramTypes);
        return ctor.newInstance(args);
      } else {
        // --- 场景 B: 调用普通方法 ---
        Class<?> clazz = (target instanceof Class) ? (Class<?>) target : target.getClass();
        Method method = findMethod(clazz, methodName, paramTypes);

        // 如果是静态方法，invoke 第一个参数忽略 target，但为了通用通常传 null
        // 如果是实例方法，target 必须是实例对象
        return method.invoke(target, args);
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("反射调用失败: " + e.getMessage());
    }
  }

  /**
   * 辅助方法：查找匹配的方法（处理自动拆箱问题）
   *
   * @param clazz
   * @param name
   * @param paramTypes
   * @return
   * @throws NoSuchMethodException
   */
  private static Method findMethod(Class<?> clazz, String name, Class<?>[] paramTypes) throws NoSuchMethodException {
    try {
      // 尝试直接获取（精确匹配）
      return clazz.getMethod(name, paramTypes);
    } catch (NoSuchMethodException e) {
      // 尝试将包装类型转换为基本类型后再查找 (如 Integer -> int)
      Class<?>[] primitiveTypes = convertToPrimitive(paramTypes);
      return clazz.getMethod(name, primitiveTypes);
    }
  }

  /**
   * 辅助方法：查找匹配的构造函数
   *
   * @param clazz
   * @param paramTypes
   * @return
   * @throws NoSuchMethodException
   */
  private static Constructor<?> findConstructor(Class<?> clazz, Class<?>[] paramTypes) throws NoSuchMethodException {
    try {
      return clazz.getConstructor(paramTypes);
    } catch (NoSuchMethodException e) {
      Class<?>[] primitiveTypes = convertToPrimitive(paramTypes);
      return clazz.getConstructor(primitiveTypes);
    }
  }

  /**
   * 将包装类型数组转换为基本类型数组
   *
   * @param types
   * @return
   */
  private static Class<?>[] convertToPrimitive(Class<?>[] types) {
    Class<?>[] newTypes = new Class[types.length];
    for (int i = 0; i < types.length; i++) {
      Class<?> type = types[i];
      // 如果映射表里有（如 Integer），取对应的 int.class，否则保持原样
      newTypes[i] = PRIMITIVE_MAP.getOrDefault(type, type);
    }
    return newTypes;
  }
}
