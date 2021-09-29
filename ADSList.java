import java.util.Arrays;

public class ADSList<E> implements ArrayList<E> {

    private static final int DEFAULT_CAPACITY = 4;
    private Object[] elements;
    private int size = 0;

    public ADSList() {
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    public boolean add(E element) {
        if(this.size == this.elements.length) {
            this.elements = grow();
        }
        this.elements[this.size++] = element;
        return true;
    }

    public boolean add(int index, E element) {
        checkIndex(index);
        if (this.size == this.elements.length) {
            this.elements = grow();
        }
        E lastElement = this.getElement(this.size - 1);
        for (int i = this.size -1; i > index; i--) {
            this.elements[i] = this.elements[i-1];
        }
        this.elements[this.size] = lastElement;
        this.elements[index] = element;
        this.size++;
        return true;
    }

    public E get(int index) { 
        checkIndex(index); 
        return this.getElement(index);
    }

    private E getElement(int index) {
        try {
            return (E) this.elements[index];
        } catch (Exception e) {
            return null;
        }
    }

    public E set(int index, E element) {
        checkIndex(index);
        E oldElement = this.getElement(index);
        this.elements[index] = element;
        return oldElement;
    }

    public E remove(int index) {
        this.checkIndex(index);
        E element = this.getElement(index);
        this.elements[index] = null;
        this.size--;
        shift(index);
        ensureCapacity();
        return element;
    }

    private void shift(int index) {
        for (int i=index; i < this.size; i++) {
            elements[i] = elements[i+1];
        }
    } 

    private Object[] grow() {
        return Arrays.copyOf(this.elements, this.elements.length * 2);
    }

    private Object[] shrink() {
        return Arrays.copyOf(this.elements, this.elements.length / 2);
    }

    private void ensureCapacity() {
        if (this.size < this.elements.length / 3) {
            this.elements = shrink();
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException(String.format("Index out of bounds: %d for size: %d", index, this.size));
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        if (size > 0) return false;
        else return true;

    }
    
    public boolean contains(Object o) {
        if (o != null) {
            for (Object element : elements) {
                if (o.equals(element)) {
                    return true;
                }
            }
        } else {
            for (Object element : elements) {
                if (element == null) {
                    return true;
                }
            }
        }
        return false;
    }

    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (elements[i]==null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(elements[i]))
                    return i;
        }
        return -1;
    }
    
}
