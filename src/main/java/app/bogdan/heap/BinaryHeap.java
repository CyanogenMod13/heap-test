package app.bogdan.heap;

import java.util.*;

public class BinaryHeap<T> implements Heap<T>, Iterable<T> {
    private final Comparator<T> comparator;
    private final List<HeapNode<T>> heapArray = new ArrayList<>();
    private final HeapType type;
    private int nextIndex = 0;

    public BinaryHeap(Collection<T> values, Comparator<T> comparator, HeapType type) {
        this.comparator = comparator;
        this.type = type;
        for (T value : values) {
            insert(value);
        }
    }

    public BinaryHeap(T[] values, Comparator<T> comparator, HeapType type) {
        this(Arrays.asList(values), comparator, type);
    }

    public BinaryHeap(Comparator<T> comparator, HeapType type) {
        this(Collections.emptyList(), comparator, type);
    }

    @Override
    public T get() {
        if (heapArray.isEmpty()) return null;
        return heapArray.get(0).getValue();
    }

    @Override
    public T extract() {
        if (heapArray.isEmpty()) return null;

        HeapNode<T> result = heapArray.get(0);
        HeapNode<T> next = replaceFirst();

        while (next.hasLeft(heapArray.size())) {
            HeapNode<T> swapChild = heapArray.get(next.getLeftIndex());

            if (next.hasRight(heapArray.size())) {
                HeapNode<T> right = heapArray.get(next.getRightIndex());
                if (compare(right, swapChild) > 0) {
                    swapChild = right;
                }
            }

            if (compare(swapChild, next) > 0) {
                next.swap(swapChild);
                next = swapChild;
                continue;
            }
            break;
        }

        return result.getValue();
    }

    private HeapNode<T> replaceFirst() {
        int lastIndex = heapArray.size() - 1;

        HeapNode<T> last = heapArray.get(lastIndex);
        heapArray.set(0, last);
        heapArray.remove(lastIndex);
        last.setIndex(0);
        return last;
    }

    @Override
    public void insert(T value) {
        HeapNode<T> next = new HeapNode<>(value, nextIndex++);
        heapArray.add(next);

        while (next.hasParent()) {
            HeapNode<T> parent = heapArray.get(next.getParentIndex());
            if (compare(next, parent) > 0) {
                next.swap(parent);
                next = parent;
                continue;
            }
            break;
        }
    }

    @Override
    public void merge(Heap<T> heap) {
        for (T value : heap) {
            insert(value);
        }
    }

    private int compare(HeapNode<T> n1, HeapNode<T> n2) {
        return type.modifier * comparator.compare(n1.getValue(), n2.getValue());
    }

    @Override
    public Iterator<T> iterator() {
        return heapArray.stream().map(HeapNode::getValue).iterator();
    }

    public enum HeapType {
        MAX(1), MIN(-1);

        final int modifier;

        HeapType(int modifier) {
            this.modifier = modifier;
        }
    }
}
