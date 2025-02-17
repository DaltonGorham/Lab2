package EventCalender;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class EventPanel extends JPanel {
    private Event event;
    private JPanel eventDetailsPanel;
    private JButton completeButton;
    private JLabel nameLabel, startTimeLabel, endTimeLabel, durationLabel, locationLabel, completedLabel, dateLabel;
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM d, yyyy");
    DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("h:mm a");



    EventPanel(Event event) {
        this.event = event;

        setLayout(new BorderLayout(5, 5));
        setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
        eventDetailsPanel = new JPanel();
        eventDetailsPanel.setLayout(new BoxLayout(eventDetailsPanel, BoxLayout.Y_AXIS));
        eventDetailsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(eventDetailsPanel, BorderLayout.CENTER);

        nameLabel = new JLabel("Event: " + event.getName());
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        eventDetailsPanel.add(nameLabel);


        eventDetailsPanel.add(Box.createVerticalGlue());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));


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


        if (event instanceof Deadline deadline) {
            dateLabel = new JLabel("Date: " + deadline.getDateTime().format(dateFormat));
            eventDetailsPanel.add(dateLabel);

            endTimeLabel = new JLabel("Due: " + deadline.getDateTime().format(timeFormat));
            eventDetailsPanel.add(endTimeLabel);
        }

        if (event instanceof Completable) {
            Completable completable = (Completable) event;
            completedLabel = new JLabel("Completed: " + completable.isCompleted());
            eventDetailsPanel.add(completedLabel);

            completeButton = new JButton("Complete");
            completeButton.addActionListener(e -> {
                completable.complete();
                completedLabel.setText("Completed: " + completable.isCompleted());

            });
            buttonPanel.add(completeButton);
        }
        add(buttonPanel, BorderLayout.EAST);

    }

}
