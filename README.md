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

### Main Classes and Methods

#### `EventPlanner`
- **`addDefaultEvents(List<Event> defaultEvents)`**: Adds preset events.
- **`main(String[] args)`**: Initializes the main GUI.

#### `Event`
- **`getName()`**: Returns the event name.
- **`compareTo(Event e)`**: Compares events for sorting.

#### `Meeting`
- **`getDuration()`**: Calculates the time duration of the meeting.
- **`complete()`**: Marks the meeting as completed.

#### `Deadline`
- **`complete()`**: Marks the deadline as completed.

#### `AddEventModal`
- **`isEntryValid()`**: Validates modal input.
- **`isConfirmed()`**: Tells whether input is confirmed by the user.

#### `EventListPanel`
- **`addEvent(Event event)`**: Adds a new event to the list.
- **`updateDisplay()`**: Refreshes the event display panel.
- **`handleSorting()`**: Applies sorting based on user input.
- **`handleFiltering()`**: Applies selected filters to the event list.

---

## Future Improvements
- Allow editing of existing events.
- Add better persistence (store/load events from a database or file).
- Improve modal validation for edge cases.
- Include reminders/notifications for upcoming deadlines or meetings.