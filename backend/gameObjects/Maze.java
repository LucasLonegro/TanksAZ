package backend.gameObjects;

import backend.interfaces.Stationary;
import backend.polygons.Line;
import backend.polygons.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Maze implements Stationary {
    private final List<Stationary> walls = new ArrayList<>();
    public void addWall(Stationary l){
        walls.add(l);
    }
    @Override
    public Set<Point> points() {
        return walls.stream().flatMap(w -> w.points().stream()).collect(Collectors.toSet());
    }

    @Override
    public Set<Line> lines() {
        return walls.stream().flatMap(w -> w.lines().stream()).collect(Collectors.toSet());
    }
}
