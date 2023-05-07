package island.animal.model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Plant implements Runnable{
    PrintWriter log;
    FileWriter logFile;
    @Override
    public void run() {
        try {
            logFile = new FileWriter("log.txt", true);
            log = new PrintWriter(logFile);
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }
        log.println("The plant is planted");
        log.flush();
    }
}
