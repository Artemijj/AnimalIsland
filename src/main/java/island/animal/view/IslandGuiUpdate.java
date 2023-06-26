package island.animal.view;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import island.animal.model.island.Island;
import island.animal.util.Logger;

import java.util.concurrent.ScheduledExecutorService;

public class IslandGuiUpdate {

    private Island island;
    private IIslandGui islandGui;
    private StatView statView;

    public IslandGuiUpdate(Island island, IIslandGui islandGui) {
        this.island = island;
        this.islandGui = islandGui;
        statView = new StatView(island, islandGui);
    }

    private ScheduledExecutorService ses;

    public void panelIslandGuiUpdateStart() {
        try {
            ses = Executors.newScheduledThreadPool(5, r -> {
                Thread t = Executors.defaultThreadFactory().newThread(r);
                t.setPriority(Thread.MAX_PRIORITY);
                return t;
            });
            ses.scheduleWithFixedDelay(() -> gridPanelIslandGuiUpdateStart(), 1, 5, TimeUnit.SECONDS);
            ses.scheduleWithFixedDelay(() -> statView.loadStatPanel(), 1, 5, TimeUnit.SECONDS);
            ses.scheduleWithFixedDelay(() -> {
                if (island.getAnimalCount() == 0) {
                    System.out.println("Count of animal is " + island.getAnimalCount());
                    island.stop();
                    islandGui.alertDialog("<html>" + "This is the END." + "<br>" + "<br>" + "All animals is dead..." + "</html>");
                }
            }, 1, island.getModelParameter().getDurationOfTact() * 10, TimeUnit.MILLISECONDS);
        } catch (Exception ex) {
            Logger.printError(ex);
//            ex.printStackTrace();
        }
    }

    public void panelUpdateStop() {
        ses.shutdown();
    }

    private void gridPanelIslandGuiUpdateStart() {
        try {
            int i = 0;
            for (int j = 0; j < islandGui.getHeightIsland(); j++) {
                for (int k = 0; k < islandGui.getWidthIsland(); k++) {
                    islandGui.getCellPanels()[i].setTextCellLabels(island.arrayCells[i]);
                    i++;
                }
            }
        } catch (Exception ex) {
            Logger.printError(ex);
        }
    }
}
