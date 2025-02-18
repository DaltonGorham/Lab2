package EventCalender;
import javax.swing.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventPlanner {

    // method to add the default events : one deadline, one meeting
    public static void addDefaultEvents(List<Event> defaultEvents) {
        defaultEvents.add(new Meeting("Standup Meeting", LocalDateTime.of(2025, 10, 15, 10, 0),
                LocalDateTime.of(2023, 10, 15, 11, 0), "Room 212"));

        defaultEvents.add(new Deadline("Java Lab 2", LocalDateTime.of(2025, 2, 18, 11, 59)));
    }

    // main method to launch gui
    public static void main(String[] args) {
        JFrame frame = new JFrame("EventPlanner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        EventListPanel listPanel = new EventListPanel(frame);

        // add the default events
        ArrayList<Event> defaultEvents = new ArrayList<>();
        addDefaultEvents(defaultEvents);
        for (Event event : defaultEvents) {
            listPanel.addEvent(event);
        }
        frame.add(listPanel);

        frame.pack();
        frame.setSize(1920, 1080);
        frame.setVisible(true);
    }
}

