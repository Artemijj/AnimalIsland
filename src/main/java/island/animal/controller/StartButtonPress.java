package island.animal.controller;

import island.animal.model.Island;
import island.animal.view.IIslandGui;
import island.animal.view.IslandGuiUpdate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartButtonPress implements ActionListener {
    private IIslandGui islandGui;
    private IslandGuiUpdate islandGuiUpdate;
    private Island island;

    public StartButtonPress(IIslandGui islandGui, Island island, IslandGuiUpdate islandGuiUpdate) {
        this.islandGui = islandGui;
        this.island = island;
        this.islandGuiUpdate = islandGuiUpdate;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        island.start();
        islandGui.loadGridPanel();
        islandGuiUpdate.panelIslandGuiUpdateStart();
    }
}
