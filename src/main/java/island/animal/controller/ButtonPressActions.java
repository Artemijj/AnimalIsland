package island.animal.controller;

import island.animal.model.Island;
import island.animal.view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class ButtonPressActions {
    private Island island;
    private IIslandGui islandGui;
    private IslandGuiUpdate islandGuiUpdate;


    public ButtonPressActions(Island island, IIslandGui islandGui, IslandGuiUpdate islandGuiUpdate) {
        this.island = island;
        this.islandGui = islandGui;
        this.islandGuiUpdate = islandGuiUpdate;
    }
    public void startButton() {
        island.start();
        islandGui.loadGridPanel();
        islandGuiUpdate.panelIslandGuiUpdateStart();
    }

    public void stopButton() {
        island.stop();
        islandGuiUpdate.panelUpdateStop();
    }

    public void selectButton() {
        JFileChooser fileOpen = new JFileChooser();
        int ret = fileOpen.showDialog(null, "Open file");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileOpen.getSelectedFile();
                    islandGui.setFileSelectLabel(file.getAbsolutePath());
                    if (!island.getModelParameter().readParameters(file.getAbsolutePath())) {
                        islandGui.alertDialog("<html>" + "Wrong file!" + "<br>" + "<br>" + "Select correct file." + "</html>");
                    }
                }

    }
}
