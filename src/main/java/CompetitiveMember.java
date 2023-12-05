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




    // Training
    public CompetitiveMember( int memberNumber, Duration swimTime, LocalDate dateOfSwim, SwimmingDiscipline swimmingDiscipline) {
        super(memberNumber, swimTime, dateOfSwim, swimmingDiscipline);

        initializeEventDetailsTræning(swimTime, dateOfSwim, swimmingDiscipline);
    }

    // Event
    public CompetitiveMember(int memberNumber, Duration swimTime, LocalDate dateOfSwim, SwimmingDiscipline swimmingDiscipline, String eventName, String eventPlacement) {
        super(memberNumber, swimTime, dateOfSwim, swimmingDiscipline, eventName, eventPlacement);

        initializeEventDetailsEvent(swimTime, dateOfSwim, swimmingDiscipline, eventName, eventPlacement, eventSwimTime);
    }

    public CompetitiveMember() {

    }

    public CompetitiveMember(LocalTime svimTime, LocalDate dateOfSwim, SwimmingDiscipline swimmingDiscipline) {
    }


    private void initializeEventDetailsEvent(Duration swimTime, LocalDate dateOfSwim, SwimmingDiscipline swimmingDiscipline, String eventName, String eventPlacement, Duration eventSwimTime) {
        this.swimTime = swimTime;
        this.dateOfSwim = dateOfSwim;
        this.swimmingDiscipline = swimmingDiscipline;
        this.eventName = eventName;
        this.eventPlacement = eventPlacement;
        this.eventSwimTime = eventSwimTime;
    }
    private void initializeEventDetailsTræning(Duration swimTime, LocalDate dateOfSwim, SwimmingDiscipline swimmingDiscipline) {
        this.swimTime = swimTime;
        this.dateOfSwim = dateOfSwim;
        this.swimmingDiscipline = swimmingDiscipline;
    }

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
