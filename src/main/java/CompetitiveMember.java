import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CompetitiveMember {

    private LocalTime swimTime;
    private LocalDate dateOfSwim;
    private SwimmingDiscipline swimmingDiscipline;

    private String eventName;
    private String eventPlacement;
    //private Duration eventSwimTime;

    //no event
    // no event
    public CompetitiveMember(String name, String dateOfBirth, String gender, int phonenumber, String adress, int memberNumber, String passiveOrActive, String memberType, String motionist, String competitive, LocalTime swimTime, String dateOfSwim, SwimmingDiscipline swimmingDiscipline){
        initializeEventDetails(swimTime, dateOfSwim, swimmingDiscipline);
    }


    //with event
    public CompetitiveMember(
            String name,
            LocalDate dateOfBirth,
            String gender,
            int phonenumber,
            String adress,
            int memberNumber,
            String passiveOrActive,
            String memberType,
            String motionist,
            String competitive,
            LocalTime swimTime,
            String dateOfSwim,
            SwimmingDiscipline swimmingDiscipline,
            String eventName,
            String eventPlacement) {

        initializeEventDetails(swimTime, dateOfSwim, swimmingDiscipline);
        //this(name, dateOfBirth, gender, phonenumber, adress, memberNumber, passiveOrActive, memberType, motionist, competitive, swimTime, dateOfSwim, swimmingDiscipline);
        //this.eventName = eventName;
        //this.eventPlacement = eventPlacement;

    }

    public CompetitiveMember(LocalTime svimTime, LocalDate dateOfSwim, SwimmingDiscipline swimmingDiscipline) {
    }

    public LocalTime getSwimTime() {
        return swimTime;
    }
    public LocalDate getDateOfSwim() {
        return dateOfSwim;
    }
    public SwimmingDiscipline getSwimmingDiscipline() {
        return swimmingDiscipline;
    }


    private void initializeEventDetails(LocalTime swimTime, String dateOfSwim, SwimmingDiscipline swimmingDiscipline) {
        this.swimTime = swimTime;
        this.dateOfSwim = LocalDate.parse(dateOfSwim, DateTimeFormatter.ofPattern("dd-MM-yyyy"));;
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
