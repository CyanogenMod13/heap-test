package app.bogdan;

import app.bogdan.heap.BinaryHeap;
import app.bogdan.heap.Heap;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Random().nextInt() % 10);
        }

        Heap<Integer> heap = new BinaryHeap<>(list, Integer::compare, BinaryHeap.HeapType.MIN);

        System.out.println(Collections.min(list));
        System.out.println(heap.get());
        System.out.println();
        Integer num;
        while ((num = heap.extract()) != null) {
            System.out.print(num + ", ");
        }
    }
}