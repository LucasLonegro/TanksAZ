package backend.interfaces;

import backend.polygons.Line;
import backend.polygons.Point;

import java.util.List;

public interface Stationary {
    default boolean intersectedBy(Stationary other) {
        for (Line l1 : lines()) {
            for(Line l2 : other.lines())
                if (l1.intersects(l2))
                    return true;
        }
        return false;
    }

    List<Point> points();

    List<Line> lines();
}
