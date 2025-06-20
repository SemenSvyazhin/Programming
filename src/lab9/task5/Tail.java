package lab9.task5;

import lab9.task4.Node;

public class Tail {
    public static void main(String[] args) {
        Node head = null;
        for (int i = 3; i >= 0; i--) {
            head = new Node(i, head);
        }

        // Вывод
        Node current = head;
        while (current != null) {
            System.out.println(current.value);
            current = current.next;
        }
    }
}
