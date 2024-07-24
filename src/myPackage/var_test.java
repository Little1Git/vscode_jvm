package myPackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class var_test {
    public static void main(String[] args) { // JDK 10
        // var list = List.of("apple", "banana", "cherry"); // 推断为List<String>
        // var map = Map.of("key1", 1, "key2", 2); // 推断为Map<String, Integer>

        var numbers = new ArrayList<Integer>(); // 推断为ArrayList<Integer>
        var map = new HashMap<String, Integer>(); // 推断为HashMap<String, Integer>
        // var只能用于局部变量的声明，不能用于成员变量、方法参数或返回类型。
        // var要求在声明时必须初始化变量，以便编译器能够推断出类型。

        // System.out.println(list.getClass().getName());
        System.out.println(map.getClass().getName());
        System.out.println(numbers.getClass().getName());
    }
}
