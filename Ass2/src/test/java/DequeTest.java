import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class DequeTest {

    private Deque<Integer> deque = null;

    @Before
    public void setUp() throws Exception {
        deque = new Deque<>();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void isEmpty() {
        assertTrue(deque.isEmpty());
        deque.addFirst(10);
        assertFalse(deque.isEmpty());
        deque.removeLast();
        assertTrue(deque.isEmpty());
        deque.addLast(30);
        deque.addFirst(74);
        deque.removeFirst();
        assertFalse(deque.isEmpty());
        deque.removeLast();
        assertTrue(deque.isEmpty());
    }

    @Test
    public void size() {
        assertEquals(0, deque.size());
        deque.addFirst(10);
        assertEquals(1, deque.size());
        deque.removeLast();
        assertEquals(0, deque.size());
        deque.addLast(30);
        deque.addFirst(74);
        assertEquals(2, deque.size());
        deque.removeFirst();
        assertEquals(1, deque.size());
        deque.removeLast();
        assertEquals(0, deque.size());
    }

    @Test
    public void addFirstAndRemoveFirst() {
        int num = 1000;
        for (int i = 0; i < num; i++) {
            deque.addFirst(i);
        }
        assertEquals(num, deque.size());
        for (int i = 0; i < num; i++) {
            assertEquals(num - 1 - i, deque.removeFirst().intValue());
        }
        assertEquals(0, deque.size());
    }

    @Test
    public void addLastAndRemoveLast() {
        int num = 1000;
        for (int i = 0; i < num; i++) {
            deque.addLast(i);
        }
        assertEquals(num, deque.size());
        for (int i = 0; i < num; i++) {
            assertEquals(num - 1 - i, deque.removeLast().intValue());
        }
        assertEquals(0, deque.size());
    }

    @Test
    public void iterator() {
        int num = 1000;
        for (int i = 0; i < num; i++) {
            deque.addLast(i);
        }

        Iterator<Integer> it = deque.iterator();
        int i = 0;
        while (it.hasNext()) {
            assertEquals(i, it.next().intValue());
            i++;
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void iterator_next() {
        Iterator<Integer> it = deque.iterator();
        it.next();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void iterator_remove() {
        Iterator<Integer> it = deque.iterator();
        it.remove();
    }
}