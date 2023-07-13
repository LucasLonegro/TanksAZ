package backend.polygons;

import backend.interfaces.Stationary;

import java.util.*;

public class Line implements Comparable<Line>, Stationary {
    private final Point p1, p2;

    public Line(Point p1, Point p2) {
        if (p1.compareTo(p2) > 0) {
            this.p1 = p1;
            this.p2 = p2;
        } else {
            this.p1 = p2;
            this.p2 = p1;
        }
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    public boolean intersects(Line other) {
        double o1 = orientation(p1,p2,other.p1);
        double o2 = orientation(p1,p2,other.p2);
        double o3 = orientation(other.p1,other.p2,p1);
        double o4 = orientation(other.p1,other.p2,p2);
        return (o1 != o2 && o3 != o4) || (o1 == 0 && onSegment(p1,other.p1,p2)) || (o2 == 0 && onSegment(p1,other.p2,p2)) || (o3 == 0 && onSegment(other.p1,p1,other.p2)) || (o4 == 0 && onSegment(other.p1, p2, other.p2));
    }

    private boolean onSegment(Point p1, Point p2, Point p3){
        return p2.getX() <= Math.max(p1.getX(),p3.getX()) && p2.getX() >= Math.min(p1.getX(), p3.getX()) && p2.getY() <= Math.max(p1.getY(),p3.getY()) && p2.getY() >= Math.min(p1.getY(), p3.getY());
    }

    private double orientation(Point p1, Point p2, Point p3) {
        double val = (p2.getY() - p1.getY()) * (p3.getX() - p2.getX()) - (p2.getX() - p1.getX()) * (p3.getY() - p2.getY());
        return val == 0 ? 0 : val > 0 ? 1 : 2;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || (o instanceof Line other && other.p1.equals(p1) && other.p2.equals(p2));
    }

    @Override
    public int hashCode() {
        return Objects.hash(p1, p2);
    }

    @Override
    public int compareTo(Line other) {
        return Comparator.comparing(Line::getP1).thenComparing(Line::getP2).compare(this, other);
    }

    @Override
    public Set<Point> points() {
        return Set.of(p1,p2);
    }

    @Override
    public Set<Line> lines() {
        return Set.of(this);
    }
}
