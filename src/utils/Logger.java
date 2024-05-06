package utils;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


//TODO singleton unit test

public class Logger {
    private static Logger instance;

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void cleanConsole(JFrame frame) {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cleanHistory();
                System.exit(0); // Close the application
            }
        });
    }

    public static void cleanHistory() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
