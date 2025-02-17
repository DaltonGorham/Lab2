package EventCalender;
import java.time.Duration;
import java.time.LocalDateTime;

public class Meeting extends Event implements Completable{

    private LocalDateTime endDateTime;
    private LocalDateTime startDateTime;
    private String location;
    private boolean complete;

    @Override
    public void complete(){
        complete = true;
    }
    @Override
    public boolean isCompleted() {
        return complete;
    }
    @Override
    public String getName(){return name;}

    public Meeting(String name, LocalDateTime start, LocalDateTime end, String location){
        super(name, start);
        this.endDateTime = end;
        this.startDateTime = start;
        this.location = location;

    }

    public LocalDateTime getEndDateTime() {return endDateTime;}

    public LocalDateTime getStartDateTime() {return startDateTime;}

    public String getLocation() {return location;}

    public Duration getDuration() {return Duration.between(getDateTime(), endDateTime);}

    public void setEndDateTime(LocalDateTime endDateTime) {this.endDateTime = endDateTime;}

    public void setLocation(String location) {this.location = location;}











}
