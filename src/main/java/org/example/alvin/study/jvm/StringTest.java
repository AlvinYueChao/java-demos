package org.example.alvin.study.jvm;

public class StringTest {
    public static void main(String[] args) {
        // 代码编译加载时，会在常量池中创建常量“abc”，运行时，返回常量池中的字符串引用
        String str1 = "abc";
        // 代码编译加载时，会在常量池中创建常量“abc”，在调用new时，会在堆中创建String对象，引用常量池中的"abc"，最终返回该String对象的引用
        String str2 = new String("abc");
        // 返回常量“abc”的引用
        String str3 = str2.intern();
        // 经过编译器优化，执行过程和str1相同
        String str4 = "ab" + "c";
        // java.lang.StringBuilder.toString 返回的是一个堆中新建的String对象的引用
        String str5 = new StringBuilder("abc").toString();

        System.out.println(str1 == str2);   // false
        System.out.println(str2 == str3);   // false
        System.out.println(str1 == str3);   // true
        System.out.println(str1 == str4);   // true
        System.out.println(str1 == str5);   // false
    }
}
