package EventCalender;

import javax.swing.*;
import java.awt.*;

public class EventPanel extends JPanel {
    private Event event;
    private JPanel eventDetailsPanel;
    private JButton completeButton;
    private JLabel nameLabel, timeLabel, durationLabel, locationLabel, completedLabel;



    EventPanel(Event event) {
        this.event = event;

        setLayout(new BorderLayout(5, 5));
        setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
        JPanel eventDetailsPanel = new JPanel();
        eventDetailsPanel.setLayout(new BoxLayout(eventDetailsPanel, BoxLayout.Y_AXIS));
        eventDetailsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(eventDetailsPanel, BorderLayout.CENTER);

        nameLabel = new JLabel("Event: " + event.getName());
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        eventDetailsPanel.add(nameLabel);
        timeLabel = new JLabel("Time: " + event.getDateTime());
        eventDetailsPanel.add(timeLabel);

        eventDetailsPanel.add(Box.createVerticalGlue());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));


        if (event instanceof Meeting meeting) {
            locationLabel = new JLabel("Location: " + meeting.getLocation());
            eventDetailsPanel.add(locationLabel);

            durationLabel = new JLabel("Duration: " + meeting.getDuration());
            eventDetailsPanel.add(durationLabel);
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
