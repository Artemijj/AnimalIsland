package island.animal.view;

import javax.swing.*;

public interface IIslandGui {
    JFrame createMainWindow();
    int getWidthIsland();
    int getHeightIsland();
    CellView[] getCellPanels();
    JLabel getStatLabelArray(int i);
    void loadGridPanel();
    void setFileSelectLabel(String filename);
    void alertDialog(String alert);
}
