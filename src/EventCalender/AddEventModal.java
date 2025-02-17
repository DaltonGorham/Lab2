package EventCalender;

import javax.swing.*;
import java.awt.*;

public class AddEventModal extends JDialog {

    private String eventType;
    private String eventName;
    private String eventDate;
    private String eventStartTime;
    private String eventEndTime;
    private String eventLocation;
    private JTextField eventNameField;
    private JTextField eventDateField;
    private JTextField eventStartTimeField;
    private JTextField eventEndTimeField;
    private JComboBox<String> eventDropDown;
    private JTextField eventLocationField;
    private boolean isConfirmed;


    public AddEventModal(Frame parent) {
        super(parent, "Add Event", true);
        addComponentsToPane();
        setSize(600,400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);
    }

    private void addComponentsToPane() {
        // event type
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2, 10, 10));
        panel.add(new JLabel("Event Type: "));
        eventDropDown = new JComboBox<>(new String[]{"Meeting", "Deadline"});
        panel.add(eventDropDown);

        // name field
        panel.add(new JLabel("Event Name: "));
        eventNameField = new JTextField(15);
        panel.add(eventNameField);

        // date field
        panel.add(new JLabel("Event Date: "));
        eventDateField = new JTextField(10);
        panel.add(eventDateField);

        // time field
        panel.add(new JLabel("Event Start Time (HH:MM AM/PM"));
        eventStartTimeField = new JTextField(10);
        panel.add(eventStartTimeField);

        panel.add(new JLabel("Event End Time (HH:MM AM/PM"));
        eventEndTimeField = new JTextField(10);
        panel.add(eventEndTimeField);

        panel.add(new JLabel("Event Location"));
        eventLocationField = new JTextField(15);
        panel.add(eventLocationField);


        JButton confirmEventButton = new JButton("Add Event");
        JButton cancelEventButton = new JButton("Cancel");
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(confirmEventButton);
        buttonsPanel.add(cancelEventButton);


        eventDropDown.addActionListener(e -> {
            boolean isMeeting = "Meeting".equals(eventDropDown.getSelectedItem());
            eventStartTimeField.setEnabled(isMeeting);
            eventLocationField.setEnabled(isMeeting);
            eventEndTimeField.setEnabled(isMeeting);
            boolean isDeadline = "Deadline".equals(eventDropDown.getSelectedItem());
            eventDateField.setEnabled(isDeadline);
            eventEndTimeField.setEnabled(isDeadline);
        });





        confirmEventButton.addActionListener(e -> {
            if (isEntryValid()) {
                eventType = (String) eventDropDown.getSelectedItem();
                eventName = eventNameField.getText();
                eventDate = eventDateField.getText();
                eventEndTime = eventEndTimeField.getText();
                if ("Meeting".equals(eventType)){
                    eventStartTime = eventStartTimeField.getText();
                    eventLocation = eventLocationField.getText();
                }
                isConfirmed = true;
                dispose();
            }

        });


        cancelEventButton.addActionListener(e -> dispose());

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);


    }
    private boolean isEntryValid() {
        // Check if event name is empty
        if (eventNameField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Event Name is required!",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validate the event date format (MM/DD/YYYY)
        if (eventDateField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Event Date is required!",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (!eventDateField.getText().matches("\\d{2}/\\d{2}/\\d{4}")) {
            JOptionPane.showMessageDialog(this, "Event Date must be in MM/DD/YYYY format!",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validate meeting-specific fields
        if ("Meeting".equals(eventType)) {
            if (eventStartTimeField.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Event Start Time is required!",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (eventEndTimeField.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Event End Time is required!",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (eventLocationField.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Event Location is required!",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
            }

            // Validate time format (HH:MM AM/PM)
            if (!isValidTimeFormat(eventStartTimeField.getText()) || !isValidTimeFormat(eventEndTimeField.getText())) {
                JOptionPane.showMessageDialog(this, "Event Start Time and End Time must be in HH:MM AM/PM format!",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        return true;
    }

    // This is to validate the format. @StackOverFlow
    private boolean isValidTimeFormat(String time) {
        return time.matches("^(1[0-2]|0?[1-9]):([0-5][0-9])\\s?[APap][Mm]$");
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }


    public String getEventType() {
        return eventType;
    }

    public String getEventName() {
        return eventName;
    }


    public String getEventDate() {
        return eventDate;
    }

    public String getEventStartTime() {
        return eventStartTime;
    }

    public String getEventEndTime() {
        return eventEndTime;
    }

    public String getEventLocation() {
        return eventLocation;
    }
}

