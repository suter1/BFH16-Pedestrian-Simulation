package model.persons;

import model.Position;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by suter1 on 05.10.2016.
 */
public class YoungPerson extends Person {

    public static final double MIN_SPEED = 0.58;
    public static final double MAX_SPEED = 1.62;

    public YoungPerson(double maxHeight, double maxWidth, List<Position> path) {
        super(maxHeight, maxWidth, path, ThreadLocalRandom.current().nextDouble(MIN_SPEED, MAX_SPEED));
    }

    public YoungPerson(double maxHeight, double maxWidth) {
        super(maxHeight, maxWidth, ThreadLocalRandom.current().nextDouble(MIN_SPEED, MAX_SPEED));
    }
}
