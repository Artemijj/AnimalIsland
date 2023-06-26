package island.animal.view;

import island.animal.model.animals.Animal;
import island.animal.model.island.Cell;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class CellView {
    private JPanel cellPanel;
    private JLabel predatorCellLabel;
    private JLabel omnivorousCellLabel;
    private JLabel herbivoreCellLabel;
    private JLabel animalCellLabel;
    private JLabel plantCellLabel;
    private DecimalFormat myFormat = new DecimalFormat("#.##");

    public JPanel getCellPanel() {
        return cellPanel;
    }

    public JLabel getPredatorCellLabel() {
        return predatorCellLabel;
    }

    public JLabel getOmnivorousCellLabel() {
        return omnivorousCellLabel;
    }

    public JLabel getHerbivoreCellLabel() {
        return herbivoreCellLabel;
    }

    public JLabel getAnimalCellLabel() {
        return animalCellLabel;
    }

    public JLabel getPlantCellLabel() {
        return plantCellLabel;
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

    public String toolTipText(Cell cell) {
        int cnt = 0;
        int index = cell.getIndex();
        StringBuffer sb = new StringBuffer();
        sb.append("<html>" + "Cell № " + index + "<br>" + "<br>");
        if (cell.getAnimals().size() == 0) {
            sb.append("Animal numbers - 0" + "<br>");
        } else {
            for (Animal animal : cell.getAnimals()) {
                cnt++;
                if (cnt > 10) {
                    sb.append("etc..." + "<br>");
                    break;
                }
                sb.append(animal.getSpecies() + " " + animal.getSpecies().icon + " (" + animal.getUuid() + ") weight " + myFormat.format(animal.getWeight()) + "kg." + "<br>");
            }
        }
        sb.append("Plants 🌱 - " + myFormat.format(cell.getPlantCount()) + "kg." + "</html>");
        return sb.toString();
    }

    public void setTextCellLabels(Cell cell) {
        int predators = cell.typeAnimalCount("Predator");
        String predatorString = String.format("Predators - %d", predators);
        JLabel predatorCellLabel = getPredatorCellLabel();
        if (predators != 0) {
            predatorCellLabel.setText(predatorString);
        } else {
            predatorCellLabel.setText("      ");
        }

        int omnivorous = cell.typeAnimalCount("Omnivorous");
        String omnivorousString = String.format("Omnivorous - %d", omnivorous);
        JLabel omnivorousCellLabel = getOmnivorousCellLabel();
        if (omnivorous != 0) {
            omnivorousCellLabel.setText(omnivorousString);
        } else {
            omnivorousCellLabel.setText("      ");
        }

        int herbivores = cell.typeAnimalCount("Herbivore");
        String herbivoreString = String.format("Herbivores - %d", herbivores);
        JLabel herbivoreCellLabel = getHerbivoreCellLabel();
        if (herbivores != 0) {
            herbivoreCellLabel.setText(herbivoreString);
        } else {
            herbivoreCellLabel.setText("      ");
        }

        StringBuffer sba = new StringBuffer();
        JLabel animalCellLabel = getAnimalCellLabel();
        if (cell.getAnimals().size() != 0) {
            for (Animal animal : cell.getAnimals()) {
                sba.append(animal.getSpecies().icon + "(" + animal.getUuid() + ")");
            }
            animalCellLabel.setBackground(Color.RED);
            animalCellLabel.setText(sba.toString());
        } else {
            animalCellLabel.setBackground(null);
            animalCellLabel.setText("      ");
        }
        JLabel plantCellLabel = getPlantCellLabel();
        if (cell.getPlantCount() != 0) {
            plantCellLabel.setBackground(Color.GREEN);
            plantCellLabel.setText("🌱🌱🌱🌱🌱🌱");
        } else {
            plantCellLabel.setBackground(null);
            plantCellLabel.removeAll();
            plantCellLabel.setText("      ");
        }

        getCellPanel().setToolTipText(toolTipText(cell));
    }
}
