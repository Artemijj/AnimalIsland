package island.animal.view;

import island.animal.controller.SelectButtonPress;
import island.animal.controller.StartButtonPress;
import island.animal.controller.StopButtonPress;
import island.animal.model.*;

import java.text.DecimalFormat;

import javax.swing.*;

import java.awt.*;

import static java.awt.Component.LEFT_ALIGNMENT;

public class IslandGui implements IIslandGui{
//    private ModelParameter modelParameter; // = new ModelParameter(null);
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
    private JPanel bottomPanel;
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
        islandGui.createMainWindow();
    }


    private JToolBar createToolBar() {
        JToolBar tb = new JToolBar();
        tb.setFloatable(false);
        tb.setAlignmentX(LEFT_ALIGNMENT);

//        tb.setBackground(Color.GREEN);  //?????????????????????????????

        JButton start = new JButton("Start");
        start.addActionListener(new StartButtonPress(this, island, islandGuiUpdate));
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
        JScrollPane scrollPane = new JScrollPane(createGridPanel());
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        centralPanel.add(scrollPane);
        return centralPanel;
    }

    private JPanel createGridPanel() {
        gridPanel = new JPanel();
        gridPanel.setAlignmentX(LEFT_ALIGNMENT);
        gridPanel.setPreferredSize(new Dimension(2019, 779));
        return gridPanel;
    }

    private JPanel createStatPanel() {
        statPanel = new JPanel();
        statPanel.setLayout(new BoxLayout(statPanel, BoxLayout.Y_AXIS));
        statPanel.setAlignmentX(LEFT_ALIGNMENT);

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

        return statPanel;
    }

    private JPanel createBottomPanel() {
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.setAlignmentX(LEFT_ALIGNMENT);
        bottomPanel.add(createStatPanel());
        return bottomPanel;
    }

    public JFrame createMainWindow() {
        window = new JFrame("Island");
        window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setAlignmentX(LEFT_ALIGNMENT);

        mainPanel.add(createToolPanel());
        mainPanel.add(createCentralPanel());
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(createBottomPanel());
        mainPanel.add(Box.createVerticalStrut(5));
        window.add(mainPanel);
        window.pack();
        window.setVisible(true);

        return window;
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

                cellPanel.setToolTipText(cellPanelClass.toolTipText(island.arrayCells[i]));

                gridPanel.add(cellPanel);
                cellPanelClass.setTextCellLabels(island.arrayCells[i]);
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
