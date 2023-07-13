package backend.interfaces;

import backend.interfaces.Mover;
import backend.polygons.Point;

public interface Spinner extends Mover {
    double ANGLE_TOLERANCE = 0.1;
    double getAngle();
    void setAngle(double angle);
    Point getCenterPoint();
    Stationary posAfterRotation(double angle);
    default void rotateBy(double angle) {
        setAngle((getAngle() - angle) % (Math.PI * 2));
        points().forEach(p -> p.rotate_about(getCenterPoint(), angle));
    }
    default void rotateTowards(Point point, double angle) {
        double da = getAngle() - firstPoint().angle(point);
        if (Math.abs(da) > ANGLE_TOLERANCE) {
            if ((da > -2 * Math.PI && da < -Math.PI) || (da > 0 && da < Math.PI)) {
                rotateBy(angle);
            } else
                rotateBy(-angle);
        }
    }
}
