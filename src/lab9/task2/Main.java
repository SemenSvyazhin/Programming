package lab9.task2;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите целое число: ");
        int number = scanner.nextInt();
        scanner.close();

        System.out.println("Двоичное представление: " + convertToBinary(number));
    }

    public static String convertToBinary(int number) {
        // Если число отрицательное, обрабатываем знак отдельно.
        if (number < 0) {
            return "-" + convertToBinary(-number);
        }
        // Базовый случай: если число меньше 2, просто возвращаем его строковое представление.
        if (number < 2) {
            return String.valueOf(number);
        }
        // Рекурсивно делим число на 2 и дописываем остаток от деления.
        return convertToBinary(number / 2) + (number % 2);
    }
}
