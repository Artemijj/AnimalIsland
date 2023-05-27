package island.animal.model;

import java.util.EnumMap;

public enum Animals {
    Fox(20, 2, 4) {
		void fillCanEat() {
			canEat.put(Sheep,30);
		}
	},
	Sheep(10, 3, 5) {
		void fillCanEat() {};
	};

	public double weight;
	public int     speed;
	public double  feed;
	public EnumMap<Animals,Integer> canEat ;

	Animals(double weight,int speed,double  feed) {
		this.weight = weight;
		this.speed = speed;
		this.feed = feed;
	}
	abstract void fillCanEat();

	// static initializator
	static {
	   for (Animals animal : Animals.values()) {
		animal.canEat = new EnumMap<>(Animals.class);
		animal.fillCanEat();
	   }
        }
}
