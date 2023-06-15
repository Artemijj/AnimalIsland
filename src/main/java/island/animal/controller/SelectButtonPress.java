package island.animal.controller;

import island.animal.model.Island;
import island.animal.view.IIslandGui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SelectButtonPress implements ActionListener {
    private IIslandGui islandGui;
    private Island island;

    public SelectButtonPress(IIslandGui islandGui, Island island) {
        this.islandGui = islandGui;
        this.island = island;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JFileChooser fileOpen = new JFileChooser();
        int ret = fileOpen.showDialog(null, "Open file");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileOpen.getSelectedFile();
                    islandGui.setFileSelectLabel(file.getAbsolutePath());
                    island.getModelParameter().readParameters(file.getAbsolutePath());
                }

    }
}
