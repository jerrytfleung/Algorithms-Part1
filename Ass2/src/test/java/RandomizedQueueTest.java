import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.*;

public class RandomizedQueueTest {

    private RandomizedQueue<Integer> queue;

    @Before
    public void setUp() throws Exception {
        queue = new RandomizedQueue<>();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void isEmpty() {
        assertTrue(queue.isEmpty());
        queue.enqueue(10);
        assertFalse(queue.isEmpty());
        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void size() {
        assertEquals(0, queue.size());
        int num = 1000;
        for (int i = 0; i < num; i++) {
            queue.enqueue(i);
        }
        assertEquals(num, queue.size());
        int numDeleted = num - 10;
        for (int i = 0; i < numDeleted; i++) {
            queue.dequeue();
        }
        assertEquals(10, queue.size());
    }

    @Test
    public void enqueue() {
        Set<Integer> set = new HashSet<>();
        int num = 1000;
        for (int i = 0; i < num; i++) {
            queue.enqueue(i);
        }
        for (int i = 0; i < num; i++) {
            int val = queue.dequeue().intValue();
            assertFalse(set.contains(val));
            assertTrue(set.add(val));
        }
    }

    @Test
    public void sample() {
        Set<Integer> set = new HashSet<>();
        int num = 10;
        for (int i = 0; i < num; i++) {
            queue.enqueue(i);
        }
        int[] freq = new int[num];
        for (int i = 0; i < 1000; i++) {
            int val = queue.sample().intValue();
            freq[val]++;
        }
        for (int f : freq) {
            assertTrue(f != 0);
        }
    }

    @Test
    public void iterator() {
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }
        Iterator<Integer> it = queue.iterator();
        int count = 0;
        while (it.hasNext()) {
            assertNotNull(it.next());
            queue.dequeue();
            count++;
        }
        assertEquals(10, count);
    }
}