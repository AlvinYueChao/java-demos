package org.example.alvin.study.jvm;

import java.lang.reflect.Field;
import java.util.Objects;
import sun.misc.Unsafe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UnsafeFactory {

  private UnsafeFactory() {
  }

  public static Unsafe getUnsafeInstance() {
    Field theUnsafeInstance;
    try {
      theUnsafeInstance = Unsafe.class.getDeclaredField("theUnsafe");
      Objects.requireNonNull(theUnsafeInstance).setAccessible(true);
    } catch (NoSuchFieldException e) {
      throw new RuntimeException(e);
    }
    try {
      return (Unsafe) Objects.requireNonNull(theUnsafeInstance).get(Unsafe.class);
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }
}
