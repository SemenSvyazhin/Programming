package lab9.task8;

public class LinkedListTest {
    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();

        System.out.println("=== Тестирование методов с циклами ===");

        // Создание списка с головы
        list.createHead(new int[]{1, 2, 3, 4, 5});
        System.out.println("Создание с головы: " + list);

        // Создание списка с хвоста
        list.createTail(new int[]{6, 7, 8, 9, 10});
        System.out.println("Создание с хвоста: " + list);

        // Добавление элементов
        list.addFirst(0);
        System.out.println("После addFirst(0): " + list);

        list.addLast(11);
        System.out.println("После addLast(11): " + list);

        // Вставка элементов
        list.insert(99, 3);
        System.out.println("После insert(99, 3): " + list);

        // Удаление элементов
        list.removeFirst();
        System.out.println("После removeFirst(): " + list);

        list.removeLast();
        System.out.println("После removeLast(): " + list);

        list.remove(4);
        System.out.println("После remove(4): " + list);

        System.out.println("\n=== Тестирование рекурсивных методов ===");

        MyLinkedList recList = new MyLinkedList();

        // Рекурсивное создание с головы
        recList.createHeadRec(new int[]{10, 20, 30, 40, 50});
        System.out.println("Рекурсивное createHeadRec: " + recList.toStringRec());

        // Рекурсивное создание с хвоста
        recList.createTailRec(new int[]{60, 70, 80, 90, 100});
        System.out.println("Рекурсивное createTailRec: " + recList.toStringRec());
    }
}