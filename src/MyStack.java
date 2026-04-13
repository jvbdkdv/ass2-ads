public class MyStack<T> {
    private MyNode<T> top;
    private int size = 0;

    public void push(T item) { // Добавление на вершину
        MyNode<T> newNode = new MyNode<>(item);
        newNode.next = top;
        top = newNode;
        size++;
    }

    public T pop() { // Извлечение с вершины
        if (top == null) return null;
        T data = top.data;
        top = top.next;
        size--;
        return data;
    }
}