package backend.polygons;

import backend.interfaces.Stationary;

import java.util.*;
import java.util.stream.Collectors;

public class Polygon implements Comparable<Polygon>, Stationary {
    private final List<Point> points;
    private final List<Line> lines = new ArrayList<>();
    private Double influence, squareInfluence;
    private final static double NEARBY_LIMIT = 1.44, WEAK_NEARBY_LIMIT = 2.25;

    public Polygon(Point... points) {
        if (points[0] == null)
            throw new IllegalArgumentException();
        this.points = Arrays.stream(points).toList();
        setLines();
        setInfluence();
    }

    public void moveBy(double dx, double dy) {
        points.forEach(p -> p.moveBy(dx, dy));
    }
    public void scaleTo(double factor) {
        Point p0 = points.get(0);
        points.forEach(p -> p.moveBy(p0.getX() + factor * (p.getX() - p0.getX()),p0.getY() + factor * (p.getY() - p0.getY())));
        setInfluence();
    }

    public Set<Line> lines() {
        return new HashSet<>(lines);
    }

    public Set<Point> points() {
        return new HashSet<>(points);
    }
    public Polygon getClone(){
        return new Polygon(clonePoints().toArray(Point[]::new)); // disgusting
    }

    @Override
    public int compareTo(Polygon other) {
        Iterator<Line> i1 = this.lines().iterator();
        Iterator<Line> i2 = other.lines().iterator();
        int aux;
        while (i1.hasNext() && i2.hasNext()) {
            aux = i1.next().compareTo(i2.next());
            if (aux != 0)
                return aux;
        }
        return 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lines);
    }

    protected List<Point> clonePoints(){
        return points.stream().map(p -> new Point(p.getX(),p.getY())).toList();
    }
    private void setLines() {
        if (points.isEmpty())
            return;
        Iterator<Point> iter = points.iterator();
        Point first = iter.next();
        Point previous = first;
        Point next;
        while (iter.hasNext()) {
            next = iter.next();
            lines.add(new Line(previous, next));
            previous = next;
        }
        lines.add(new Line(previous, first));
    }

    private void setInfluence() {
        influence = points.stream().map(point -> points.get(0).distance(point)).max(Double::compareTo).orElse(0.0);
        squareInfluence = influence * influence;
    }

    private boolean isNearby(Point p) {
        return isCloserThanSquare(p, NEARBY_LIMIT);
    }

    private boolean isWeakNearby(Point p) {
        return isCloserThanSquare(p, WEAK_NEARBY_LIMIT);
    }

    private boolean isCloserThanSquare(Point p, double squareDistance) {
        return distanceSquare(points.get(0), p) < squareInfluence * squareDistance;
    }

    private double distanceSquare(Point p1, Point p2) {
        return Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2);
    }
}
