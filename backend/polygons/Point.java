package backend.polygons;

import backend.interfaces.Stationary;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Comparator;

public class Point implements Comparable<Point>, Stationary {
    private Double x, y;

    public Point(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public void moveBy(Double dx, Double dy) {
        x += dx;
        y += dy;
    }

    public Double getX() {
        return this.x;
    }

    public Double getY() {
        return this.y;
    }

    public Double distance(Point other) {
        return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
    }

    public Double angle(Point other) {
        if (dy(other) >= 0) {
            return Math.acos(dx(other) / this.distance(other));
        } else
            return Math.acos(-dx(other) / this.distance(other)) + Math.PI;
    }

    public void rotate_about(Point other, Double angle){
        double rel_x = dx(other);
        double rel_y = dy(other);
        x = x + (rel_x - (rel_x * Math.cos(angle) + rel_y * Math.sin(angle)));
        y = y + (rel_y - (-rel_x * Math.sin(angle) + rel_y * Math.cos(angle)));
    }
    private double dx(Point other){
        return other.x - x;
    }
    private double dy(Point other){
        return other.y - y;
    }
    @Override
    public String toString(){
        return "%s,%s".formatted(x,y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x,y);
    }

    @Override
    public boolean equals(Object o) {
        return this == o || (o instanceof Point other && other.x.equals(this.x) && other.y.equals(this.y));
    }

    @Override
    public int compareTo(Point other) {
        return Comparator.comparing(Point::getX).thenComparing(Point::getY).compare(this, other);
    }
    @Override
    public List<Point> points() {
        return List.of(this);
    }

    public List<Line> lines(){
        return new ArrayList<>();
    }
    public Point getClone(){
        return new Point(x,y);
    }
}
