package island.animal.view;

import javax.swing.*;
import java.awt.*;

public class CellView {
    private JPanel cellPanel;
    private JLabel predatorCellLabel;
    private JLabel omnivorousCellLabel;
    private JLabel herbivoreCellLabel;
    private JLabel animalCellLabel;
    private JLabel plantCellLabel;

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
}
