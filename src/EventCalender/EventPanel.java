package EventCalender;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class EventPanel extends JPanel {
    private Event event;
    private JPanel eventDetailsPanel;
    private JLabel nameLabel, startTimeLabel, endTimeLabel, durationLabel, locationLabel, completedLabel, dateLabel;
    private JCheckBox completeCheckbox;
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM d, yyyy");  // formats for the dates
    DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("h:mm a");       // formats for the times

    // constructor
    EventPanel(Event event) {
        this.event = event;

        setLayout(new BorderLayout(5, 5));
        setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
        eventDetailsPanel = new JPanel();
        eventDetailsPanel.setLayout(new BoxLayout(eventDetailsPanel, BoxLayout.Y_AXIS));
        eventDetailsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(eventDetailsPanel, BorderLayout.CENTER);

        // Name
        nameLabel = new JLabel("Event: " + event.getName());
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        eventDetailsPanel.add(nameLabel);
        eventDetailsPanel.add(Box.createVerticalGlue());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));


        // If the event is a meeting, this will be the output
        if (event instanceof Meeting meeting) {
            dateLabel = new JLabel("Date: " + meeting.getDateTime().format(dateFormat));
            eventDetailsPanel.add(dateLabel);

            startTimeLabel = new JLabel("Start Time: " + meeting.getStartDateTime().format(timeFormat));
            eventDetailsPanel.add(startTimeLabel);

            endTimeLabel = new JLabel("End Time: " + meeting.getEndDateTime().format(timeFormat));
            eventDetailsPanel.add(endTimeLabel);

            durationLabel = new JLabel("Duration: " + meeting.getDuration());
            eventDetailsPanel.add(durationLabel);

            locationLabel = new JLabel("Location: " + meeting.getLocation());
            eventDetailsPanel.add(locationLabel);
        }


        // If the event is a deadline, this will be the output
        if (event instanceof Deadline deadline) {
            dateLabel = new JLabel("Date: " + deadline.getDateTime().format(dateFormat));
            eventDetailsPanel.add(dateLabel);

            endTimeLabel = new JLabel("Due: " + deadline.getDateTime().format(timeFormat));
            eventDetailsPanel.add(endTimeLabel);
        }

        // Both meetings and deadlines can be marked as complete
        if (event instanceof Completable) {
            Completable completable = (Completable) event;
            completedLabel = new JLabel("Completed: " + completable.isCompleted());
            eventDetailsPanel.add(completedLabel);

            completeCheckbox = new JCheckBox("Complete");
            completeCheckbox.addActionListener(e -> {
                completable.complete();
                completedLabel.setText("Completed: " + completable.isCompleted());

            });
            buttonPanel.add(completeCheckbox);
        }
        add(buttonPanel, BorderLayout.EAST);
    }
}
