package island.animal.model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class Logger {
    static PrintWriter log;
    static FileWriter logFile;
    public static void printLog(String txt) {
        String message = new Date() + " - " + txt;
        System.out.println(message);
        try {
            logFile = new FileWriter("log.txt", true);
            log = new PrintWriter(logFile);
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }
        log.println(message);
        log.flush();
    }
}
