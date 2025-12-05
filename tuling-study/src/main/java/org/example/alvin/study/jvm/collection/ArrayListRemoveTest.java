package org.example.alvin.study.jvm.collection;

import java.util.ArrayList;

public class ArrayListRemoveTest {
  public static void main(String[] args) {
    ArrayList<String> list = new ArrayList<>();
    list.add("a");
    list.add("a");
    list.add("b");
    list.add("b");
    list.add("c");
    list.add("c");
    remove(list); // 删除指定的“b”元素 for(int i=0; i
  }

  public static void remove(ArrayList<String> list) {
    for (String s : list) {
      if (s.equals("b")) {
        list.remove(s);
      }
    }
  }
}
