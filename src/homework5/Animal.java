package homework5;

public class Animal {
    protected String name;
    protected int runLimit;
    protected float jumpLimit;

    public Animal(String name, int runLimit, float jumpLimit) {
        this.name = name;
        this.runLimit = (int) randomise(runLimit);
        this.jumpLimit = Math.round(randomise(jumpLimit) * 10) / 10f;
    }

    public String run(int length) {
        return name + ((runLimit >= length) ? " ran ": " failed to run " ) + length + " m!";
    }

    public String jump(float height) {
        return name + ((jumpLimit >= height) ? " jumped ": " failed to jump " ) + height + " m!";
    }

    protected float randomise(float limit) {
        return limit * (float) (0.8 + 0.5 * Math.random());
    }

    @Override
    public String toString() {
        return name + " Run limit: " + runLimit + ", jump limit: " + jumpLimit;
    }
}
