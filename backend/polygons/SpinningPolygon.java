package backend.polygons;

import backend.interfaces.Spinner;
import backend.interfaces.Stationary;

import java.util.Set;

public class SpinningPolygon extends MovingPolygon implements Spinner {
    private final Point centerPoint;
    private double angle;

    public SpinningPolygon(double velocity, double direction, Point centerPoint, Point... points) {
        super(velocity, direction, points);
        this.centerPoint = centerPoint;
        this.angle = direction;
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
    @Override
    public Set<Point> points(){
        Set<Point> toRet = super.points();
        toRet.add(centerPoint);
        return toRet;
    }
    @Override
    public SpinningPolygon getClone(){
        return new SpinningPolygon(getVelocity(),getDirection(),centerPoint,clonePoints().toArray(Point[]::new));
    }

    @Override
    public Stationary posAfterRotation(double angle) {
        SpinningPolygon toRet = getClone();
        toRet.rotateBy(angle);
        return toRet;
    }
}
