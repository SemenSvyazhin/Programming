package lab9;

import java.util.*;

public class CollectionPerformanceTest {
    private static final int VARIANT_NUMBER = 2; // Номер вашего варианта
    private static final int COLLECTION_SIZE = VARIANT_NUMBER * 1_000_000;
    private static final int INDEX_ACCESS_SIZE = VARIANT_NUMBER * 1_000_000_000;
    private static final int WARMUP_ITERATIONS = 5;
    private static final int TEST_ITERATIONS = 10;

    public static void main(String[] args) {
        // Тестируемые коллекции
        List<Integer> arrayList = new ArrayList<>();
        Set<Integer> treeSet = new TreeSet<>();
        List<Integer> linkedList = new LinkedList<>();

        System.out.println("Тестирование производительности коллекций:");
        System.out.println("Номер варианта: " + VARIANT_NUMBER);
        System.out.println("Размер коллекции: " + COLLECTION_SIZE);
        System.out.println("Количество операций доступа по индексу: " + INDEX_ACCESS_SIZE);
        System.out.println("Количество итераций теста: " + TEST_ITERATIONS);
        System.out.println("--------------------------------------------");

        // Заполняем коллекции начальными данными
        fillCollection(arrayList);
        fillCollection(treeSet);
        fillCollection(linkedList);

        // Создаем таблицу операций
        String[][] operations = {
                {"Добавление в начало",
                        "arrayList.add(0, 1)",
                        "N/A",
                        "linkedList.addFirst(1)"},

                {"Добавление в конец",
                        "arrayList.add(1)",
                        "treeSet.add(1)",
                        "linkedList.add(1)"},

                {"Добавление в середину",
                        "arrayList.add(arrayList.size()/2, 1)",
                        "treeSet.add(1)",
                        "linkedList.add(linkedList.size()/2, 1)"},

                {"Удаление из начала",
                        "arrayList.remove(0)",
                        "removeFirst(treeSet)",
                        "linkedList.removeFirst()"},

                {"Удаление из конца",
                        "arrayList.remove(arrayList.size()-1)",
                        "removeLast(treeSet)",
                        "linkedList.removeLast()"},

                {"Удаление из середины",
                        "arrayList.remove(arrayList.size()/2)",
                        "removeMiddle(treeSet)",
                        "linkedList.remove(linkedList.size()/2)"},

                {"Получение по индексу",
                        "testIndexAccess(arrayList)",
                        "N/A",
                        "testIndexAccess(linkedList)"}
        };

        // Заголовок таблицы
        System.out.printf("%-25s %-15s %-15s %-15s\n",
                "Операция", "ArrayList", "TreeSet", "LinkedList");
        System.out.println("------------------------------------------------------------");

        // Тестируем операции
        for (String[] op : operations) {
            String opName = op[0];
            String arrayListOp = op[1];
            String treeSetOp = op[2];
            String linkedListOp = op[3];

            System.out.printf("%-25s", opName);

            // Тестируем ArrayList
            if (!arrayListOp.equals("N/A")) {
                System.out.printf(" %-15.2f", testOperation(arrayList, arrayListOp));
            } else {
                System.out.printf(" %-15s", "N/A");
            }

            // Тестируем TreeSet
            if (!treeSetOp.equals("N/A")) {
                System.out.printf(" %-15.2f", testOperation(treeSet, treeSetOp));
            } else {
                System.out.printf(" %-15s", "N/A");
            }

            // Тестируем LinkedList
            if (!linkedListOp.equals("N/A")) {
                System.out.printf(" %-15.2f", testOperation(linkedList, linkedListOp));
            } else {
                System.out.printf(" %-15s", "N/A");
            }

            System.out.println();
        }
    }

    private static void removeFirst(Set<Integer> set) {
        if (!set.isEmpty()) {
            set.remove(set.iterator().next());
        }
    }

    private static void removeLast(TreeSet<Integer> set) {
        if (!set.isEmpty()) {
            set.remove(set.last());
        }
    }

    private static void removeMiddle(Set<Integer> set) {
        if (set.isEmpty()) return;
        Iterator<Integer> it = set.iterator();
        for (int i = 0; i < set.size()/2; i++) it.next();
        it.remove();
    }

    private static void fillCollection(Collection<Integer> collection) {
        for (int i = 0; i < CollectionPerformanceTest.COLLECTION_SIZE; i++) {
            collection.add(i);
        }
    }

    private static double testOperation(Collection<Integer> collection, String operation) {
        try {
            // Специальная обработка теста доступа по индексу
            if (operation.equals("testIndexAccess(arrayList)") ||
                    operation.equals("testIndexAccess(linkedList)")) {
                return testIndexAccess((List<Integer>)collection);
            }

            // Прогрев JVM
            for (int i = 0; i < WARMUP_ITERATIONS; i++) {
                executeOperation(collection, operation);
            }

            // Измерение времени
            long totalTime = 0;
            for (int i = 0; i < TEST_ITERATIONS; i++) {
                long start = System.nanoTime();
                executeOperation(collection, operation);
                long end = System.nanoTime();
                totalTime += (end - start);
            }

            return (double)totalTime / TEST_ITERATIONS / 1_000; // мкс
        } catch (Exception e) {
            return -1;
        }
    }

    private static double testIndexAccess(List<Integer> list) {
        try {
            // Прогрев JVM
            for (int i = 0; i < WARMUP_ITERATIONS; i++) {
                list.get(list.size()/2);
            }

            // Измерение времени
            long totalTime = 0;
            int middle = list.size()/2;
            for (int i = 0; i < TEST_ITERATIONS; i++) {
                long start = System.nanoTime();
                for (int j = 0; j < INDEX_ACCESS_SIZE/TEST_ITERATIONS; j++) {
                    list.get(middle);
                }
                long end = System.nanoTime();
                totalTime += (end - start);
            }

            return (double)totalTime / ((double) INDEX_ACCESS_SIZE /1_000); // наносекунд на операцию
        } catch (Exception e) {
            return -1;
        }
    }

    private static void executeOperation(Collection<Integer> collection, String operation) {
        if (collection instanceof List<Integer> list) {
            switch (operation) {
                case "arrayList.add(0, 1)":
                case "linkedList.addFirst(1)":
                    list.addFirst(1);
                    break;
                case "arrayList.add(1)":
                case "linkedList.add(1)":
                    list.add(1);
                    break;
                case "arrayList.add(arrayList.size()/2, 1)":
                case "linkedList.add(linkedList.size()/2, 1)":
                    list.add(list.size()/2, 1);
                    break;
                case "arrayList.remove(0)":
                case "linkedList.removeFirst()":
                    list.removeFirst();
                    break;
                case "arrayList.remove(arrayList.size()-1)":
                case "linkedList.removeLast()":
                    list.removeLast();
                    break;
                case "arrayList.remove(arrayList.size()/2)":
                case "linkedList.remove(linkedList.size()/2)":
                    list.remove(list.size()/2);
                    break;
            }
        } else if (collection instanceof TreeSet<Integer> set) {
            switch (operation) {
                case "treeSet.add(1)":
                    set.add(1);
                    break;
                case "removeFirst(treeSet)":
                    removeFirst(set);
                    break;
                case "removeLast(treeSet)":
                    removeLast(set);
                    break;
                case "removeMiddle(treeSet)":
                    removeMiddle(set);
                    break;
            }
        }
    }
}

