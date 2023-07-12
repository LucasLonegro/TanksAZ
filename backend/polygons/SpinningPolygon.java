package backend.polygons;

import backend.interfaces.Spinner;

public class SpinningPolygon extends MovingPolygon implements Spinner {
    private final Point centerPoint;
    private double angle = 0;

    public SpinningPolygon(double velocity, double direction, Point centerPoint, Point... points) {
        super(velocity, direction, points);
        this.centerPoint = centerPoint;
    }

    public void move() {
        super.move();
        if (!points().contains(centerPoint)) {
            centerPoint.moveBy(dx(), dy());
        }
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }
    public double getAngle(){
        return this.angle;
    }
    public Point getCenterPoint(){
        return this.centerPoint;
    }
}
