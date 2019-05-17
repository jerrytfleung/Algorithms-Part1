import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] array;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        array = (Item[]) new Object[1];
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    private void resize(int newCapacity) {
        Item[] newArray = (Item[]) new Object[newCapacity];
        int newSize = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                newArray[newSize++] = array[i];
            }
        }
        array = newArray;
        size = newSize;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (size == array.length) {
            resize(2 * array.length);
        }
        array[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int index = StdRandom.uniform(size);
        Item val = array[index];
        size--;
        array[index] = array[size];
        array[size] = null;
        if (size < array.length / 4) {
            // shrink
            resize(array.length / 2);
        }
        return val;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int index = StdRandom.uniform(size);
        Item val = array[index];
        return val;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    // ArrayIterator implementation
    private class ArrayIterator implements Iterator<Item> {

        private final Item[] arr;
        private int index;

        public ArrayIterator() {
            arr = (Item[]) new Object[size];
            int arrSize = size;
            int nIndex = 0;
            while (arrSize > 0) {
                int randomIndex = StdRandom.uniform(arrSize);
                arr[nIndex] = array[randomIndex];
                array[randomIndex] = array[arrSize-1];
                array[arrSize-1] = arr[nIndex];
                nIndex++;
                arrSize--;
            }
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < arr.length;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            else
                return arr[index++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}