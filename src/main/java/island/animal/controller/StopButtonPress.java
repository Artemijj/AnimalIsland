package island.animal.controller;

import island.animal.model.Island;
import island.animal.view.IIslandGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StopButtonPress implements ActionListener {
    private IIslandGui islandGui;
    private Island island;

    public StopButtonPress(IIslandGui islandGui, Island island) {
        this.islandGui = islandGui;
        this.island = island;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        island.stop();
        islandGui.panelUpdateStop();
    }
}
