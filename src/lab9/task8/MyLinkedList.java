package lab9.task8;

public class MyLinkedList {
    private static class Node {
        int value;
        Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node head;

    // === Методы с использованием циклов ===

    /**
     * Создание списка с головы (добавление элементов в начало)
     * @param values массив значений для добавления
     */
    public void createHead(int[] values) {
        for (int i = values.length - 1; i >= 0; i--) {
            head = new Node(values[i], head);
        }
    }

    /**
     * Создание списка с хвоста (добавление элементов в конец)
     * @param values массив значений для добавления
     */
    public void createTail(int[] values) {
        if (values.length == 0) return;

        head = new Node(values[0], null);
        Node tail = head;

        for (int i = 1; i < values.length; i++) {
            tail.next = new Node(values[i], null);
            tail = tail.next;
        }
    }

    /**
     * Добавление элемента в начало списка
     * @param value значение для добавления
     */
    public void addFirst(int value) {
        head = new Node(value, head);
    }

    /**
     * Добавление элемента в конец списка
     * @param value значение для добавления
     */
    public void addLast(int value) {
        if (head == null) {
            head = new Node(value, null);
            return;
        }

        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = new Node(value, null);
    }

    /**
     * Вставка элемента на указанную позицию
     * @param value значение для вставки
     * @param index позиция (начиная с 0)
     */
    public void insert(int value, int index) {
        if (index < 0) throw new IndexOutOfBoundsException();
        if (index == 0) {
            addFirst(value);
            return;
        }

        Node current = head;
        for (int i = 0; current != null && i < index - 1; i++) {
            current = current.next;
        }

        if (current == null) throw new IndexOutOfBoundsException();

        current.next = new Node(value, current.next);
    }

    /**
     * Удаление первого элемента
     */
    public void removeFirst() {
        if (head == null) return;
        head = head.next;
    }

    /**
     * Удаление последнего элемента
     */
    public void removeLast() {
        if (head == null) return;
        if (head.next == null) {
            head = null;
            return;
        }

        Node current = head;
        while (current.next.next != null) {
            current = current.next;
        }
        current.next = null;
    }

    /**
     * Удаление элемента по индексу
     * @param index позиция элемента для удаления
     */
    public void remove(int index) {
        if (index < 0 || head == null) throw new IndexOutOfBoundsException();
        if (index == 0) {
            removeFirst();
            return;
        }

        Node current = head;
        for (int i = 0; current != null && i < index - 1; i++) {
            current = current.next;
        }

        if (current == null || current.next == null) throw new IndexOutOfBoundsException();

        current.next = current.next.next;
    }

    /**
     * Преобразование списка в строку
     * @return строковое представление списка
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node current = head;
        while (current != null) {
            sb.append(current.value).append(" ");
            current = current.next;
        }
        return sb.toString().trim();
    }

    // === Рекурсивные методы ===

    /**
     * Рекурсивное создание списка с головы
     * @param values массив значений
     */
    public void createHeadRec(int[] values) {
        head = createHeadRecHelper(values, values.length - 1);
    }

    private Node createHeadRecHelper(int[] values, int index) {
        if (index < 0) return null;
        return new Node(values[index], createHeadRecHelper(values, index - 1));
    }

    /**
     * Рекурсивное создание списка с хвоста
     * @param values массив значений
     */
    public void createTailRec(int[] values) {
        if (values.length == 0) {
            head = null;
            return;
        }
        head = createTailRecHelper(values, 0);
    }

    private Node createTailRecHelper(int[] values, int index) {
        if (index == values.length - 1) {
            return new Node(values[index], null);
        }
        return new Node(values[index], createTailRecHelper(values, index + 1));
    }

    /**
     * Рекурсивное преобразование списка в строку
     * @return строковое представление списка
     */
    public String toStringRec() {
        return toStringRecHelper(head);
    }

    private String toStringRecHelper(Node node) {
        if (node == null) return "";
        return node.value + " " + toStringRecHelper(node.next);
    }
}
