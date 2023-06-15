package island.animal.controller;

import island.animal.view.IIslandGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GridPanelUpdate implements ActionListener {
    IIslandGui islandGui;

    public GridPanelUpdate(IIslandGui islandGui) {
        this.islandGui = islandGui;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        islandGui.gridPanelUpdate();
    }
}
