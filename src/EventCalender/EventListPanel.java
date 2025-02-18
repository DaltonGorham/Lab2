package EventCalender;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private JCheckBox removeEventsCheckbox;
    private JCheckBox removeDeadlinesCheckbox;
    private JCheckBox removeMeetingsCheckbox;
    private JCheckBox removeCompletedCheckbox;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");  // format for dates
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("hh:mm a");     // formats for times

    // constructor
    public EventListPanel(JFrame parentFrame) {
        setSize(800, 600);
        this.parentFrame = parentFrame;
        String[] sortingOptions = {"Sort by Name", "Sort by Date", "Sort by Name(Reverse Order)", "Sort by Date(Reverse Order)"};
        events = new ArrayList<Event>();
        controlPanel = new JPanel();
        displayPanel = new JPanel();
        sortDropDown = new JComboBox<>(sortingOptions);
        filterDisplay = new JCheckBox("Show Filters");
        addEventButton = new JButton("Add Event");

        // Multiple filter checkboxes
        removeEventsCheckbox = new JCheckBox("Remove Events");
        removeDeadlinesCheckbox = new JCheckBox("Remove Deadlines");
        removeMeetingsCheckbox = new JCheckBox("Remove Meetings");
        removeCompletedCheckbox = new JCheckBox("Remove Completed Tasks");

        // add all controls to control panel
        controlPanel.add(addEventButton);
        controlPanel.add(sortDropDown);
        controlPanel.add(filterDisplay);
        controlPanel.add(removeEventsCheckbox);
        controlPanel.add(removeDeadlinesCheckbox);
        controlPanel.add(removeMeetingsCheckbox);
        controlPanel.add(removeCompletedCheckbox);
        setFilterCheckboxVisibility(false);
        add(controlPanel);

        // create the display panel
        displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
        displayPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(displayPanel);
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));

        // action listener methods
        handleSorting();
        handleFiltering();
        handleAddEvent();
        updateDisplay();

    }


    // If this is set as true the filter method will show up
    private void setFilterCheckboxVisibility(boolean visible) {
        removeEventsCheckbox.setVisible(visible);
        removeDeadlinesCheckbox.setVisible(visible);
        removeMeetingsCheckbox.setVisible(visible);
        removeCompletedCheckbox.setVisible(visible);
    }

    // Updates the display for every new event added and shows new filters when applicable
    public void updateDisplay() {
        if (removeCompletedCheckbox.isSelected()) {
            removeCompletedEvents();
        }

        // Apply filters based on individual checkboxes
        if (removeEventsCheckbox.isSelected()) {
            removeEvents();
        }

        if (removeDeadlinesCheckbox.isSelected()) {
            removeDeadlines();
        }

        if (removeMeetingsCheckbox.isSelected()) {
            removeMeetings();
        }

        displayPanel.removeAll(); // Clear current display

        for (Event event : events) {
            displayPanel.add(new EventPanel(event));
        }

        // Refresh display
        displayPanel.revalidate();
        displayPanel.repaint();
    }

    // remove all deadlines
    public void removeDeadlines() {
        events.removeIf(event -> event instanceof Deadline);
    }

    // remove all meetings
    public void removeMeetings() {
        events.removeIf(event -> event instanceof Meeting);
    }

    // remove all events
    public void removeEvents() {
        events.clear();
    }

    // Remove completed events from the list
    public void removeCompletedEvents() {
        events.removeIf(event -> event instanceof Completable && ((Completable) event).isCompleted());
    }



    // Method to sort by name, date, or those in reverse
    public void handleSorting() {
        sortDropDown.addActionListener(e -> {
            String selectedOption = (String) sortDropDown.getSelectedItem();

            switch (selectedOption) {
                case "Sort by Name" -> events.sort(Comparator.comparing(Event::getName));
                case "Sort by Date" -> events.sort(Comparator.comparing(Event::getDateTime));
                case "Sort by Name(Reverse Order)" -> events.sort(Comparator.comparing(Event::getName).reversed());
                case "Sort by Date(Reverse Order)" -> events.sort(Comparator.comparing(Event::getDateTime).reversed());
            }
            updateDisplay();
        });
    }

    // The action listener for all filter checkboxes
    private void handleFiltering() {
        filterDisplay.addActionListener(e -> {
            boolean isChecked = filterDisplay.isSelected();
            setFilterCheckboxVisibility(isChecked);
            updateDisplay();
        });
        removeEventsCheckbox.addActionListener(e -> updateDisplay());
        removeDeadlinesCheckbox.addActionListener(e -> updateDisplay());
        removeMeetingsCheckbox.addActionListener(e -> updateDisplay());
        removeCompletedCheckbox.addActionListener(e -> updateDisplay());
    }

    // The action listener for the add event button which uses the AddEventModal class to create a modal
    public void handleAddEvent() {
        addEventButton.addActionListener(e -> {
            AddEventModal modal = new AddEventModal(parentFrame);
            modal.setVisible(true);

            if (modal.isConfirmed()) {
                String eventType = modal.getEventType();
                if (eventType == null) {
                    return;
                }
                // Get the input from the modal
                String eventName = modal.getEventName();
                String eventLocation = modal.getEventLocation();
                String eventDate = modal.getEventDate();

                // parse the input and add it to the events array list based off meeting or deadline
                switch (eventType) {
                    case "Meeting":
                        LocalDateTime eventStartTime = parseDateTime(modal.getEventDate(), modal.getEventStartTime());
                        LocalDateTime eventEndTime = parseDateTime(modal.getEventDate(), modal.getEventEndTime());
                       addEvent(new Meeting(eventName, eventStartTime, eventEndTime, eventLocation));
                        break;
                    case "Deadline":
                        String eventTime = modal.getEventEndTime();
                        System.out.println(eventTime);
                        addEvent(new Deadline(eventName, parseDateTime(eventDate, eventTime)));
                }
                updateDisplay();
            }
        });
    }

    // method to add the events to this list
    public void addEvent(Event event) {
        events.add(event);
        updateDisplay();
    }

    /*
        Method that translates the LocalDateTime into something that more readable and easier to input.
        If a time is not added then it will automatically default to midnight.
     */
    private LocalDateTime parseDateTime(String date, String time) {
        try {
            LocalDate parsedDate = LocalDate.parse(date, DATE_FORMATTER);
            LocalTime parsedTime = (time != null && !time.isEmpty()) ? LocalTime.parse(time, TIME_FORMATTER) : LocalTime.MIDNIGHT;
            System.out.println(parsedDate);
            System.out.println(parsedTime);
            return LocalDateTime.of(parsedDate, parsedTime);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date/time format: " + date + (time != null ? " " + time : ""));
        }
    }
}
