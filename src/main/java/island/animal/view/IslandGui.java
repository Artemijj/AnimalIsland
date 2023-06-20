package island.animal.view;

import island.animal.controller.SelectButtonPress;
import island.animal.controller.StartButtonPress;
import island.animal.controller.StopButtonPress;
import island.animal.model.*;

import java.text.DecimalFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

import java.awt.*;

import static java.awt.Component.LEFT_ALIGNMENT;

public class IslandGui implements IIslandGui{
    private static ModelParameter modelParameter = new ModelParameter(null);
    private static Island island = new Island(modelParameter);

    public IslandGui(Island island) {
        this.island = island;
    }

    private JFrame window;
    private JPanel mainPanel;
    private JLabel fileSelectLabel = new JLabel("Select preference file.");
    private JPanel gridPanel;
    private JPanel statPanel;
    private CellView[] cellPanels;
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
        islandGui.mainWindow();
    }

    private JToolBar createToolBar() {
        JToolBar tb = new JToolBar();
        tb.setFloatable(false);
        tb.setAlignmentX(LEFT_ALIGNMENT);

//        tb.setBackground(Color.GREEN);  //?????????????????????????????

        JButton start = new JButton("Start");
        start.addActionListener(new StartButtonPress(this, island));
        JButton stop = new JButton("Stop");
        stop.addActionListener(new StopButtonPress(this, island));

        fileSelectLabel.setPreferredSize(new Dimension(300, fileSelectLabel.getPreferredSize().height));

        JButton fileSelectButton = new JButton("...");
        fileSelectButton.addActionListener(new SelectButtonPress(this, island));

        tb.add(start);
        tb.add(stop);
        tb.addSeparator();
        tb.addSeparator();
        tb.add(fileSelectLabel);
        tb.addSeparator();
        tb.add(fileSelectButton);

        return tb;
    }

    private JPanel createToolPanel(){
        JPanel toolPanel = new JPanel();
        toolPanel.setLayout(new BoxLayout(toolPanel, BoxLayout.X_AXIS));
        toolPanel.setAlignmentX(LEFT_ALIGNMENT);
        toolPanel.add(createToolBar());
        return toolPanel;
    }

    private JPanel createCentralPanel() {
        JPanel centralPanel = new JPanel();
        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(gridPanel);
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        centralPanel.add(scrollPane);
        return centralPanel;
    }

    public void mainWindow() {
        window = new JFrame("Island");
        window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setAlignmentX(LEFT_ALIGNMENT);

        gridPanel = new JPanel();
        gridPanel.setAlignmentX(LEFT_ALIGNMENT);
        gridPanel.setPreferredSize(new Dimension(2019, 779));

//        gridPanel.setBackground(Color.CYAN);   //?????????????????????????

        statPanel = new JPanel();
        statPanel.setLayout(new BoxLayout(statPanel, BoxLayout.Y_AXIS));
        statPanel.setAlignmentX(LEFT_ALIGNMENT);

//        statPanel.setBackground(Color.RED);  //?????????????????????????????????????

        predatorStatLabel = new JLabel("Predators - 0");
        omnivorousStatLabel = new JLabel("Omnivorous - 0");
        herbivoreStatLabel = new JLabel("Herbivores - 0");
        plantStatLabel = new JLabel("Plants - 0");

        statPanel.add(predatorStatLabel);
        statPanel.add(omnivorousStatLabel);
        statPanel.add(herbivoreStatLabel);
        statPanel.add(plantStatLabel);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.setAlignmentX(LEFT_ALIGNMENT);

        bottomPanel.add(statPanel);

        mainPanel.add(createToolPanel());
        mainPanel.add(createCentralPanel());
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(bottomPanel);
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

        cellPanels = new CellView[arraySize];

        for (int j = 0; j < heightIsland; j++) {
            for (int k = 0; k < widthIsland; k++) {
                CellView cellPanelClass = new CellView();
                cellPanels[i] = cellPanelClass;
                JPanel cellPanel = cellPanelClass.createCell();

                cellPanel.setToolTipText(toolTipText(i));

                gridPanel.add(cellPanel);
                setTextCellLabels(i);
                i++;
            }
        }
//        gridPanel.setPreferredSize(new Dimension(widthIsland * 200, heightIsland * 77));
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
        gridPanel.setPreferredSize(new Dimension(widthIsland * 100, heightIsland * 77));
        window.repaint();
        window.revalidate();
    }

    public void gridPanelUpdate() {
        gridPanel.setPreferredSize(new Dimension(widthIsland * 100, heightIsland * 77));
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
        JLabel predatorCellLabel = cellPanels[i].getPredatorCellLabel();
        if (predators != 0) {
            predatorCellLabel.setText(predatorString);
        } else {
            predatorCellLabel.setText("      ");
        }

        int omnivorous = (int)island.arrayCells[i].getAnimals().stream().filter(x -> x.getSpecies().parentType.equals("Omnivorous")).count();
        String omnivorousString = String.format("Omnivorous - %d", omnivorous);
        JLabel omnivorousCellLabel = cellPanels[i].getOmnivorousCellLabel();
        if (omnivorous != 0) {
            omnivorousCellLabel.setText(omnivorousString);
        } else {
            omnivorousCellLabel.setText("      ");
        }

        int herbivores = (int)island.arrayCells[i].getAnimals().stream().filter(x -> x.getSpecies().parentType.equals("Herbivore")).count();
        String herbivoreString = String.format("Herbivores - %d", herbivores);
        JLabel herbivoreCellLabel = cellPanels[i].getHerbivoreCellLabel();
        if (herbivores != 0) {
            herbivoreCellLabel.setText(herbivoreString);
        } else {
            herbivoreCellLabel.setText("      ");
        }

        StringBuffer sba = new StringBuffer();
        JLabel animalCellLabel = cellPanels[i].getAnimalCellLabel();
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
        JLabel plantCellLabel = cellPanels[i].getPlantCellLabel();
        if (island.arrayCells[i].getPlantCount() != 0) {
            plantCellLabel.setBackground(Color.GREEN);
            plantCellLabel.setText("🌱🌱🌱🌱🌱🌱");
        } else {
            plantCellLabel.setBackground(null); //Color.LIGHT_GRAY);
            plantCellLabel.removeAll();
            plantCellLabel.setText("      ");
        }

        cellPanels[i].getCellPanel().setToolTipText(toolTipText(i));
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
