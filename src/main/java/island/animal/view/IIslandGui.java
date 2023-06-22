package island.animal.view;

import javax.swing.*;

public interface IIslandGui {
    JFrame createMainWindow();
    int getWidthIsland();
    int getHeightIsland();
    CellView[] getCellPanels();
    JLabel getStatLabelArray(int i);
//    JFrame mainWindow();
    void loadGridPanel();
//    void setTextCellLabels(int i);
//    void loadStatPanel();
//    void gridPanelUpdate();
    void setFileSelectLabel(String filename);
//    void panelUpdateStart();
//    void panelUpdateStop();
    void alertDialog(String alert);
}
