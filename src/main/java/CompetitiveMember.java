import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class CompetitiveMember extends Member {

    private Duration swimTime;
    private LocalDate dateOfSwim;
    private SwimmingDiscipline swimmingDiscipline;
    private String eventName;
    private String eventPlacement;
    private Duration eventSwimTime;


    //TODO Training Initializer
    public CompetitiveMember(int memberNumber, Duration swimTime, LocalDate dateOfSwim, SwimmingDiscipline swimmingDiscipline) {
        super(memberNumber, swimTime, dateOfSwim, swimmingDiscipline);

        initializeEventDetailsTræning(memberNumber, swimTime, dateOfSwim, swimmingDiscipline);
    }


    //TODO Event Initializer
    public CompetitiveMember(int memberNumber, Duration swimTime, LocalDate dateOfSwim, SwimmingDiscipline swimmingDiscipline, String eventName, String eventPlacement) {
        super(memberNumber, swimTime, dateOfSwim, swimmingDiscipline, eventName, eventPlacement);

        initializeEventDetailsEvent(memberNumber, swimTime, dateOfSwim, swimmingDiscipline, eventName, eventPlacement, eventSwimTime);
    }


//TODO IDK WHAT TO DO MÅSKE SLET IDK
   // public CompetitiveMember(int memberNumber, LocalTime swimTime, LocalDate dateOfSwim, SwimmingDiscipline swimmingDiscipline) {
     //   super(memberNumber, swimTime, dateOfSwim, swimmingDiscipline);
    //}


//TODO Getters
    public Duration getSwimTime() {
        return swimTime;
    }
    public LocalDate getDateOfSwim() {
        return dateOfSwim;
    }
    public SwimmingDiscipline getSwimmingDiscipline() {
        return swimmingDiscipline;
    }
    public String getEventName() {
        return eventName;
    }
    public String getEventPlacement() {
        return eventPlacement;
    }

    //
    private void initializeEventDetailsEvent(int memberNumber, Duration swimTime, LocalDate dateOfSwim, SwimmingDiscipline swimmingDiscipline, String eventName, String eventPlacement, Duration eventSwimTime) {
        this.setMemberNumber(memberNumber);
        this.swimTime = swimTime;
        this.dateOfSwim = dateOfSwim;
        this.swimmingDiscipline = swimmingDiscipline;
        this.eventName = eventName;
        this.eventPlacement = eventPlacement;
        this.eventSwimTime = eventSwimTime;
    }
    private void initializeEventDetailsTræning(int memberNumber, Duration swimTime, LocalDate dateOfSwim, SwimmingDiscipline swimmingDiscipline) {
        this.setMemberNumber(memberNumber);
        this.swimTime = swimTime;
        this.dateOfSwim = dateOfSwim;
        this.swimmingDiscipline = swimmingDiscipline;
    }
    public void editSwimTime(String newSwimTime) {
        this.swimTime = Duration.parse(newSwimTime);
    }
    public void editDateOfSwim(String newDateOfSwim){
        this.dateOfSwim = LocalDate.parse(newDateOfSwim);
    }
    public void editSwimmingDiscipline(String newSwimmingDiscipline) {
        this.swimmingDiscipline = SwimmingDiscipline.valueOf(newSwimmingDiscipline);
    }
    public void editEventName(String newEventName) {
        this.eventName = newEventName;
    }
    public void editEventPlacement(String newEventPlacement) {
        this.eventPlacement = newEventPlacement;
    }

    @Override
    public String toString() {
        return "CompetitiveMember{" +
                "swimTime=" + swimTime +
                ", dateOfSwim=" + dateOfSwim +
                ", swimmingDiscipline=" + swimmingDiscipline +
                ", eventName='" + eventName + '\'' +
                ", eventPlacement='" + eventPlacement + '\'' +
                '}';
    }
}
