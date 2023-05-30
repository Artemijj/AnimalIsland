package island.animal.model;

import java.util.EnumMap;

public enum Animals {
    Fox(8, 2, 2, "🐺") {
		void fillCanEat() {
			canEat.put(Sheep,30);
		}
	},
	Sheep(15, 3, 7, "🐑") {
		void fillCanEat() {};
	};

	public double weight;
	public int speed;
	public double feed;
	public String icon;
	public EnumMap<Animals,Integer> canEat ;

	Animals(double weight, int speed, double  feed, String icon) {
		this.weight = weight;
		this.speed = speed;
		this.feed = feed;
		this.icon = icon;
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
