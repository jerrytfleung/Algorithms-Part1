import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {

    private final LineSegment[] lineSegment;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
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
        Arrays.sort(copied);

        List<LineSegment> lineSegmentList = new ArrayList<>();
        for (int i = 0; i < copied.length; i++) {
            for (int j = i + 1; j < copied.length; j++) {
                for (int h = j + 1; h < copied.length; h++) {
                    for (int k = h + 1; k < copied.length; k++) {
                        Point p1 = copied[i], p2 = copied[j], p3 = copied[h], p4 = copied[k];
                        if (Double.compare(p1.slopeTo(p2), p1.slopeTo(p3)) == 0
                                && Double.compare(p1.slopeTo(p2), p1.slopeTo(p4)) == 0) {
                            lineSegmentList.add(new LineSegment(p1, p4));
                        }
                    }
                }
            }
        }
        lineSegment = lineSegmentList.toArray(new LineSegment[lineSegmentList.size()]);
    }

    // the number of line segments
    public int numberOfSegments() {
        return lineSegment.length;
    }

    // the line segments
    public LineSegment[] segments() {
        return Arrays.copyOf(lineSegment, lineSegment.length);
    }
}
