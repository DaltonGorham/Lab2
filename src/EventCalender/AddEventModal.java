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
        setLocationRelativeTo(parent);
        setVisible(true);

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
        });



        confirmEventButton.addActionListener(e -> {
            if (eventNameField.getText().isEmpty() || eventDateField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Event name and date are required! Try Again.",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (isEntryValid()) {
                eventType = (String) eventDropDown.getSelectedItem();
                eventName = eventNameField.getText();
                eventDate = eventDateField.getText();
                if ("Meeting".equals(eventType)){
                    eventStartTime = eventStartTimeField.getText();
                    eventEndTime = eventEndTimeField.getText();
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
    private boolean isEntryValid(){
        if ("Meeting".equals(eventDropDown.getSelectedItem()) && eventEndTimeField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Event Time is required for a Meeting!",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
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

