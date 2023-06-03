package island.animal.model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Plant implements Runnable{
    private Island island;

    public Plant(Island island) {
        this.island = island;
    }

    //    PrintWriter log;
//    FileWriter logFile;
    @Override
    public void run() {
        island.arrayCells[RandomValue.getIntRandom(island.getWidth() * island.getHeight())].addPlant(1);
        Logger.printLog("The plant is planted");
//        try {
//            logFile = new FileWriter("log.txt", true);
//            log = new PrintWriter(logFile);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            return;
//        }
//        log.println("The plant is planted");
//        log.flush();
    }
}
