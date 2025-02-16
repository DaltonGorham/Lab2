package EventCalender;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
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


    public EventListPanel() {

        String[] sortingOptions = {"Sort by Name", "Sort by Date", "Sort by Name(Reverse Order)", "Sort by Date(Reverse Order)"};
        String[] filterOptions = {"Hide Events", "Hide Deadlines", "Hide Meetings"};
        events = new ArrayList<Event>();
        controlPanel = new JPanel();
        displayPanel = new JPanel();
        sortDropDown = new JComboBox<>(sortingOptions);
        filterDisplay = new JCheckBox("Filter Display");
        addEventButton = new JButton("Add Event");


        controlPanel.add(addEventButton);
        controlPanel.add(filterDisplay);
        controlPanel.add(sortDropDown);
        add(controlPanel);

        displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
        displayPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(new JScrollPane(displayPanel));
        events.add(new Deadline("Submit assignment", LocalDateTime.now().plusDays(2)));
        events.add(new Meeting("Team Meeting", LocalDateTime.now(), LocalDateTime.now().plusHours(3), "Room 1"));
        events.add(new Meeting("Team Meeting", LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(3), "Room 2"));


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

        filterDisplay.addActionListener(e -> {
            updateDisplay();
        });



        updateDisplay();
    }

    public void updateDisplay() {
       displayPanel.removeAll();
       for (Event event : events) {
           boolean displaying = true;

           if (filterDisplay.isSelected() && event instanceof Completable completable){
               displaying = completable.isCompleted();
           }

           if (filterDisplay.isSelected() && event instanceof Deadline deadline){
               displaying = !deadline.isCompleted();
           }

           if (filterDisplay.isSelected() && event instanceof Meeting meeting){
               displaying = !meeting.isCompleted();
           }

           if (displaying) {
               displayPanel.add(new EventPanel(event));
           }
       }
       displayPanel.revalidate();
       displayPanel.repaint();
    }





}
