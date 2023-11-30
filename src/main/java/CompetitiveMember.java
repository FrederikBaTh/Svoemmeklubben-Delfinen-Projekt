import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CompetitiveMember extends Member {

    private Duration swimTime;
    private LocalDate dateOfSwim;
    private String swimmingDiscipline;

    private String eventName;
    private String eventPlacement;
    //private Duration eventSwimTime;

    //no event
    public CompetitiveMember(String name, LocalDate dateOfBirth, String gender, int phonenumber, String adress, int memberNumber, String passiveOrActive, String memberType, String motionist, String competitive, Duration swimTime, LocalDate dateOfSwim, String swimmingDiscipline){
    super(name, dateOfBirth, gender, phonenumber, adress, memberNumber, passiveOrActive, memberType, motionist, competitive,swimTime,dateOfSwim,swimmingDiscipline);

        initializeEventDetails(swimTime, dateOfSwim, swimmingDiscipline);
        //this.swimTime = swimTime;
       // this.dateOfSwim = dateOfSwim;
       // this.swimmingDiscipline = swimmingDiscipline;


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
            Duration swimTime,
            LocalDate dateOfSwim,
            String swimmingDiscipline,
            String eventName,
            String eventPlacement) {
        super(
                name,
                dateOfBirth.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                gender,
                phonenumber,
                adress,
                memberNumber,
                passiveOrActive,
                memberType,
                motionist,
                competitive);

        initializeEventDetails(swimTime, dateOfSwim, swimmingDiscipline);
        //this(name, dateOfBirth, gender, phonenumber, adress, memberNumber, passiveOrActive, memberType, motionist, competitive, swimTime, dateOfSwim, swimmingDiscipline);
        //this.eventName = eventName;
        //this.eventPlacement = eventPlacement;

    }
    private void initializeEventDetails(Duration swimTime, LocalDate dateOfSwim, String swimmingDiscipline) {
        this.swimTime = swimTime;
        this.dateOfSwim = dateOfSwim;
        this.swimmingDiscipline = swimmingDiscipline;
    }











}
