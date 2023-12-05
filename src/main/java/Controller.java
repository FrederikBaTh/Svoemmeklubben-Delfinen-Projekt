import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

//
public class Controller {


    Member member = new Member();
    CompetitiveMember competitiveMember = new CompetitiveMember();

    Database database = new Database();

    public void registrerMedlem(String name, String dateOfBirth, String gender, int phonenumber, String adress, int memberNumber, String passiveOrActive, String memberType, String motionist) {
        database.registrerMedlem(name, dateOfBirth, gender, phonenumber, adress, memberNumber, passiveOrActive, memberType, motionist);
    }


    public void sortMembersByAge(ArrayList<Member> members) {
        database.sortMembersByAge(members);
    }

    public int calculateAge(LocalDate date){
        return member.calculateAge(date);
    }

    public ArrayList<Member> getMembers() {
        return database.getMeembers();

    }

    public int calculateYearlyIncome(){
        return database.calculateYearlyIncome();
    }


    public int generateMemberNumber(){

        return member.generateMemberNumber();
    }


    public void registrerTræningsResultat(int memberNumber, Duration svimTime, LocalDate dateOfSwim, SwimmingDiscipline swimmingDiscipline) {
        database.registrerTræningTid(memberNumber, svimTime, dateOfSwim, swimmingDiscipline);
    }

    public void registrerEventResultat(int memberNumber, Duration swimTime, LocalDate dateOfSwim, SwimmingDiscipline swimmingDiscipline, String eventName, String eventPlacement, Duration eventSwimTime) {
        database.registrerEventTid(memberNumber, swimTime, dateOfSwim, swimmingDiscipline, eventName, eventPlacement, eventSwimTime);
    }

    public boolean memberNumberUsed(int input) {
        return false;
    }


    public void printCompMembersOver18() {
        database.printCompMembersOver18();
    }

    public void setCompetitiveMember(String name, LocalDate dateOfBirth, String gender, int phonenumber, String address, int memberNumber, String passiveOrActive, String memberType, String motionist) {
        database.setCompetitiveMember(name, dateOfBirth, gender, phonenumber, address, memberNumber, passiveOrActive, memberType, motionist);

    }

}