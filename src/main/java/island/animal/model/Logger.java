package island.animal.model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class Logger {
    private static PrintWriter log;
    private static FileWriter logFile;
    public synchronized static  void  printLog(String txt) {
        if ( logFile == null) {
            try {
                logFile = new FileWriter("log.txt"); //, true);
                log = new PrintWriter(logFile);
            } catch (IOException ex) {
                ex.printStackTrace();
                return;
            }
        }
        String message = new Date() + " - " + txt;
        System.out.println(message);
        log.println(message);
        log.flush();
    }
}
