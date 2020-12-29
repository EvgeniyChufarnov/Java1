package homework5;

public class Cat extends Animal {
    private static final int RUN_LIMIT = 200;
    private static final float JUMP_LIMIT = 2f;
    private static int id = 0;

    public Cat() {
        super("Cat #" + (++id), RUN_LIMIT, JUMP_LIMIT);
    }
}
