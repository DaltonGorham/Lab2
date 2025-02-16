package EventCalender;
import java.time.LocalDateTime;

public class Deadline extends Event implements Completable {

    private boolean complete;


    Deadline(String name, LocalDateTime deadline){
        super(name, deadline);
    }
    @Override
    public void complete(){
        complete = true;
    }
    @Override
    public boolean isCompleted(){
        return complete;
    }
    @Override
    public String getName(){
        return name;
    }






}
