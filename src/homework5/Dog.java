package homework5;

public class Dog extends Animal {
    private static final int RUN_LIMIT = 500;
    private static final float JUMP_LIMIT = 0.5f;
    private static final int SWIM_LIMIT = 10;
    private static int id = 0;

    private final int swimLimit = (int) randomise(SWIM_LIMIT);

    public Dog() {
        super("Dog #" + (++id), RUN_LIMIT, JUMP_LIMIT);
    }

    public String swim(int length) {
        return name + ((runLimit >= length) ? " swam " : " failed to swim ") + length + " m!";
    }

    @Override
    public String toString() {
        return super.toString() + ", swim limit: " + swimLimit;
    }
}
