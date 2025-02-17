package EventCalender;

import javax.swing.*;
import java.time.LocalDateTime;

public class EventPlanner {

    public static void addDefaultEvents(EventListPanel events) {

    }



    public static void main(String[] args) {
        JFrame frame = new JFrame("EventPlanner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        EventListPanel listPanel = new EventListPanel(frame);


        frame.add(listPanel);


        frame.pack();
        frame.setVisible(true);
    }
}
