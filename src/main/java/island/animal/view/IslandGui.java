package island.animal.view;

import island.animal.controller.SelectButtonPress;
import island.animal.controller.StartButtonPress;
import island.animal.controller.StopButtonPress;
import island.animal.model.*;

import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

import java.awt.*;

import static java.awt.Component.LEFT_ALIGNMENT;

public class IslandGui implements IIslandGui{
    private ModelParameter modelParameter; // = new ModelParameter(null);
    private Island island; // = new Island(modelParameter);
    private IslandGuiUpdate islandGuiUpdate;
    private int widthIsland;
    private int heightIsland;

    public IslandGui(Island island) {
        this.island = island;
        islandGuiUpdate = new IslandGuiUpdate(island, this);
        widthIsland = island.getModelParameter().getWidthIsland();
        heightIsland = island.getModelParameter().getHeightIsland();
    }

    private JFrame window;
    private JPanel mainPanel;
    private JLabel fileSelectLabel = new JLabel("Select preference file.");
    private JPanel gridPanel;
    private JPanel statPanel;
    private CellView[] cellPanels;
//    private int widthIsland = island.getModelParameter().getWidthIsland();
//    private int heightIsland = island.getModelParameter().getHeightIsland();
    private JLabel predatorStatLabel;
    private JLabel omnivorousStatLabel;
    private JLabel herbivoreStatLabel;
    private JLabel plantStatLabel;
    private JLabel[] statLabelArray = new JLabel[4];
    private DecimalFormat myFormat = new DecimalFormat("#.##");
//    private ScheduledExecutorService ses;


    public int getWidthIsland() {
        return widthIsland;
    }

    public int getHeightIsland() {
        return heightIsland;
    }

    public CellView[] getCellPanels() {
        return cellPanels;
    }

    public JLabel getStatLabelArray(int i) {
        return statLabelArray[i];
    }

    public static void main(String[] args) {
        ModelParameter modelParameter = new ModelParameter(null);
        Island island = new Island(modelParameter);
        IIslandGui islandGui = new IslandGui(island);
//        islandGuiUpdate = new IslandGuiUpdate(island, this);
        islandGui.mainWindow();
    }

    public void startButton(ActionEvent actionEvent) {
        island.start();
        loadGridPanel();
        islandGuiUpdate.panelIslandGuiUpdateStart();
    }


    private JToolBar createToolBar() {
        JToolBar tb = new JToolBar();
        tb.setFloatable(false);
        tb.setAlignmentX(LEFT_ALIGNMENT);
        JButton start = new JButton("Start");
        start.addActionListener(x -> startButton(x));
        JButton stop = new JButton("Stop");
        stop.addActionListener(new StopButtonPress(this, island, islandGuiUpdate));

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

        statLabelArray[0] = predatorStatLabel = new JLabel("Predators - 0");
//        statLabelArray[0] = plantStatLabel;
        statLabelArray[1] = omnivorousStatLabel = new JLabel("Omnivorous - 0");
//        statLabelArray[1] = omnivorousStatLabel;
        statLabelArray[2] = herbivoreStatLabel = new JLabel("Herbivores - 0");
//        statLabelArray[2] = herbivoreStatLabel;
        statLabelArray[3] = plantStatLabel = new JLabel("Plants - 0");
//        statLabelArray[3] = plantStatLabel;


        statPanel.add(statLabelArray[0]);
        statPanel.add(statLabelArray[1]);
        statPanel.add(statLabelArray[2]);
        statPanel.add(statLabelArray[3]);

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
                CellView cellPanelClass = new CellView(island, this);
                cellPanels[i] = cellPanelClass;
                JPanel cellPanel = cellPanelClass.createCell();

                cellPanel.setToolTipText(cellPanelClass.toolTipText(i));

                gridPanel.add(cellPanel);
                cellPanelClass.setTextCellLabels(i);
                i++;
            }
        }
//        gridPanel.setPreferredSize(new Dimension(widthIsland * 100, heightIsland * 77));
        gridPanel.setPreferredSize(new Dimension(widthIsland * 100, heightIsland * 77));
        window.repaint();
        window.revalidate();
    }



    public void alertDialog(String alert) {
        JOptionPane.showMessageDialog(window, alert);
    }
}
