import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first = null;
    private Node last = null;
    private int size = 0;

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldFirst = first;
        Node newItem = new Node();
        newItem.val = item;
        first = newItem;
        if (isEmpty()) {
            last = newItem;
        } else {
            oldFirst.prev = newItem;
            newItem.next = oldFirst;
        }
        size++;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldLast = last;
        Node newItem = new Node();
        newItem.val = item;
        last = newItem;
        if (isEmpty()) {
            first = newItem;
        } else {
            oldLast.next = newItem;
            newItem.prev = oldLast;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item val = first.val;
        first = first.next;
        size--;
        if (isEmpty()) {
            last = first;
        } else {
            first.prev = null;
        }
        return val;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item val = last.val;
        last = last.prev;
        size--;
        if (isEmpty()) {
            first = last;
        } else {
            last.next = null;
        }
        return val;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    // Node implementation
    private class Node {
        Item val;
        Node next;
        Node prev;

        Node() {
            val = null;
            next = null;
            prev = null;
        }
    }

    // ListIterator implementation
    private class ListIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (current == null) {
                throw new NoSuchElementException();
            }
            Item item = current.val;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
