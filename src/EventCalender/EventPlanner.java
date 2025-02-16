package EventCalender;

import javax.swing.*;
import java.time.LocalDateTime;

public class EventPlanner {





    public static void main(String[] args) {
        JFrame frame = new JFrame("EventPlanner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        EventListPanel listPanel = new EventListPanel();


        frame.add(listPanel);


        frame.pack();
        frame.setVisible(true);
    }
}
