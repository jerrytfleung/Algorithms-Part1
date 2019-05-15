import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PercolationTest {

    private Percolation percolation = null;

    @Before
    public void setUp() throws Exception {
        percolation = new Percolation(3);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void openAndIsOpen() {
        percolation.open(1, 1);
        assertTrue(percolation.isOpen(1, 1));
        assertFalse(percolation.isOpen(3, 3));
        percolation.open(3, 3);
        assertTrue(percolation.isOpen(3, 3));
    }

    @Test
    public void isFull() {
        assertFalse(percolation.isFull(1, 1));
        assertFalse(percolation.isFull(1, 2));
        assertFalse(percolation.isFull(1, 3));

        assertFalse(percolation.isFull(2, 1));
        assertFalse(percolation.isFull(2, 2));
        assertFalse(percolation.isFull(2, 3));

        assertFalse(percolation.isFull(3, 1));
        assertFalse(percolation.isFull(3, 2));
        assertFalse(percolation.isFull(3, 3));

        percolation.open(1, 3);
        assertTrue(percolation.isFull(1, 3));
        percolation.open(2, 3);
        assertTrue(percolation.isFull(2, 3));
        percolation.open(3, 3);
        assertTrue(percolation.isFull(3, 3));

        percolation.open(3, 1);
        assertFalse(percolation.isFull(3, 1));
        percolation.open(2, 1);
        assertFalse(percolation.isFull(2, 1));
        percolation.open(1, 1);
        assertTrue(percolation.isFull(1, 1));
        assertTrue(percolation.isFull(2, 1));
        assertTrue(percolation.isFull(3, 1));

    }

    @Test
    public void numberOfOpenSites() {
        assertEquals(0, percolation.numberOfOpenSites());
        percolation.open(1, 1);
        assertEquals(1, percolation.numberOfOpenSites());
    }

    @Test
    public void percolates() {
        assertFalse(percolation.percolates());
        percolation.open(1, 1);
        assertFalse(percolation.percolates());
        percolation.open(1, 2);
        assertFalse(percolation.percolates());
        percolation.open(1, 3);
        assertFalse(percolation.percolates());

        percolation.open(2, 1);
        assertFalse(percolation.percolates());
        percolation.open(2, 2);
        assertFalse(percolation.percolates());
        percolation.open(2, 3);
        assertFalse(percolation.percolates());

        percolation.open(3, 2);
        assertTrue(percolation.percolates());
    }

    @Test
    public void testSingle() {
        percolation = new Percolation(1);
        assertFalse(percolation.percolates());
        percolation.open(1, 1);
        assertTrue(percolation.percolates());
    }

}