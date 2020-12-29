package homework5;

import java.util.Random;

public class Main {
    private static final Random RANDOM = new Random();
    private static final int NUMBER_OF_ANIMALS_TYPES = 4;

    public static void main(String[] args) {
        Animal[] animals = new Animal[50];

        for (int i = 0; i < animals.length; i++) {
            animals[i] = createRandomAnimal();
        }

        for (Animal animal : animals) {
            System.out.println(animal);
            System.out.println(animal.run(RANDOM.nextInt(2000)));
            System.out.println(animal.jump(Math.round(RANDOM.nextFloat() * 5 * 100) / 100f));
            if (animal instanceof Dog) {
                System.out.println(((Dog) animal).swim(RANDOM.nextInt(20)));
            } else if (animal instanceof Horse) {
                System.out.println(((Horse) animal).swim(RANDOM.nextInt(100)));
            }
        }
    }

    private static Animal createRandomAnimal() {
        return switch (RANDOM.nextInt(NUMBER_OF_ANIMALS_TYPES)) {
            case 0 -> new Dog();
            case 1 -> new Cat();
            case 2 -> new Horse();
            case 3 -> new Bird();
            default -> null;
        };
    }
}
