package backend.interfaces;

public interface Dragger extends Mover{
    double getDrag();
    default void doDrag(){
        setVelocity(getVelocity() * (1 - getDrag()));
    }
}
