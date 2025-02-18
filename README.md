# Event Planner Application

This project is planning and managing events like meetings and deadlines. The design utilizes **object-oriented programming principles** with inheritance and polymorphism to represent different types of events. Users can interact with the GUI to add, view, modify, and filter events.

---

## Project Structure

### Packages
- **`EventCalender`**: Contains all the logic, object models, and GUI components required for running the application.

### Main Components
1. **`Event`**: The abstract base class for `Meeting` and `Deadline`.
2. **Event Types**:
    - **`Meeting`**: Represents events with a start time, end time, and location.
    - **`Deadline`**: Represents events with a single due date/time.
3. **GUI Components**:
    - **`AddEventModal`**: A modal dialog for creating new events.
    - **`EventListPanel`**: The main panel that displays and controls event management.
    - **`EventPanel`**: A sub panel for displaying individual event details.
4. **Utility Interfaces**:
    - **`Completable`**: Interface for events that can be marked as completed.

---

## Application Flow

### How It Works
1. **Start**:
    - The application is launched via the `EventPlanner` class.
    - A GUI window (`JFrame`) is initialized with an **`EventListPanel`**, the main panel containing all controls and event displays.
2. **Default Events**:
    - Two events (one `Meeting` and one `Deadline`) are added as default upon launching.
3. **User Interaction**:
    - Users can add events via a modal dialogue box.
    - Events are displayed and sorted/filtered dynamically.
    - Events can also be marked as completed or removed based on filters.
4. **Filters & Sorting**:
    - Sort events by name or date (both ascending/descending).
    - Apply filters to display or remove specific event categories (Meetings, Deadlines, Completed).

---

## Features

### Core Functionalities

1. **Add Events**:
    - Events can be added through the `AddEventModal` dialog.
    - Input is validated for proper formatting (date/time/location).

2. **Display Events**:
    - Events are displayed in a scrollable panel (`EventPanel`).
    - Display adapts to whether the event is a `Meeting` or `Deadline`.

3. **Mark as Complete**:
    - Completable events (both `Meeting` and `Deadline`) can be marked as complete using a checkbox.
    - The status is reflected in the GUI dynamically.

4. **Sorting**:
    - Sort events by name or date (ascending or reverse order) using a dropdown.

5. **Filtering**:
    - Toggle filters using checkboxes:
        - Remove all events.
        - Remove only Deadlines or Meetings.
        - Remove completed tasks.

---

### GUI Interaction Workflow

- **Main Frame** (`EventPlanner`):
    - Contains the main `EventListPanel` where all event operations are managed.
- **Event List Panel** (`EventListPanel`):
    - Displays a list of events using `EventPanel`.
    - Includes controls for adding, filtering, and sorting events.
- **Event Panel** (`EventPanel`):
    - Displays details for individual events:
        - For `Meetings`: Includes start time, end time, duration, and location.
        - For `Deadlines`: Shows due date and time.
    - Allows marking events as complete.
- **Add Event Modal** (`AddEventModal`):
    - Provides an input form for creating either `Meeting` or `Deadline`.
    - Dynamically updates its fields (e.g., enabling/disabling fields) based on the event type.

---

## Inheritance Structure

1. **`Event` (Abstract Class)**:
    - Base class for all types of events.
    - Implements properties like `name` and `dateTime`.
    - Implements the `Comparable` interface to sort events by date/name.

2. **`Meeting` (Subclass)**:
    - Inherits from `Event`.
    - Adds additional properties:
        - `startTime`, `endTime`, `location`, and `duration`.
    - Implements `Completable` interface.

3. **`Deadline` (Subclass)**:
    - Inherits from `Event`.
    - Adds a `complete` status.
    - Implements `Completable` interface.

4. **`Completable` (Interface)**:
    - Applied to any event type that can be marked as completed.
    - Implemented by both `Meeting` and `Deadline`.

---



## Usage Instructions

### Running the Application
1. **Set up the Project**:
    - Clone or import the project into your favorite IDE (e.g., IntelliJ IDEA).
    - Ensure you have the required Java JDK installed.

2. **Launch**:
    - Run the `main` method in the `EventPlanner` class.

3. **Interacting with the App**:
    - **Add Events**:
        - Click the "Add Event" button, and enter details using the provided dialog.
        - Input validation ensures correct formatting.
    - **Sort Events**:
        - Select sorting criteria (by date or name) using the dropdown.
    - **Apply Filters**:
        - Use checkboxes to filter events (e.g., remove deadlines, meetings, or completed tasks).
    - **Mark Events as Complete**:
        - Use the checkbox within each event panel to mark events as "completed".

---

## Methods Overview

### `EventPlanner`
- **`addDefaultEvents(List<Event>)`**:
  - Adds preset default events (e.g., one `Meeting` and one `Deadline`). 
- **`main(String[])`**:
  - Creates the JFrame and initializes the `EventListPanel` with default events.

---

### `Event (Abstract)`
- **Constructors**:
  - `Event(String name, LocalDateTime dateTime)`: Initializes the name and event date.
- **Methods**:
  - **`String getName()`**: Returns the event name.
  - **`void setName(String name)`**: Updates the event name.
  - **`LocalDateTime getDateTime()`**: Gets the associated date and time.
  - **`void setDateTime(LocalDateTime dateTime)`**: Sets the associated date and time.
  - **`int compareTo(Event e)`**:
    - First compares events by datetime.
    - If equal, sorts by name alphabetically.

---

### `Meeting`
- **Constructors**:
  - `Meeting(String name, LocalDateTime start, LocalDateTime end, String location)`: Creates a meeting event.
- **Methods**:
  - **`LocalDateTime getStartDateTime()`**: Returns the start time of the meeting.
  - **`LocalDateTime getEndDateTime()`**: Returns the end time of the meeting.
  - **`Duration getDuration()`**: Calculates and returns the duration of the meeting.
  - **`String getLocation()`**: Returns the meeting location.
  - **`void complete()`**: Marks the meeting as completed.
  - **`boolean isCompleted()`**: Checks if the meeting is marked as complete.

---

### `Deadline`
- **Constructors**:
  - `Deadline(String name, LocalDateTime deadline)`: Creates a deadline event.
- **Methods**:
  - **`void complete()`**: Marks the deadline as complete.
  - **`boolean isCompleted()`**: Returns if the deadline task is completed.

---

### `AddEventModal`
- **Constructors**:
  - `AddEventModal(Frame parent)`: Creates a modal dialog for adding events.
- **Core Methods**:
  - **`boolean isEntryValid()`**: Validates input fields to ensure correct formats for date and time.
  - **`boolean isConfirmed()`**: Checks if the user confirmed the modal by clicking "Add Event".
  - **`String getEventName()`**: Retrieves the entered event name.
  - **`String getEventType()`**: Retrieves the event type (Meeting or Deadline).
  - **`String getEventDate()`**: Retrieves the entered event date.
  - **`String getEventStartTime()`**: Retrieves the entered start time (if applicable).
  - **`String getEventEndTime()`**: Retrieves the entered end time.
  - **`String getEventLocation()`**: Retrieves the entered event location.

---

### `EventListPanel`
- **Constructors**:
  - `EventListPanel(JFrame parentFrame)`: Initializes the panel with all controls.
- **Core Methods**:
  - **`addEvent(Event event)`**: Adds an event to the list and updates the display.
  - **`updateDisplay()`**: Re-renders the display panel with current events.
  - **`removeDeadlines()`**: Filters out all deadlines from the event list.
  - **`removeMeetings()`**: Filters out all meetings from the event list.
  - **`removeEvents()`**: Clears all events from the list.
  - **`removeCompletedEvents()`**: Removes completed tasks from the list.
  - **`handleSorting()`**: Sorts events based on user dropdown selection (name/date, ascending/descending).
  - **`handleFiltering()`**: Handles the behavior of multiple filtering checkboxes.
  - **`handleAddEvent()`**: Instantiates the `AddEventModal` dialog and processes newly added events.

---

### `EventPanel`
- **Constructors**:
  - `EventPanel(Event event)`: Initializes the panel for a specific event.
- **Core Methods**:
  - Dynamically formats its components based on the type of event (Meeting or Deadline).

---

## Running the Application

1. **Setup**:
   - Clone/import the project into your favorite Java IDE.
   - Make sure you have Java SDK 23 set up.

2. **Launch**:
   - Run the `EventPlanner` class to start the application.

3. **Usage**:
   - Add events using the `Add Event` button.
   - Mark events as complete or apply filters to customize your view.
   - Sort events by name or date using the sorting dropdown.

---

## Future Enhancements
- Enable event editing directly from the GUI.
- Introduce persistent storage (e.g., file/database integration) to save events across sessions.
- Add reminders or notifications for upcoming events.
- Implement recurring events for more robust event management.
