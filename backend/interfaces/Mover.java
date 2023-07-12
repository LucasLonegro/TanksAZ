package backend.interfaces;

public interface Mover extends Stationary {
    double getVelocity();
    void setVelocity(double velocity);
    double getDirection();
    void setDirection(double direction);
    void move();
    Stationary posAfterMove();
    default double dx() {
        return getVelocity() * Math.cos(getDirection());
    }

    default double dy() {
        return getVelocity() * Math.sin(getDirection());
    }
}
