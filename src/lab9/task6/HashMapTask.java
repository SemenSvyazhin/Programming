package lab9.task6;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.ArrayList;

public class HashMapTask {
    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put(i, "String" + i);
        }

        // Строки с ключом > 5
        System.out.println("Ключи > 5:");
        map.entrySet().stream()
                .filter(e -> e.getKey() > 5)
                .forEach(e -> System.out.println(e.getValue()));

        // Строки с ключом = 0
        if (map.containsKey(0)) {
            System.out.println("Ключ 0: " + map.get(0));
        }

        // Перемножение ключей с длиной строки > 5
        int product = map.entrySet().stream()
                .filter(e -> e.getValue().length() > 5)
                .mapToInt(e -> e.getKey())
                .reduce(1, (a, b) -> a * b);
        System.out.println("Произведение ключей: " + product);
    }
}