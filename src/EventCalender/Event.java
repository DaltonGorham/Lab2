package EventCalender;
import java.time.LocalDateTime;

abstract class Event implements Comparable<Event> {
    String name;
    LocalDateTime dateTime;

    public Event(String name, LocalDateTime datetime){
        this.name = name;
        this.dateTime = datetime;
    }

    abstract String getName();


    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime datetime) {
        this.dateTime = datetime;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Event e) {
        int dateCompare = dateTime.compareTo(e.dateTime);
        if(dateCompare == 0){
            return name.compareTo(e.name);
        }
        return dateCompare;
    }

}
