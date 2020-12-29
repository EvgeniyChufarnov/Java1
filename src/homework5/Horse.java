package homework5;

public class Horse extends Animal {
    private static final int RUN_LIMIT = 1500;
    private static final float JUMP_LIMIT = 3f;
    private static final int SWIM_LIMIT = 100;
    private static int id = 0;

    private final int swimLimit = (int) randomise(SWIM_LIMIT);

    public Horse() {
        super("Horse #" + (++id), RUN_LIMIT, JUMP_LIMIT);
    }

    public String swim(int length) {
        return name + ((runLimit >= length) ? " swam ": " failed to swim " ) + length + " m!";
    }

    @Override
    public String toString() {
        return super.toString() + ", swim limit: " + swimLimit;
    }
}
