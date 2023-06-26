package island.animal.model.animals;

import island.animal.model.island.Island;

public interface IAnimal {
    void eat(Island island, int position);
    int move(Island island, int initialPosition);
    void reproduction(Island island, int position);
    void die(Island island, int position);
}
