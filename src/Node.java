class MyNode<T> {
    T data;
    MyNode<T> next;

    MyNode(T data) {
        this.data = data;
        this.next = null;
    }
}