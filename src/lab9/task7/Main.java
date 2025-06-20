package lab9.task7;

import java.util.ArrayList;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        int n = 10000; // количество человек в кругу

        // Тест для ArrayList
        System.out.println("Тестирование с использованием ArrayList, n = " + n);
        long startTime = System.nanoTime();
        int survivorArrayList = ArrayList(n);
        long elapsedTimeArrayList = System.nanoTime() - startTime;
        System.out.println("Остался (ArrayList): " + survivorArrayList);
        System.out.printf("Время работы (ArrayList): %.3f ms\n\n", elapsedTimeArrayList / 1_000_000.0);

        // Тест для LinkedList
        System.out.println("Тестирование с использованием LinkedList, n = " + n);
        startTime = System.nanoTime();
        int survivorLinkedList = LinkedList(n);
        long elapsedTimeLinkedList = System.nanoTime() - startTime;
        System.out.println("Остался (LinkedList): " + survivorLinkedList);
        System.out.printf("Время работы (LinkedList): %.3f ms\n", elapsedTimeLinkedList / 1_000_000.0);
    }

    // Моделирование процесса с использованием ArrayList
    public static int ArrayList(int n) {
        ArrayList<Integer> people = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            people.add(i);
        }
        int index = 0;
        while (people.size() > 1) {
            index = (index + 1) % people.size();
            people.remove(index);
        }
        return people.getFirst();
    }

    // Моделирование процесса с использованием LinkedList
    public static int LinkedList(int n) {
        LinkedList<Integer> people = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            people.add(i);
        }
        int index = 0;
        while (people.size() > 1) {
            index = (index + 1) % people.size();
            people.remove(index);
        }
        return people.getFirst();
    }
}
