package island.animal.model;

import java.util.EnumMap;

public enum Species {
    Wolf(50, 30, 3, 8, "🐺", "Predator") {
        void fillCanEat() {
            canEat.put(Sheep, 70);
        }

        Animal create(Island island) {
            return new Wolf(this, island);
        }
    },
    Fox(8, 30, 2, 2, "🦊", "Predator") {
        void fillCanEat() {
            canEat.put(Sheep, 30);
        }

        Animal create(Island island) {
            return new Fox(this, island);
        }
    },
    Sheep(15, 140, 3, 7, "🐑", "Herbivore") {
        void fillCanEat() {
        }

        Animal create(Island island) {
            return new Sheep(this, island);
        }
    };

    public double weight;
    public int quantity;
    public int speed;
    public double feed;
    public String icon;
    public String parentType;
    public EnumMap<Species, Integer> canEat;

    Species(double weight, int quantity, int speed, double feed, String icon, String parentType) {
        this.weight = weight;
        this.quantity = quantity;
        this.speed = speed;
        this.feed = feed;
        this.icon = icon;
        this.parentType = parentType;
    }

    abstract void fillCanEat();

    abstract Animal create(Island island);

    // static initializator
    static {
        for (Species animal : Species.values()) {
            animal.canEat = new EnumMap<>(Species.class);
            animal.fillCanEat();
        }
    }
}
