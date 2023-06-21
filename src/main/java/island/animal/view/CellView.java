package island.animal.view;

import island.animal.model.Animal;
import island.animal.model.Island;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class CellView {
    private Island island;
    private IIslandGui islandGui;
    private JPanel cellPanel;
    private JLabel predatorCellLabel;
    private JLabel omnivorousCellLabel;
    private JLabel herbivoreCellLabel;
    private JLabel animalCellLabel;
    private JLabel plantCellLabel;
    private DecimalFormat myFormat = new DecimalFormat("#.##");

    public CellView(Island island, IIslandGui islandGui) {
        this.island = island;
        this.islandGui = islandGui;
    }

    //    public CellView(JPanel cellPanel, JLabel predatorCellLabel, JLabel omnivorousCellLabel, JLabel herbivoreCellLabel, JLabel animalCellLabel, JLabel plantCellLabel) {
//        this.cellPanel = cellPanel;
//        this.predatorCellLabel = predatorCellLabel;
//        this.omnivorousCellLabel = omnivorousCellLabel;
//        this.herbivoreCellLabel = herbivoreCellLabel;
//        this.animalCellLabel = animalCellLabel;
//        this.plantCellLabel = plantCellLabel;
//    }
//
//    public static void main(String[] args) {
//        cellPanel = new JPanel();
//        cellPanel.setLayout(new BoxLayout(cellPanel, BoxLayout.Y_AXIS));
//        cellPanel.setBorder(BorderFactory.createLineBorder(Color.black));
//        cellPanel.setMinimumSize(new Dimension(300, 200));
//
//        predatorCellLabel = new JLabel();
//
//        omnivorousCellLabel = new JLabel();
//
//        herbivoreCellLabel = new JLabel();
//
//        animalCellLabel = new JLabel("      ");
//        animalCellLabel.setOpaque(true);
//
//        plantCellLabel = new JLabel("      ");
//        plantCellLabel.setOpaque(true);
//    }


    public JPanel getCellPanel() {
        return cellPanel;
    }

    public void setCellPanel(JPanel cellPanel) {
        this.cellPanel = cellPanel;
    }

    public JLabel getPredatorCellLabel() {
        return predatorCellLabel;
    }

    public void setPredatorCellLabel(JLabel predatorCellLabel) {
        this.predatorCellLabel = predatorCellLabel;
    }

    public JLabel getOmnivorousCellLabel() {
        return omnivorousCellLabel;
    }

    public void setOmnivorousCellLabel(JLabel omnivorousCellLabel) {
        this.omnivorousCellLabel = omnivorousCellLabel;
    }

    public JLabel getHerbivoreCellLabel() {
        return herbivoreCellLabel;
    }

    public void setHerbivoreCellLabel(JLabel herbivoreCellLabel) {
        this.herbivoreCellLabel = herbivoreCellLabel;
    }

    public JLabel getAnimalCellLabel() {
        return animalCellLabel;
    }

    public void setAnimalCellLabel(JLabel animalCellLabel) {
        this.animalCellLabel = animalCellLabel;
    }

    public JLabel getPlantCellLabel() {
        return plantCellLabel;
    }

    public void setPlantCellLabel(JLabel plantCellLabel) {
        this.plantCellLabel = plantCellLabel;
    }

    public JPanel createCell() {
        cellPanel = new JPanel();
        cellPanel.setLayout(new BoxLayout(cellPanel, BoxLayout.Y_AXIS));
        cellPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        cellPanel.setMinimumSize(new Dimension(300, 200));

        predatorCellLabel = new JLabel();

        omnivorousCellLabel = new JLabel();

        herbivoreCellLabel = new JLabel();

        animalCellLabel = new JLabel("      ");
        animalCellLabel.setOpaque(true);

        plantCellLabel = new JLabel("      ");
        plantCellLabel.setOpaque(true);

        cellPanel.add(predatorCellLabel);
        cellPanel.add(omnivorousCellLabel);
        cellPanel.add(herbivoreCellLabel);
        cellPanel.add(animalCellLabel);
        cellPanel.add(plantCellLabel);
        return cellPanel;
    }

    public String toolTipText(int i) {
        StringBuffer sb = new StringBuffer();
        sb.append("<html>" + "Cell № " + i + "<br>" + "<br>");
        if (island.getCell(i).getAnimals().size() == 0) {
            sb.append("Animal numbers - 0" + "<br>");
        } else {
            for (Animal animal : island.getCell(i).getAnimals()) {
                sb.append(animal.getSpecies() + " " + animal.getSpecies().icon + " (" + animal.getUuid() + ") weight " + myFormat.format(animal.getWeight()) + "kg." + "<br>");
            }
        }
        sb.append("Plants 🌱 - " + island.getCell(i).getPlantCount() + "kg." + "</html>");
        return sb.toString();
    }

    public void setTextCellLabels(int i) {
        int predators = (int)island.arrayCells[i].getAnimals().stream().filter(x -> x.getSpecies().parentType.equals("Predator")).count();
        String predatorString = String.format("Predators - %d", predators);
        JLabel predatorCellLabel = islandGui.getCellPanels()[i].getPredatorCellLabel();
        if (predators != 0) {
            predatorCellLabel.setText(predatorString);
        } else {
            predatorCellLabel.setText("      ");
        }

        int omnivorous = (int)island.arrayCells[i].getAnimals().stream().filter(x -> x.getSpecies().parentType.equals("Omnivorous")).count();
        String omnivorousString = String.format("Omnivorous - %d", omnivorous);
        JLabel omnivorousCellLabel = islandGui.getCellPanels()[i].getOmnivorousCellLabel();
        if (omnivorous != 0) {
            omnivorousCellLabel.setText(omnivorousString);
        } else {
            omnivorousCellLabel.setText("      ");
        }

        int herbivores = (int)island.arrayCells[i].getAnimals().stream().filter(x -> x.getSpecies().parentType.equals("Herbivore")).count();
        String herbivoreString = String.format("Herbivores - %d", herbivores);
        JLabel herbivoreCellLabel = islandGui.getCellPanels()[i].getHerbivoreCellLabel();
        if (herbivores != 0) {
            herbivoreCellLabel.setText(herbivoreString);
        } else {
            herbivoreCellLabel.setText("      ");
        }

        StringBuffer sba = new StringBuffer();
        JLabel animalCellLabel = islandGui.getCellPanels()[i].getAnimalCellLabel();
        if (island.arrayCells[i].getAnimals().size() != 0) {
            for (Animal animal : island.arrayCells[i].getAnimals()) {
                sba.append(animal.getSpecies().icon + "(" + animal.getUuid() + ")");
            }
            animalCellLabel.setBackground(Color.RED);
            animalCellLabel.setText(sba.toString());
        } else {
            animalCellLabel.setBackground(null); //Color.LIGHT_GRAY);
            animalCellLabel.setText("      ");
        }
        JLabel plantCellLabel = islandGui.getCellPanels()[i].getPlantCellLabel();
        if (island.arrayCells[i].getPlantCount() != 0) {
            plantCellLabel.setBackground(Color.GREEN);
            plantCellLabel.setText("🌱🌱🌱🌱🌱🌱");
        } else {
            plantCellLabel.setBackground(null); //Color.LIGHT_GRAY);
            plantCellLabel.removeAll();
            plantCellLabel.setText("      ");
        }

        islandGui.getCellPanels()[i].getCellPanel().setToolTipText(toolTipText(i));
    }
}
