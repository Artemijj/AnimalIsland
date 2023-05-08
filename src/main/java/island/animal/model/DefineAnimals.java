package island.animal.model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DefineAnimals implements Runnable{
//    PrintWriter log;
//    FileWriter logFile;
    @Override
    public void run() {
        Logger.printLog("The animal is created");
//        try {
//            logFile = new FileWriter("log.txt", true);
//            log = new PrintWriter(logFile);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            return;
//        }
//        log.println("The animal is created");
//        log.flush();
    }
}
