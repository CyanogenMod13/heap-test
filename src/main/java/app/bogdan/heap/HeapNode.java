package app.bogdan.heap;

public class HeapNode<T> {
    private T value;
    private int index;

    public HeapNode(T value, int index) {
        this.value = value;
        this.index = index;
    }

    public int getCurrentIndex() {
        return index;
    }

    public boolean hasParent() {
        return index > 0;
    }

    public boolean hasLeft(int size) {
        return getLeftIndex() < size;
    }

    public boolean hasRight(int size) {
        return getRightIndex() < size;
    }

    public int getParentIndex() {
        return (index - 1) / 2;
    }

    public int getLeftIndex() {
        return 2 * index + 1;
    }

    public int getRightIndex() {
        return 2 * index + 2;
    }

    public T getValue() {
        return value;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void swap(HeapNode<T> node) {
        T temp = node.value;
        node.value = this.value;
        this.value = temp;
    }
}