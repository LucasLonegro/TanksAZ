package backend.gameObjects;

import backend.interfaces.Mover;
import backend.interfaces.Spinner;
import backend.interfaces.Stationary;
import backend.polygons.Line;
import backend.polygons.Point;
import backend.polygons.Polygon;
import backend.polygons.SpinningPolygon;

import java.util.ArrayList;
import java.util.List;

public class Tank implements Spinner {
    private final List<SpinningPolygon> model;
    private final double scale;
    private final static double SIDE_RATIO = Math.sqrt(2), NOSE_RATIO = 1.2;
    public Tank(Point centerPoint, double scale, double velocity){
        this.scale = scale;
        model = List.of(
                new SpinningPolygon(velocity,0,centerPoint, new Point(centerPoint.getX() - SIDE_RATIO*scale, centerPoint.getY())),
                new SpinningPolygon(velocity,0,centerPoint),
                new SpinningPolygon(velocity,0,centerPoint));
    }

    @Override
    public double getVelocity() {
        return model.get(0).getVelocity();
    }

    @Override
    public void setVelocity(double velocity) {
        model.forEach(p -> p.setVelocity(velocity));
    }

    @Override
    public double getDirection() {
        return model.get(0).getDirection();
    }

    @Override
    public void setDirection(double direction) {
        model.forEach(p -> p.setDirection(direction));
    }
    @Override
    public double getAngle() {
        return model.get(0).getAngle();
    }

    @Override
    public void setAngle(double angle) {
        model.forEach(p -> p.setAngle(angle));
    }

    @Override
    public Point getCenterPoint() {
        return model.get(0).getCenterPoint();
    }

    @Override
    public void move(){
        model.forEach(SpinningPolygon::move);
    }


    @Override
    public List<Point> points() {
        return model.stream().flatMap(p -> points().stream()).toList();
    }

    @Override
    public List<Line> lines() {
        return model.stream().flatMap(p -> lines().stream()).toList();
    }
    public Tank getClone(){
        return new Tank(getCenterPoint().getClone(),scale,getVelocity());
    }

    @Override
    public Stationary posAfterMove() {
        Tank toRet = getClone();
        toRet.rotateBy(getAngle());
        toRet.move();
        return toRet;
    }
    @Override
    public Stationary posAfterRotation(double angle) {
        Tank toRet = getClone();
        toRet.rotateBy(getAngle() + angle);
        return toRet;
    }
}
