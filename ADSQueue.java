public class ADSQueue<E> implements Queue<E> {

    private Node<E> head;
    private int size;

    public void offer(E element) {
        Node<E> newNode = new Node<>(element);
        if (this.head == null) {
            this.head = newNode;
        } else {
            Node<E> current = this.head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        this.size++;
    }

    @Override
    public E poll() {
        ensureNonEmpty();
        E element = this.head.element;
        if (this.size == 1) {
            this.head = null;
        } else {
            Node<E> next = this.head.next;
            this.head.next = null;
            this.head = next;
        }
        this.size--;
        return element;
    }

    @Override
    public E peek() {
        return this.head.element;
    }

    public int size() {
        return size;
    }

    private boolean ensureNonEmpty() {
        if(isEmpty()) return true;
        else return false;
    }

    @Override
    public boolean isEmpty() {
        if (size > 0) return false;
        else return true;
    }
    
}
