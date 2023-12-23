package app.bogdan.heap;

public interface Heap<T> extends Iterable<T> {
    T get();

    T extract();

    void insert(T value);

    void merge(Heap<T> heap);
}
