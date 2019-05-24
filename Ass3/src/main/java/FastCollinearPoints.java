import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {

    private final LineSegment[] lineSegments;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {

        if (points == null) {
            throw new IllegalArgumentException();
        }
        for (Point point : points) {
            if (point == null) {
                throw new IllegalArgumentException();
            }
        }
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException();
                }
            }
        }

        Point[] copied = Arrays.copyOf(points, points.length);
        List<LineSegment> lineList = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            Point p = points[i];
            Arrays.sort(copied);
            Arrays.sort(copied, p.slopeOrder());
            int min = 0;
            int max = min;
            while (min < copied.length) {
                while (max < copied.length && Double.compare(p.slopeTo(copied[min]), p.slopeTo(copied[max])) == 0) {
                    max++;
                }
                if (max - min >= 3) {
                    Point pMin = copied[min].compareTo(p) < 0 ? copied[min] : p;
                    Point pMax = copied[max - 1].compareTo(p) > 0 ? copied[max - 1] : p;
                    if (p == pMin) {
                        lineList.add(new LineSegment(pMin, pMax));
                    }
                }
                min = max;
            }
        }
        lineSegments = new LineSegment[lineList.size()];
        lineList.toArray(lineSegments);
    }

    // the number of line segments
    public int numberOfSegments() {
        return lineSegments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        return Arrays.copyOf(lineSegments, lineSegments.length);
    }
}
