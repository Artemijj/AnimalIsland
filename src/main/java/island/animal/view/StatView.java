package island.animal.view;

import island.animal.model.*;

import java.awt.*;
import java.text.DecimalFormat;

public class StatView {

    private Island island;
    private IIslandGui islandGui;

    public StatView(Island island, IIslandGui islandGui) {
        this.island = island;
        this.islandGui = islandGui;
    }

    private DecimalFormat myFormat = new DecimalFormat("#.##");

    public void loadStatPanel() {
        int allPredators = 0;
        int allOmnivorous = 0;
        int allHerbivores = 0;
        double allPlants = 0;
        for (Cell cell : island.arrayCells) {
            allPredators += cell.typeAnimalCount("Predator");
            allOmnivorous += cell.typeAnimalCount("Omnivorous");
            allHerbivores += cell.typeAnimalCount("Herbivore");
            allPlants += cell.getPlantCount();
        }
        String predatorString = String.format("Predators - %d", allPredators);
        islandGui.getStatLabelArray(0).setText(predatorString);
        String omnivorousString = String.format("Omnivorous - %d", allOmnivorous);
        islandGui.getStatLabelArray(1).setText(omnivorousString);
        String herbivoreString = String.format("Herbivores - %d", allHerbivores);
        islandGui.getStatLabelArray(2).setText(herbivoreString);
        String plantString = String.format("Plants - %S", myFormat.format(allPlants));
        islandGui.getStatLabelArray(3).setText(plantString);
    }
}
