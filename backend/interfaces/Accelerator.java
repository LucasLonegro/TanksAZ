package backend.interfaces;

public interface Accelerator extends Spinner, Dragger {
    double getThrust();
    default void doThrust() {
        double vel_x = getVelocity() * Math.cos(getDirection()) + getThrust() * Math.cos(getAngle());
        double vel_y = getVelocity() * Math.sin(getDirection()) + getThrust() * Math.sin(getAngle());
        setVelocity(Math.sqrt(vel_x * vel_x + vel_y * vel_y));
        if (getVelocity() == 0) {
            setDirection(0);
        } else
            setDirection(vel_y > 0 ? Math.acos(vel_x / getVelocity()) : -Math.acos(vel_x / getVelocity()));
    }
}
