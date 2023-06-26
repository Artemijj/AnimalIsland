package island.animal.model;

import java.io.*;
import java.util.Date;

public class Logger {
    private static PrintWriter log;
    private static FileWriter logFile;
    private static PrintWriter error;
    private static FileWriter errorFile;
    private static PrintStream ps;

    public synchronized static void printLog(String txt) {
        if (logFile == null) {
            try {
                logFile = new FileWriter("log.txt");
                log = new PrintWriter(logFile);
            } catch (IOException ex) {
                printError(ex);
//                ex.printStackTrace();
                return;
            }
        }
        String message = new Date() + " - " + txt;
        System.out.println(message);
        log.println(message);
        log.flush();
    }

    public synchronized static void printError(Exception exception) {
        if (error == null) {
            try {
                errorFile = new FileWriter("error.txt");
                error = new PrintWriter(errorFile);
            } catch (IOException ex) {
                ex.printStackTrace();
                return;
            }
        }
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);
        error.println(new Date());
        error.println(sw);
        error.println();
        error.flush();
    }
}
