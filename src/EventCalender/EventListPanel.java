package EventCalender;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class EventListPanel extends JPanel {
    private ArrayList<Event> events;
    private JPanel controlPanel;
    private JPanel displayPanel;
    private JComboBox sortDropDown;
    private JCheckBox filterDisplay;
    private JButton addEventButton;
    private JFrame parentFrame;
    private JCheckBox hideEventsCheckbox;
    private JCheckBox hideDeadlinesCheckbox;
    private JCheckBox hideMeetingsCheckbox;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    public EventListPanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        String[] sortingOptions = {"Sort by Name", "Sort by Date", "Sort by Name(Reverse Order)", "Sort by Date(Reverse Order)"};
        events = new ArrayList<Event>();
        controlPanel = new JPanel();
        displayPanel = new JPanel();
        sortDropDown = new JComboBox<>(sortingOptions);
        filterDisplay = new JCheckBox("Filter Display");
        addEventButton = new JButton("Add Event");

        // Multiple filter checkboxes
        hideEventsCheckbox = new JCheckBox("Hide Events");
        hideDeadlinesCheckbox = new JCheckBox("Hide Deadlines");
        hideMeetingsCheckbox = new JCheckBox("Hide Meetings");


        controlPanel.add(addEventButton);
        controlPanel.add(sortDropDown);
        controlPanel.add(filterDisplay);
        controlPanel.add(hideEventsCheckbox);
        controlPanel.add(hideDeadlinesCheckbox);
        controlPanel.add(hideMeetingsCheckbox);
        setFilterCheckboxVisibility(false);
        add(controlPanel);

        displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
        displayPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(displayPanel);
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
        events.add(new Deadline("Meeting with Client", LocalDate.now().atStartOfDay()));

        handleSorting();
        handleFiltering();
        handleAddEvent();
        updateDisplay();

    }


    private void setFilterCheckboxVisibility(boolean visible) {
       hideEventsCheckbox.setVisible(visible);
       hideDeadlinesCheckbox.setVisible(visible);
       hideMeetingsCheckbox.setVisible(visible);
    }



    public void updateDisplay() {
        displayPanel.removeAll(); // Clear current display

        for (Event event : events) {
            boolean displaying = true;

            // Apply filters based on individual checkboxes
            if (event instanceof Completable && hideEventsCheckbox.isSelected()) {
                displaying = false; // Hide events (completable items)
            } else if (event instanceof Deadline && hideDeadlinesCheckbox.isSelected()) {
                displaying = false; // Hide deadlines
            } else if (event instanceof Meeting && hideMeetingsCheckbox.isSelected()) {
                displaying = false; // Hide meetings
            }

            if (displaying) {
                displayPanel.add(new EventPanel(event));
            }
        }

        // Refresh display
        displayPanel.revalidate();
        displayPanel.repaint();
    }

    public void handleSorting() {
        sortDropDown.addActionListener(e -> {
            String selectedOption = (String) sortDropDown.getSelectedItem();
            if (selectedOption.equals("Sort by Name")) {
                Collections.sort(events);
            }
            else if (selectedOption.equals("Sort by Date")){
                events.sort(Comparator.comparing(Event::getDateTime));
            }
            else if (selectedOption.equals("Sort by Name(Reverse Order)")) {
                Collections.sort(events, Collections.reverseOrder());
            }
            else if (selectedOption.equals("Sort by Date(Reverse Order)")) {
                events.sort(Comparator.comparing(Event::getDateTime).reversed());
            }


            updateDisplay();
        });

        updateDisplay();
    }


    private void handleFiltering() {
        filterDisplay.addActionListener(e -> {
            boolean isChecked = filterDisplay.isSelected();
            setFilterCheckboxVisibility(isChecked);
            updateDisplay();
        });
        hideEventsCheckbox.addActionListener(e -> updateDisplay());
        hideDeadlinesCheckbox.addActionListener(e -> updateDisplay());
        hideMeetingsCheckbox.addActionListener(e -> updateDisplay());
    }

    public void handleAddEvent() {
        addEventButton.addActionListener(e -> {
            AddEventModal modal = new AddEventModal(parentFrame);
            modal.setVisible(true);

            if (modal.isConfirmed()) {
                String eventType = modal.getEventType();
                if (eventType == null) {
                    return;
                }

                String eventName = modal.getEventName();
                String eventStartTime = modal.getEventStartTime();
                String eventEndTime = modal.getEventEndTime();
                String eventLocation = modal.getEventLocation();
                String eventDate = modal.getEventDate();


                System.out.println("Event Type: " + eventType);
                System.out.println("Event Name: " + eventName);
                System.out.println("Event Start Time: " + eventStartTime);
                System.out.println("Event End Time: " + eventEndTime);
                System.out.println("Event Location: " + eventLocation);
                System.out.println("Event Date: " + eventDate);

                events.add(new Deadline(eventName, parseDateTime(eventDate)));
                updateDisplay();
                System.out.println(events);

            }});
        updateDisplay();

    }

    public LocalDateTime parseDateTime(String dateTime) {
        try {
            // Define a formatter for date with optional time
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm am/pm");

            if (dateTime.contains(":")) {
                // If a time is included, parse as LocalDateTime
                return LocalDateTime.parse(dateTime, dateTimeFormatter);
            } else {
                // If no time is given, default to midnight
                return LocalDate.parse(dateTime, dateFormatter).atStartOfDay();
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date/time format: " + dateTime);
        }
    }




    }
