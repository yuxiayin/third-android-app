package com.example.third_android_app;

import java.util.ArrayList;
import java.util.List;

final class AnimalData {
    static final String[] NAMES = {"Lion", "Tiger", "Monkey", "Dog", "Cat", "Elephant"};
    static final int[] IMAGES = {
            R.drawable.lion, R.drawable.tiger, R.drawable.monkey,
            R.drawable.dog, R.drawable.cat, R.drawable.elephant
    };

    private AnimalData() {}

    static List<Animal> createAnimals() {
        List<Animal> animals = new ArrayList<>();
        for (int i = 0; i < NAMES.length; i++) {
            animals.add(new Animal(NAMES[i], IMAGES[i]));
        }
        return animals;
    }

    static final class Animal {
        final String name;
        final int imageRes;

        Animal(String name, int imageRes) {
            this.name = name;
            this.imageRes = imageRes;
        }
    }
}
