package backend.polygons;

import backend.interfaces.Mover;
import backend.interfaces.Stationary;

public class MovingPolygon extends Polygon implements Mover {
    private double velocity, direction;

    public MovingPolygon(double velocity, double direction, Point... points) {
        super(points);
        this.velocity = velocity;
        this.direction = direction % (Math.PI * 2);
    }
    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    @Override
    public double getDirection() {
        return direction;
    }

    @Override
    public double getVelocity() {
        return velocity;
    }
    public void setDirection(double direction) {
        this.direction = direction;
    }
    @Override
    public MovingPolygon getClone(){
        return new MovingPolygon(getVelocity(),getDirection(),clonePoints().toArray(Point[]::new));
    }
    @Override
    public Stationary posAfterMove() {
        MovingPolygon toRet = getClone();
        toRet.move();
        return toRet;
    }
}
