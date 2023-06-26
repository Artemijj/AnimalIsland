package island.animal;

import island.animal.model.island.Island;
import island.animal.util.Logger;
import island.animal.model.island.ModelParameter;
import island.animal.view.IslandConsole;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ModelParameter modelParameter = new ModelParameter(null);
        Island island = new Island(modelParameter);

        try {
            ScheduledExecutorService ses = Executors.newScheduledThreadPool(5);
            ses.scheduleWithFixedDelay(new IslandConsole(island), 5, modelParameter.getDurationOfTact() * 5, TimeUnit.MILLISECONDS);
            ses.scheduleWithFixedDelay(() -> {
                if (island.getAnimalCount() == 0) {
                    System.out.println("Count of animal is " + island.getAnimalCount());
                    System.exit(0);
                }
            }, 1, modelParameter.getDurationOfTact() * 10, TimeUnit.MILLISECONDS);
            island.start();
        } catch (Exception ex) {
            Logger.printError(ex);
//            ex.printStackTrace();
        }

    }
}
