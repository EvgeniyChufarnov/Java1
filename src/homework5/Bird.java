package homework5;

public class Bird extends Animal {
    private static final int RUN_LIMIT = 5;
    private static final float JUMP_LIMIT = 0.2f;
    private static int id = 0;

    public Bird() {
        super("Bird #" + (++id), RUN_LIMIT, JUMP_LIMIT);
    }
}
