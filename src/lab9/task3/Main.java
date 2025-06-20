package lab9.task3;

import java.util.Scanner;

public class RecursiveArrayOperations {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Введите размер массива: ");
        int size = scanner.nextInt();
        int[] array = new int[size];

        // Рекурсивный ввод массива
        inputArrayRecursively(array, 0);

        System.out.println("Введенный массив:");
        // Рекурсивный вывод массива
        printArrayRecursively(array, 0);
    }

    // Рекурсивный метод для ввода элементов массива
    private static void inputArrayRecursively(int[] array, int index) {
        if (index >= array.length) {
            return; // Базовый случай рекурсии
        }

        System.out.printf("Введите элемент массива [%d]: ", index);
        array[index] = scanner.nextInt();

        // Рекурсивный вызов для следующего элемента
        inputArrayRecursively(array, index + 1);
    }

    // Рекурсивный метод для вывода элементов массива
    private static void printArrayRecursively(int[] array, int index) {
        if (index >= array.length) {
            return; // Базовый случай рекурсии
        }

        System.out.printf("array[%d] = %d\n", index, array[index]);

        // Рекурсивный вызов для следующего элемента
        printArrayRecursively(array, index + 1);
    }
}
