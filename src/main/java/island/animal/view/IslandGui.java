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

import java.awt.*;

import static java.awt.Component.LEFT_ALIGNMENT;

public class IslandGui implements IIslandGui{
    private static ModelParameter modelParameter = new ModelParameter("");
    private static Island island = new Island(modelParameter);

    public IslandGui(Island island) {
        this.island = island;
    }

    private JFrame window;
    private JToolBar tb;
    private JButton start;
    private JButton stop;
//    private JCheckBox checkBox1;
//    private JRadioButton radioButton1;
//    private JLabel radioButtonLabel1;
//    private JRadioButton radioButton2;
    private JPanel parameterPanel;
    private JPanel horizontalPanel;
    private JLabel fileSelectLabel;
    private JButton fileSelectButton;
//    private JCheckBox checkBox2;
//    private JLabel checkBoxLabel2;
    private JPanel gridPanel;
    private JPanel statPanel;
    private JLabel predatorCellLabel;
    private JLabel omnivorousCellLabel;
    private JLabel herbivoreCellLabel;
    private JPanel animalCellPanel;
    private JPanel plantCellPanel;
    private int widthIsland = island.getModelParameter().getWidthIsland();
    private int heightIsland = island.getModelParameter().getHeightIsland();
    private JLabel predatorStatLabel;
    private JLabel omnivorousStatLabel;
    private JLabel herbivoreStatLabel;
    private JLabel plantStatLabel;
    private DecimalFormat myFormat = new DecimalFormat("#.##");
    private ScheduledExecutorService ses;

    public JPanel getGridPanel() {
        return gridPanel;
    }

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

        start = new JButton("Start");
        start.addActionListener(new StartButtonPress(this, island));
        stop = new JButton("Stop");
        stop.addActionListener(new StopButtonPress(this, island));
//        radioButton1 = new JRadioButton();
//        radioButtonLabel1 = new JLabel("Parameter file 1.");
//        radioButton2 = new JRadioButton();
//        radioButtonLabel2 = new JLabel("Parameter file 2.");

        tb.add(start);
        tb.add(stop);
        tb.addSeparator();
        tb.addSeparator();
//        tb.add(radioButton1);
//        tb.addSeparator();
//        tb.add(radioButtonLabel1);
//        tb.addSeparator();
//        tb.add(radioButton2);
//        tb.addSeparator();
//        tb.add(radioButtonLabel2);

//        parameterPanel = new JPanel();
//        parameterPanel.setLayout(new BoxLayout(parameterPanel, BoxLayout.Y_AXIS));
//
//        horizontalPanel = new JPanel();
//        horizontalPanel.setLayout(new BoxLayout(horizontalPanel, BoxLayout.X_AXIS));
//        horizontalPanel.setBackground(Color.RED);

        fileSelectLabel = new JLabel("Select preference file.");
        fileSelectLabel.setPreferredSize(new Dimension(200, fileSelectLabel.getPreferredSize().height));

        fileSelectButton = new JButton("...");
        fileSelectButton.addActionListener(new SelectButtonPress(this, island));

        tb.add(fileSelectLabel);
//        horizontalPanel.add(Box.createHorizontalGlue());
        tb.addSeparator();
        tb.add(fileSelectButton);

//        parameterPanel.add(horizontalPanel);



        gridPanel = new JPanel(new GridLayout(heightIsland, widthIsland, 1, 1));
        gridPanel.setAlignmentX(LEFT_ALIGNMENT);
////        loadGridPanel();
//        gridPanel.repaint();
//        gridPanel.revalidate();
//        gridPanelUpdateStart();


        statPanel = new JPanel();
        statPanel.setLayout(new BoxLayout(statPanel, BoxLayout.Y_AXIS));
//        statPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        predatorStatLabel = new JLabel();
        omnivorousStatLabel = new JLabel();
        herbivoreStatLabel = new JLabel();
        plantStatLabel = new JLabel();
        loadStatPanel();

        mainPanel.add(tb);
//        mainPanel.add(parameterPanel);
        mainPanel.add(gridPanel);
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

    private void loadGridPanel() {
        gridPanel.removeAll();
        int i = 0;
        for (int j = 0; j < heightIsland; j++) {
            for (int k = 0; k < widthIsland; k++) {
                JPanel cellPanel = new JPanel();//new FlowLayout(FlowLayout.LEFT));
                cellPanel.setLayout(new BoxLayout(cellPanel, BoxLayout.Y_AXIS));
                cellPanel.setBorder(BorderFactory.createLineBorder(Color.black));

                cellPanel.setToolTipText(toolTipText(i));

                int predators = (int)island.arrayCells[i].getAnimals().stream().filter(x -> x.getSpecies().parentType.equals("Predator")).count();
                String predatorString = String.format("Predators - %d", predators);
                predatorCellLabel = new JLabel();
                if (predators != 0) {
                    predatorCellLabel.setText(predatorString);
                } else {
                    predatorCellLabel.setText("   ");
                }

                int omnivorous = (int)island.arrayCells[i].getAnimals().stream().filter(x -> x.getSpecies().parentType.equals("Omnivorous")).count();
                String omnivorousString = String.format("Omnivorous - %d", omnivorous);
                omnivorousCellLabel = new JLabel();
                if (omnivorous != 0) {
                    omnivorousCellLabel.setText(omnivorousString);
                } else {
                    omnivorousCellLabel.setText("   ");
                }

                int herbivores = (int)island.arrayCells[i].getAnimals().stream().filter(x -> x.getSpecies().parentType.equals("Herbivore")).count();
                String herbivoreString = String.format("Herbivores - %d", herbivores);
                herbivoreCellLabel = new JLabel();
                if (herbivores != 0) {
                    herbivoreCellLabel.setText(herbivoreString);
                } else {
                    herbivoreCellLabel.setText("   ");
                }

                animalCellPanel = new JPanel();
                if (island.arrayCells[i].getAnimals().size() != 0) {
                    animalCellPanel.setBackground(Color.RED);
                }
                plantCellPanel = new JPanel();
                if (island.arrayCells[i].getPlantCount() != 0) {
                    plantCellPanel.setBackground(Color.GREEN);
                }

                cellPanel.add(predatorCellLabel);
                cellPanel.add(omnivorousCellLabel);
                cellPanel.add(herbivoreCellLabel);
                cellPanel.add(animalCellPanel);
                cellPanel.add(plantCellPanel);


//        panel.add(component);//, BorderLayout.SOUTH);
//        panel.add(Box.createHorizontalGlue());
                gridPanel.add(cellPanel);
                i++;
            }
        }
        gridPanel.repaint();
        gridPanel.revalidate();
    }

    private void loadStatPanel() {
        statPanel.removeAll();
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
        statPanel.add(predatorStatLabel);
        statPanel.add(omnivorousStatLabel);
        statPanel.add(herbivoreStatLabel);
        statPanel.add(plantStatLabel);

        statPanel.repaint();
        statPanel.revalidate();
    }

    public void gridPanelUpdate() {
        gridPanel.repaint();
        gridPanel.revalidate();
    }

    private void gridPanelUpdateStart() {
        GridPanelUpdate gpu = new GridPanelUpdate(this);
        Timer timer = new Timer(5000, gpu);
        timer.start();
    }

    public void panelUpdateStart() {
        ses = Executors.newScheduledThreadPool(5);
        ses.scheduleWithFixedDelay(() -> loadGridPanel(), 1, 5, TimeUnit.SECONDS);
        ses.scheduleWithFixedDelay(() -> loadStatPanel(), 1, 5, TimeUnit.SECONDS);
        ses.scheduleWithFixedDelay(() -> {
            if (island.getAnimalCount() == 0) {
                System.out.println("Count of animal is " + island.getAnimalCount());
//                System.exit(0);
                island.stop();
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
                sb.append(animal.getSpecies() + " " + animal.getSpecies().icon + " (" + animal.getUuid() + " ) weight " + myFormat.format(animal.getWeight()) + "kg." + "<br>");
            }
        }
        sb.append("Plants - " + island.getCell(i).getPlantCount() + "kg." + "</html>");
        return sb.toString();
    }
}
