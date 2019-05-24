import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PointTest {

    Point pt = null;

    @Before
    public void setUp() throws Exception {
        pt = new Point(1, 0);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void slopeTo() {
        Point o1 = new Point(4, 6);
        assertEquals(2, pt.slopeTo(o1), 0.0001);

        Point o2 = new Point(4, 0);
        assertEquals(0, pt.slopeTo(o2), 0.0001);

        Point o3 = new Point(1, 4);
        assertEquals(Double.POSITIVE_INFINITY, pt.slopeTo(o3), 0.0001);

        assertEquals(Double.NEGATIVE_INFINITY, pt.slopeTo(pt), 0.0001);

        Point o4 = new Point(390, 208);
        Point o5 = new Point(259, 208);
        assertEquals(0, o4.slopeTo(o5), 0.0001);
    }

    @Test
    public void compareTo() {
        Point o1 = new Point(4, 6);
        assertTrue(pt.compareTo(o1) < 0);

        Point o2 = new Point(4, 0);
        assertTrue(pt.compareTo(o2) < 0);

        Point o3 = new Point(0, 0);
        assertTrue(pt.compareTo(o3) > 0);

        Point o4 = new Point(1, 0);
        assertTrue(pt.compareTo(o4) == 0);
    }

    @Test
    public void slopeOrder() {
        Point[] points = new Point[3];
        points[0] = pt;
        points[1] = new Point(4, 6);
        points[2] = new Point(4, 3);

        Arrays.sort(points, pt.slopeOrder());

        assertEquals("(4, 6)", points[2].toString());
    }
}