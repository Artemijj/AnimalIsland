package island.animal.view;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import island.animal.model.*;

import java.util.concurrent.ScheduledExecutorService;

public class IslandGuiUpdate {

//    private static ModelParameter modelParameter = new ModelParameter(null);
    private Island island;
    private IIslandGui islandGui;
    private CellView cellView;

    public IslandGuiUpdate(Island island, IIslandGui islandGui) {
        this.island = island;
        this.islandGui = islandGui;
        cellView = new CellView(island, islandGui);
    }

    private ScheduledExecutorService ses;

    public void panelIslandGuiUpdateStart() {
        ses = Executors.newScheduledThreadPool(5);
        ses.scheduleWithFixedDelay(() -> gridPanelIslandGuiUpdateStart(), 1, 5, TimeUnit.SECONDS);
        ses.scheduleWithFixedDelay(() -> islandGui.loadStatPanel(), 1, 5, TimeUnit.SECONDS);
        ses.scheduleWithFixedDelay(() -> {
            if (island.getAnimalCount() == 0) {
                System.out.println("Count of animal is " + island.getAnimalCount());
                island.stop();
                islandGui.alertDialog("<html>" + "This is the END." + "<br>" + "<br>" + "All animals is dead..." + "</html>");
            }
        }, 1, island.getModelParameter().getDurationOfTact() * 10, TimeUnit.MILLISECONDS);
    }

    public void panelUpdateStop() {
        ses.shutdown();
    }

    private void gridPanelIslandGuiUpdateStart() {
        int i = 0;
        for (int j = 0; j < islandGui.getHeightIsland(); j++) {
            for (int k = 0; k < islandGui.getWidthIsland(); k++) {
                cellView.setTextCellLabels(i);
                i++;
            }
        }
    }
}
