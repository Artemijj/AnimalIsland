package island.animal.view;

import island.animal.controller.GridPanelUpdate;
import island.animal.controller.SelectButtonPress;
import island.animal.controller.StartButtonPress;
import island.animal.controller.StopButtonPress;
import island.animal.model.*;

//import java.util.Timer;
import java.text.DecimalFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

import java.awt.*;

import static java.awt.Component.LEFT_ALIGNMENT;

public class IslandGui implements IIslandGui{
    private static ModelParameter modelParameter = new ModelParameter(null);
    private static Island island = new Island(modelParameter);

    public IslandGui(Island island) {
        this.island = island;
    }

    private JFrame window;
    private JToolBar tb;
    private JButton start;
    private JButton stop;
    private JLabel fileSelectLabel;
    private JButton fileSelectButton;
    private JPanel gridPanel;
    private JPanel statPanel;
    private JLabel predatorCellLabel;
    private JLabel[] predatorCellLabels;
    private JLabel omnivorousCellLabel;
    private JLabel[] omnivorousCellLabels;
    private JLabel herbivoreCellLabel;
    private JLabel[] herbivoreCellLabels;
    private JLabel animalCellLabel;
    private JLabel[] animalCellLabels;
    private JLabel plantCellLabel;
    private JLabel[] plantCellLabels;
    private JPanel[] tooltipArray;
    private int widthIsland = island.getModelParameter().getWidthIsland();
    private int heightIsland = island.getModelParameter().getHeightIsland();
    private JLabel predatorStatLabel;
    private JLabel omnivorousStatLabel;
    private JLabel herbivoreStatLabel;
    private JLabel plantStatLabel;
    private DecimalFormat myFormat = new DecimalFormat("#.##");
    private ScheduledExecutorService ses;

    public static void main(String[] args) {
        IIslandGui islandGui = new IslandGui(island);
//        island.start();
        islandGui.mainWindow();
//        islandGui.panelUpdateStart();
    }

    public void mainWindow() {
        window = new JFrame("Island");
        window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        tb = new JToolBar();
        tb.setFloatable(false);
        tb.setAlignmentX(LEFT_ALIGNMENT);

        tb.setBackground(Color.GREEN);  //?????????????????????????????

        start = new JButton("Start");
        start.addActionListener(new StartButtonPress(this, island));
        stop = new JButton("Stop");
        stop.addActionListener(new StopButtonPress(this, island));

        tb.add(start);
        tb.add(stop);
        tb.addSeparator();
        tb.addSeparator();

        fileSelectLabel = new JLabel("Select preference file.");
        fileSelectLabel.setPreferredSize(new Dimension(300, fileSelectLabel.getPreferredSize().height));

        fileSelectButton = new JButton("...");
        fileSelectButton.addActionListener(new SelectButtonPress(this, island));

        tb.add(fileSelectLabel);
//        horizontalPanel.add(Box.createHorizontalGlue());
        tb.addSeparator();
        tb.add(fileSelectButton);

//        parameterPanel.add(horizontalPanel);



        gridPanel = new JPanel();
        gridPanel.setAlignmentX(LEFT_ALIGNMENT);
        gridPanel.setPreferredSize(new Dimension(2019, 779));

        gridPanel.setBackground(Color.CYAN);   //?????????????????????????

////        loadGridPanel();
//        gridPanel.repaint();
//        gridPanel.revalidate();
//        gridPanelUpdateStart();

        JScrollPane scrollPane = new JScrollPane(gridPanel);


        statPanel = new JPanel();
        statPanel.setLayout(new BoxLayout(statPanel, BoxLayout.Y_AXIS));
        statPanel.setAlignmentX(LEFT_ALIGNMENT);

        statPanel.setBackground(Color.RED);  //?????????????????????????????????????

//        statPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        predatorStatLabel = new JLabel("Predators - 0");
        omnivorousStatLabel = new JLabel("Omnivorous - 0");
        herbivoreStatLabel = new JLabel("Herbivores - 0");
        plantStatLabel = new JLabel("Plants - 0");

        statPanel.add(predatorStatLabel);
        statPanel.add(omnivorousStatLabel);
        statPanel.add(herbivoreStatLabel);
        statPanel.add(plantStatLabel);

        mainPanel.add(tb);
        mainPanel.add(scrollPane);
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(statPanel);
        mainPanel.add(Box.createVerticalStrut(5));
        window.add(mainPanel);
        window.pack();
        window.setVisible(true);
    }

    public void setFileSelectLabel(String filename) {
        fileSelectLabel.setText(filename);
    }

    public void loadGridPanel() {
        gridPanel.removeAll();

        widthIsland = island.getModelParameter().getWidthIsland();
        heightIsland = island.getModelParameter().getHeightIsland();

        gridPanel.setLayout(new GridLayout(heightIsland, widthIsland, 1, 1));

        window.setTitle("Island " + widthIsland + "x" + heightIsland);

        int i = 0;
        int arraySize = heightIsland * widthIsland;
        predatorCellLabels = new JLabel[arraySize];
        omnivorousCellLabels = new JLabel[arraySize];
        herbivoreCellLabels = new JLabel[arraySize];
        animalCellLabels = new JLabel[arraySize];
        plantCellLabels = new JLabel[arraySize];
        tooltipArray = new JPanel[arraySize];
        for (int j = 0; j < heightIsland; j++) {
            for (int k = 0; k < widthIsland; k++) {
                JPanel cellPanel = new JPanel();//new FlowLayout(FlowLayout.LEFT));
                cellPanel.setLayout(new BoxLayout(cellPanel, BoxLayout.Y_AXIS));
                cellPanel.setBorder(BorderFactory.createLineBorder(Color.black));
                cellPanel.setPreferredSize(new Dimension(100, 77));

                tooltipArray[i] = cellPanel;
                cellPanel.setToolTipText(toolTipText(i));

                predatorCellLabel = new JLabel();
                predatorCellLabels[i] = predatorCellLabel;

                omnivorousCellLabel = new JLabel();
                omnivorousCellLabels[i] = omnivorousCellLabel;

                herbivoreCellLabel = new JLabel();
                herbivoreCellLabels[i] = herbivoreCellLabel;

                animalCellLabel = new JLabel("      ");
                animalCellLabel.setOpaque(true);
                animalCellLabels[i] = animalCellLabel;

                plantCellLabel = new JLabel("      ");
                plantCellLabel.setOpaque(true);
                plantCellLabels[i] = plantCellLabel;

                cellPanel.add(predatorCellLabel);
                cellPanel.add(omnivorousCellLabel);
                cellPanel.add(herbivoreCellLabel);
                cellPanel.add(animalCellLabel);
                cellPanel.add(plantCellLabel);

                cellPanel.setBackground(Color.LIGHT_GRAY);

                gridPanel.add(cellPanel);
                setTextCellLabels(i);
                i++;
            }
        }
//        gridPanel.setPreferredSize(new Dimension(widthIsland * 200, heightIsland * 77));
//        window.repaint();
//        window.revalidate();
    }

    private void loadStatPanel() {
        int allPredators = 0;
        int allOmnivorous = 0;
        int allHerbivores = 0;
        double allPlants = 0;
        for (Cell cell : island.arrayCells) {
            allPredators += cell.getAnimals().stream().filter(x -> x.getSpecies().parentType.equals("Predator")).count();
            allOmnivorous += cell.getAnimals().stream().filter(x -> x.getSpecies().parentType.equals("Omnivorous")).count();
            allHerbivores += cell.getAnimals().stream().filter(x -> x.getSpecies().parentType.equals("Herbivore")).count();
            allPlants += cell.getPlantCount();
        }
        String predatorString = String.format("Predators - %d", allPredators);
        predatorStatLabel.setText(predatorString);
        String omnivorousString = String.format("Omnivorous - %d", allOmnivorous);
        omnivorousStatLabel.setText(omnivorousString);
        String herbivoreString = String.format("Herbivores - %d", allHerbivores);
        herbivoreStatLabel.setText(herbivoreString);
        String plantString = String.format("Plants - %S", myFormat.format(allPlants));
        plantStatLabel.setText(plantString);
    }

    public void gridPanelUpdate() {
//        window.removeAll();
        gridPanel.setPreferredSize(new Dimension(widthIsland * 200, heightIsland * 77));
        window.repaint();
        window.revalidate();
    }

    public void panelUpdateStart() {
        ses = Executors.newScheduledThreadPool(5);
        ses.scheduleWithFixedDelay(() -> gridPanelUpdateStart(), 1, 5, TimeUnit.SECONDS);
        ses.scheduleWithFixedDelay(() -> loadStatPanel(), 1, 5, TimeUnit.SECONDS);
        ses.scheduleWithFixedDelay(() -> {
            if (island.getAnimalCount() == 0) {
                System.out.println("Count of animal is " + island.getAnimalCount());
                island.stop();
                alertDialog("<html>" + "This is the END." + "<br>" + "<br>" + "All animals is dead..." + "</html>");
            }
        }, 1, modelParameter.getDurationOfTact() * 10, TimeUnit.MILLISECONDS);
    }

    public void panelUpdateStop() {
        ses.shutdown();
    }

    private String toolTipText(int i) {
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

    private void setTextCellLabels(int i) {
        int predators = (int)island.arrayCells[i].getAnimals().stream().filter(x -> x.getSpecies().parentType.equals("Predator")).count();
        String predatorString = String.format("Predators - %d", predators);
        predatorCellLabel = predatorCellLabels[i];
        if (predators != 0) {
            predatorCellLabel.setText(predatorString);
        } else {
            predatorCellLabel.setText("      ");
        }

        int omnivorous = (int)island.arrayCells[i].getAnimals().stream().filter(x -> x.getSpecies().parentType.equals("Omnivorous")).count();
        String omnivorousString = String.format("Omnivorous - %d", omnivorous);
        omnivorousCellLabel = omnivorousCellLabels[i];
        if (omnivorous != 0) {
            omnivorousCellLabel.setText(omnivorousString);
        } else {
            omnivorousCellLabel.setText("      ");
        }

        int herbivores = (int)island.arrayCells[i].getAnimals().stream().filter(x -> x.getSpecies().parentType.equals("Herbivore")).count();
        String herbivoreString = String.format("Herbivores - %d", herbivores);
        herbivoreCellLabel = herbivoreCellLabels[i];
        if (herbivores != 0) {
            herbivoreCellLabel.setText(herbivoreString);
        } else {
            herbivoreCellLabel.setText("      ");
        }

        StringBuffer sba = new StringBuffer();
        animalCellLabel = animalCellLabels[i];
        if (island.arrayCells[i].getAnimals().size() != 0) {
            for (Animal animal : island.arrayCells[i].getAnimals()) {
                sba.append(animal.getSpecies().icon + "(" + animal.getUuid() + ")");
            }
            animalCellLabel.setBackground(Color.RED);
            animalCellLabel.setText(sba.toString());
        } else {
            animalCellLabel.setBackground(Color.LIGHT_GRAY);
            animalCellLabel.setText("      ");
        }
        plantCellLabel = plantCellLabels[i];
        if (island.arrayCells[i].getPlantCount() != 0) {
            plantCellLabel.setBackground(Color.GREEN);
            plantCellLabel.setText("🌱🌱🌱🌱🌱🌱");
        } else {
            plantCellLabel.setBackground(Color.LIGHT_GRAY);
            plantCellLabel.removeAll();
            plantCellLabel.setText("      ");
        }

        tooltipArray[i].setToolTipText(toolTipText(i));
    }

    private void gridPanelUpdateStart() {
        int i = 0;
        for (int j = 0; j < heightIsland; j++) {
            for (int k = 0; k < widthIsland; k++) {
                setTextCellLabels(i);
                i++;
            }
        }
    }

    public void alertDialog(String alert) {
        JOptionPane.showMessageDialog(window, alert);
    }
}
