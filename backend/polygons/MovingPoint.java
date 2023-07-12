package backend.polygons;

import backend.interfaces.Mover;

import java.util.ArrayList;
import java.util.List;

public class MovingPoint extends Point implements Mover {
    private double direction, velocity;
    public MovingPoint(double x, double y, double velocity, double direction){
        super(x,y);
        this.direction = direction;
        this.velocity = velocity;
    }
    public void move(){
        moveBy(dx(),dy());
    }
    @Override
    public double getDirection() {
        return direction;
    }

    @Override
    public void setDirection(double direction) {
        this.direction = direction;
    }

    @Override
    public double getVelocity() {
        return velocity;
    }

    @Override
    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public List<Point> points(){
        return List.of(this);
    }
}
