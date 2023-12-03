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
    public CompetitiveMember(String name, LocalDate dateOfBirth, int phonenumber, int memberNumber, String memberType, Duration swimTime, LocalDate dateOfSwim, SwimmingDiscipline swimmingDiscipline) {
        super(name, dateOfBirth, phonenumber, memberNumber, memberType, swimTime, dateOfSwim, swimmingDiscipline, eventName, eventPlacement, eventSwimTime);
    // Training
    public CompetitiveMember(String name, LocalDate dateOfBirth, int phonenumber, int memberNumber, String memberType, Duration swimTime, LocalDate dateOfSwim, String swimmingDiscipline) {
        super(name, dateOfBirth, phonenumber, memberNumber, memberType, swimTime, dateOfSwim, swimmingDiscipline);

        initializeEventDetailsTræning(swimTime, dateOfSwim, swimmingDiscipline);
    }

    // Event
    public CompetitiveMember(String name, LocalDate dateOfBirth, int phonenumber, int memberNumber, String memberType, Duration swimTime, LocalDate dateOfSwim, SwimmingDiscipline swimmingDiscipline, String eventName, String eventPlacement, Duration eventSwimTime) {
        super(name, dateOfBirth, phonenumber, memberNumber, memberType, swimTime, dateOfSwim, swimmingDiscipline, eventName, eventPlacement, eventSwimTime);

        initializeEventDetailsEvent(swimTime, dateOfSwim, swimmingDiscipline, eventName, eventPlacement, eventSwimTime);
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
    private void initializeEventDetailsTræning(Duration swimTime, LocalDate dateOfSwim, String swimmingDiscipline) {
        this.swimTime = swimTime;
        this.dateOfSwim = dateOfSwim;
        this.swimmingDiscipline = swimmingDiscipline;
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
