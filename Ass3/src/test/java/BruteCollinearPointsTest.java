import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BruteCollinearPointsTest {

    private BruteCollinearPoints bruteCollinearPoints = null;

    @Before
    public void setUp() throws Exception {
        Point[] points = new Point[8];
        points[0] = new Point(10000, 0);
        points[1] = new Point(0, 10000);
        points[2] = new Point(3000, 7000);
        points[3] = new Point(7000, 3000);
        points[4] = new Point(20000, 21000);
        points[5] = new Point(3000, 4000);
        points[6] = new Point(14000, 15000);
        points[7] = new Point(6000, 7000);
        bruteCollinearPoints = new BruteCollinearPoints(points);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void numberOfSegments() {
        assertEquals(2, bruteCollinearPoints.numberOfSegments());
    }

    @Test
    public void segments() {
        assertEquals("(10000, 0) -> (0, 10000)", bruteCollinearPoints.segments()[0].toString());
        assertEquals("(3000, 4000) -> (20000, 21000)", bruteCollinearPoints.segments()[1].toString());
    }
}