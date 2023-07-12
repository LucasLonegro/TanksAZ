package backend.gameObjects;

import backend.interfaces.Mover;
import backend.polygons.Point;
import backend.polygons.Polygon;
import backend.polygons.SpinningPolygon;

import java.util.ArrayList;
import java.util.List;

public class Tank implements Mover {
    private final List<SpinningPolygon> model;
    private final static double SIDE_RATIO = Math.sqrt(2), NOSE_RATIO = 1.2;
    public Tank(Point centerPoint, double scale, double velocity){
        model = List.of(
                new SpinningPolygon(velocity,0,centerPoint, new Point(centerPoint.getX() - SIDE_RATIO*scale, centerPoint.getY())),
                new SpinningPolygon(velocity,0,centerPoint),
                new SpinningPolygon(velocity,0,centerPoint));
    }
    @Override
    public void move(){
        model.forEach(SpinningPolygon::move);
    }
}
