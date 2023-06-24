package island.animal.model;

import java.util.EnumMap;

public enum Species {
    Wolf(50, 3, 8, "🐺", "Predator") {
        void fillCanEat() {
            canEat.put(Horse, 10);
            canEat.put(Deer, 15);
            canEat.put(Rabbit, 60);
            canEat.put(Mouse, 80);
            canEat.put(Goat, 60);
            canEat.put(Sheep, 70);
            canEat.put(Boar, 15);
            canEat.put(Buffalo,10);
            canEat.put(Duck, 40);
        }

        Animal create() {
            return new Wolf(this);
        }
    },
    Boa(15, 1, 3, "🐍", "Predator") {
        void fillCanEat() {
            canEat.put(Fox, 15);
            canEat.put(Rabbit, 20);
            canEat.put(Mouse, 40);
            canEat.put(Duck, 10);
        }

        Animal create() {
            return new Boa(this);
        }
    },
    Fox(8, 2, 2, "🦊", "Predator") {
        void fillCanEat() {
            canEat.put(Rabbit, 70);
            canEat.put(Mouse, 90);
            canEat.put(Duck, 60);
            canEat.put(Caterpillar, 40);
        }

        Animal create() {
            return new Fox(this);
        }
    },
    Bear(500, 2, 80, "🐻", "Predator") {
        void fillCanEat() {
            canEat.put(Boa, 80);
            canEat.put(Horse, 40);
            canEat.put(Deer, 80);
            canEat.put(Rabbit, 80);
            canEat.put(Mouse, 90);
            canEat.put(Goat, 70);
            canEat.put(Sheep, 70);
            canEat.put(Boar, 50);
            canEat.put(Buffalo, 20);
            canEat.put(Duck, 10);
        }

        Animal create() {
            return new Bear(this);
        }
    },
    Eagle(6, 3, 1, "🦅", "Predator") {
        void fillCanEat() {
            canEat.put(Fox, 10);
            canEat.put(Rabbit, 90);
            canEat.put(Mouse, 90);
            canEat.put(Duck, 80);
        }

        Animal create() {
            return new Eagle(this);
        }
    },
    Horse(400, 4, 60, "🐎", "Herbivore") {
        void fillCanEat() {
        }

        Animal create() {
            return new Horse(this);
        }
    },
    Deer(300, 4, 50, "🦌", "Herbivore") {
        void fillCanEat() {
        }

        Animal create() {
            return new Deer(this);
        }
    },
    Rabbit(2, 2, 0.45, "🐇", "Herbivore") {
        void fillCanEat() {
        }

        Animal create() {
            return new Rabbit(this);
        }
    },
    Mouse(0.05, 1, 0.01, "🐁", "Omnivorous") {
        void fillCanEat() {
            canEat.put(Caterpillar, 90);
        }

        Animal create() {
            return new Mouse(this);
        }
    },
    Goat(60, 3, 10, "🐐", "Herbivore") {
        void fillCanEat() {
        }

        Animal create() {
            return new Goat(this);
        }
    },
    Sheep(70, 3, 15, "🐑", "Herbivore") {
        void fillCanEat() {
        }

        Animal create() {
            return new Sheep(this);
        }
    },
    Boar(400, 2, 50, "🐗", "Omnivorous") {
        void fillCanEat() {
            canEat.put(Mouse, 50);
            canEat.put(Caterpillar, 90);
        }

        Animal create() {
            return new Boar(this);
        }
    },
    Buffalo(700, 3, 100, "🐃", "Herbivore") {
        void fillCanEat() {
        }

        Animal create() {
            return new Buffalo(this);
        }
    },
    Duck(1, 4, 0.15, "🦆", "Omnivorous") {
        void fillCanEat() {
            canEat.put(Caterpillar, 90);
        }

        Animal create() {
            return new Duck(this);
        }
    },
    Caterpillar(0.01, 0, 0, "🐛", "Herbivore") {
        void fillCanEat() {
        }

        Animal create() {
            return new Caterpillar(this);
        }
    };

    public double weight;
//    public int quantity;
    public int speed;
    public double feed;
    public String icon;
    public String parentType;
    public EnumMap<Species, Integer> canEat;

    Species(double weight, int speed, double feed, String icon, String parentType) {
        this.weight = weight;
        this.speed = speed;
        this.feed = feed;
        this.icon = icon;
        this.parentType = parentType;
    }

    abstract void fillCanEat();

    abstract Animal create();

    // static initializator
    static {
        for (Species animal : Species.values()) {
            animal.canEat = new EnumMap<>(Species.class);
            animal.fillCanEat();
        }
    }
}
