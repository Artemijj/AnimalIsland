package island.animal.model;

public interface IAnimal {
    void eat(Island island);
    void move(Island island);
    void reproduction(Island island);
    void die(Island island);
}
