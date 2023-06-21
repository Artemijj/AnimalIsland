package island.animal.view;

public interface IIslandGui {
    int getWidthIsland();
    int getHeightIsland();
    void mainWindow();
    void loadGridPanel();
    void setTextCellLabels(int i);
    void loadStatPanel();
//    void gridPanelUpdate();
    void setFileSelectLabel(String filename);
//    void panelUpdateStart();
//    void panelUpdateStop();
    void alertDialog(String alert);
}
