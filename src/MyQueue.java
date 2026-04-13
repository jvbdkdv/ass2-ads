public class MyQueue<T> {
    private MyNode<T> front, back;
    private int size = 0;

    public void enqueue(T item) { // Добавление в конец [cite: 173]
        MyNode<T> newNode = new MyNode<>(item);
        if (back == null) {
            front = back = newNode;
        } else {
            back.next = newNode;
            back = newNode;
        }
        size++;
    }

    public T dequeue() { // Удаление из начала [cite: 173]
        if (front == null) return null;
        T data = front.data;
        front = front.next;
        if (front == null) back = null;
        size--;
        return data;
    }
}